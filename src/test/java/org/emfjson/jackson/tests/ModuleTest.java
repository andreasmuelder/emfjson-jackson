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
package org.emfjson.jackson.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emfjson.jackson.junit.model.ModelFactory;
import org.emfjson.jackson.junit.model.User;
import org.emfjson.jackson.module.EMFModule;
import org.emfjson.jackson.resource.JsonResource;
import org.emfjson.jackson.resource.JsonResourceFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ModuleTest {

	private final ObjectMapper mapper = new ObjectMapper();

	{
		mapper.registerModule(new EMFModule());
	}

	private ResourceSetImpl resourceSet;

	@Before
	public void setUp() {
		EPackage.Registry.INSTANCE
				.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);

		resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("*", new JsonResourceFactory(mapper));
	}

	@After
	public void tearDown() {
		EPackage.Registry.INSTANCE.clear();
	}

	@Test
	public void testSaveWithModule() throws JsonProcessingException {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "A");

		EClass c = EcoreFactory.eINSTANCE.createEClass();
		c.setName("A");

		assertEquals(expected, mapper.valueToTree(c));
	}

	@Test
	public void testReadWithModule() throws IOException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "A");

		EClass result = (EClass) mapper
				.reader()
				.withAttribute("resourceSet", resourceSet)
				.treeToValue(data, EObject.class);

		assertEquals("A", result.getName());
	}

	@Test
	public void testSaveResourceWithModule() throws JsonProcessingException {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "A");

		Resource r = new JsonResource(URI.createURI("test"), mapper);
		EClass c = EcoreFactory.eINSTANCE.createEClass();
		c.setName("A");
		r.getContents().add(c);

		assertEquals(expected, mapper.valueToTree(r));
	}

	@Test
	public void testReadResourceWithModule() throws IOException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "A");

		Resource result = mapper
				.reader()
				.withAttribute("resourceSet", resourceSet)
				.treeToValue(data, Resource.class);

		assertEquals(1, result.getContents().size());
		assertEquals(EcorePackage.Literals.ECLASS, result.getContents().get(0).eClass());
		assertEquals("A", ((EClass) result.getContents().get(0)).getName());
	}

	@Test
	public void testLoadIntoValue() throws IOException {
		JsonNode data = mapper.createObjectNode()
				.put("name", "A");

		User user = ModelFactory.eINSTANCE.createUser();
		mapper.readerForUpdating(user)
				.withAttribute("resourceSet", resourceSet)
				.treeToValue(data, EObject.class);

		assertEquals("A", user.getName());
	}

	@Test
	public void testLoadIntoResource() throws JsonProcessingException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "A");

		Resource resource = new JsonResource(URI.createURI("test"), mapper);

		mapper.readerForUpdating(resource)
				.withAttribute("resourceSet", resourceSet)
				.treeToValue(data, Resource.class);

		assertEquals(1, resource.getContents().size());

		EObject root = resource.getContents().get(0);

		assertEquals(EcorePackage.Literals.ECLASS, root.eClass());
		assertEquals("A", ((EClass) root).getName());
	}

}
