/*
 * Copyright (c) 2015 Guillaume Hillairet.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Guillaume Hillairet - initial API and implementation
 *
 */
package org.emfjson.jackson.tests.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emfjson.jackson.databind.ser.ETypeSerializer;
import org.emfjson.jackson.databind.ser.IdSerializer;
import org.emfjson.jackson.databind.ser.references.EReferenceSerializer;
import org.emfjson.jackson.handlers.URIHandler;
import org.emfjson.jackson.internal.Cache;
import org.emfjson.jackson.junit.model.ModelFactory;
import org.emfjson.jackson.junit.model.ModelPackage;
import org.emfjson.jackson.junit.model.User;
import org.emfjson.jackson.module.EMFModule;
import org.emfjson.jackson.resource.JsonResourceFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CustomSerializersTest {

	private IdSerializer idSerializer = new IdSerializer() {
		@Override
		public void serialize(EObject value, JsonGenerator jg, SerializerProvider serializers) throws IOException {
//			super.serialize(value, jg, serializers);
		}
	};

	private EReferenceSerializer referenceSerializer = new EReferenceSerializer() {
		@Override
		public void serialize(EObject value, JsonGenerator jg, EObject parent, Cache cache, URIHandler handler) throws IOException {
			jg.writeString(EcoreUtil.getID(value));
		}
	};

	private ETypeSerializer typeSerializer = new ETypeSerializer() {

		@Override
		public void serialize(EClass value, JsonGenerator jg, SerializerProvider serializers) throws IOException {
			jg.writeStringField(getProperty(), value.getName());
		}

		@Override
		public String getProperty() {
			return "type";
		}
	};

	private ResourceSet resourceSet;
	private ObjectMapper mapper;

	@Before
	public void setUp() {
		mapper = new ObjectMapper();
		resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry()
				.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("*", new JsonResourceFactory(mapper));

		EMFModule module = new EMFModule();
		module.setIdSerializer(idSerializer);
		module.setTypeSerializer(typeSerializer);
		module.setReferenceSerializer(referenceSerializer);

		mapper.registerModule(module);
	}

	@Test
	public void testSerializers() {
		JsonNode expected = mapper.createArrayNode()
				.add(mapper.createObjectNode()
						.put("type", "User")
						.put("userId", "u1")
						.put("uniqueFriend", "u2"))
				.add(mapper.createObjectNode()
						.put("type", "User")
						.put("userId", "u2")
						.set("friends", mapper.createArrayNode().add("u1").add("u3")))
				.add(mapper.createObjectNode()
						.put("type", "User")
						.put("userId", "u3")
						.set("friends", mapper.createArrayNode().add("u1").add("u2")));

		User u1 = ModelFactory.eINSTANCE.createUser();
		u1.setUserId("u1");
		User u2 = ModelFactory.eINSTANCE.createUser();
		u2.setUserId("u2");
		User u3 = ModelFactory.eINSTANCE.createUser();
		u3.setUserId("u3");

		u1.setUniqueFriend(u2);
		u2.getFriends().add(u1);
		u2.getFriends().add(u3);
		u3.getFriends().add(u1);
		u3.getFriends().add(u2);

		Resource resource = resourceSet.createResource(URI.createURI("test"));
		resource.getContents().add(u1);
		resource.getContents().add(u2);
		resource.getContents().add(u3);

		JsonNode result = mapper.valueToTree(resource);

		assertEquals(expected, result);
	}

}
