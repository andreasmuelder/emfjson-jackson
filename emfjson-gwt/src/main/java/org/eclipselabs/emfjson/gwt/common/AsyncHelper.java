package org.eclipselabs.emfjson.gwt.common;

import static org.eclipselabs.emfjson.common.EObjects.setOrAdd;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.util.Callback;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipselabs.emfjson.gwt.map.JsonReader;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

public class AsyncHelper {

	private static class AsyncIterator {
		final EObject object;
		final Iterator<EReference> it;
		final Map<EReference, JSONValue> map;
		final Callback<EObject> done;
		final JsonReader reader;

		AsyncIterator(
				EObject object,
				Iterator<EReference> it,
				Map<EReference, JSONValue> map, 
				Callback<EObject> done, 
				JsonReader reader) {
			this.object = object;
			this.it = it;
			this.map = map;
			this.done = done;
			this.reader = reader;
		}

		void forEach() {
			if (it.hasNext()) {
				final EReference reference = it.next();
				final JSONValue value = map.get(reference);
				final JSONArray array = value.isArray();

				if (array != null) {

					forEach(0, array, reference);

				} else {

					JSONObject obj = value.isObject();
					if (obj == null) {

						done.onFailure(new ClassCastException());

					} else {

						reader.parseObject(value.isObject(), new Callback<EObject>() {
							@Override public void onFailure(Throwable caught) {
								done.onFailure(caught);
							}
							@Override public void onSuccess(EObject result) {
								setOrAdd(object, reference, result);
								forEach();
							}
						});

					}

				}

			} else {

				done.onSuccess(object);

			}
		}
		
		void forEach(
				final int pos, 
				final JSONArray array, 
				final EReference reference) {

			if (pos >= array.size()) {

				forEach();

			} else {

				JSONValue current = array.get(pos);
				JSONObject obj = current.isObject();
				if (obj == null) {

					done.onFailure(new ClassCastException());

				} else {

					reader.parseObject(obj, new Callback<EObject>() {
						@Override public void onFailure(Throwable caught) {
							done.onFailure(caught);
						}
						@Override public void onSuccess(EObject result) {
							setOrAdd(object, reference, result);
							forEach(pos + 1, array, reference);
						}
					});

				}

			}
		}
	}

	public static void forEach(
			EObject object, 
			Iterator<EReference> it, 
			Map<EReference, JSONValue> map,  
			Callback<EObject> done, 
			JsonReader reader) {

		new AsyncIterator(object, it, map, done, reader).forEach();
	}

	public static void forEach(
			final Resource resource, 
			final int position, 
			final JSONArray array, 
			final JsonReader reader,
			final Callback<Resource> callback) {

		if (position >= array.size()) {
			
			callback.onSuccess(resource);
			
		} else {
			
			final JSONValue current = array.get(position);
			final JSONObject currentObject = current.isObject();

			if (currentObject != null) {

				reader.parseObject(currentObject, new Callback<EObject>() {
					@Override public void onFailure(Throwable caught) {
						callback.onFailure(caught);
					}
					@Override public void onSuccess(EObject result) {
						if (result != null) {
							resource.getContents().add(result);
						}
						forEach(resource, position + 1, array, reader, callback);
					}
				});

			} else {

				callback.onFailure(new ClassCastException());

			}
		}
	}
}
