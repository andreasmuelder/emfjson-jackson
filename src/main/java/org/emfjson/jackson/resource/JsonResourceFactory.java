/*
 * Copyright (c) 2015 Guillaume Hillairet.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Guillaume Hillairet - initial API and implementation
 */
package org.emfjson.jackson.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.emfjson.jackson.module.EMFModule;

/**
 * An implementation of Resource Factory that creates JsonResource.
 */
public class JsonResourceFactory extends ResourceFactoryImpl {

	private final ObjectMapper mapper;

	public JsonResourceFactory(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	public JsonResourceFactory() {
		this.mapper = new ObjectMapper();
		this.mapper.registerModule(new EMFModule());
	}

	@Override
	public Resource createResource(URI uri) {
		final JsonResource resource = new JsonResource(uri);
		resource.setObjectMapper(mapper);
		return resource;
	}

}
