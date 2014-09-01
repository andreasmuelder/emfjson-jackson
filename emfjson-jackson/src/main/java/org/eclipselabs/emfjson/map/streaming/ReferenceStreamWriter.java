package org.eclipselabs.emfjson.map.streaming;

import static org.eclipselabs.emfjson.common.Constants.EJS_REF_KEYWORD;
import static org.eclipselabs.emfjson.common.Constants.EJS_TYPE_KEYWORD;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipselabs.emfjson.common.IDResolver;
import org.eclipselabs.emfjson.common.Options;

import com.fasterxml.jackson.core.JsonGenerator;

public class ReferenceStreamWriter {

	private final IDResolver idResolver;
	private final Options options;

	public ReferenceStreamWriter(IDResolver idResolver, Options options) {
		this.idResolver = idResolver;
		this.options = options;
	}

	public void serialize(JsonGenerator generator, String key, EReference reference, Object value) {
		if (reference.isMany()) {
			serializeMany(generator, key, (Collection<?>) value);
		} else {
			serializeOne(generator, key, (EObject) value);
		}
	}

	public void serializeMany(JsonGenerator generator, String key, Collection<?> values) {
		try {
			generator.writeFieldName(key);
			generator.writeStartArray();
			for (Object current: values) {
				if (current instanceof EObject) {
					writeObjectRef(generator, (EObject) current);
				}
			}
			generator.writeEndArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void serializeOne(JsonGenerator generator, String key, EObject value) {
		try {
			generator.writeFieldName(key);
			writeObjectRef(generator, value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeObjectRef(JsonGenerator generator, EObject object) throws IOException {
		String ref = idResolver.get(object);
		generator.writeStartObject();
		if (options.serializeRefTypes) {
			String type = idResolver.get(object.eClass());
			generator.writeStringField(EJS_TYPE_KEYWORD, type);
		}
		generator.writeStringField(EJS_REF_KEYWORD, ref);
		generator.writeEndObject();
	}

}
