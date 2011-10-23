/*******************************************************************************
 * Copyright (c) 2011 Guillaume Hillairet.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Guillaume Hillairet - initial API and implementation
 *******************************************************************************/
package org.eclipselabs.emfjson.junit.suite;

import org.eclipselabs.emfjson.junit.tests.TestEmfJsAttributes;
import org.eclipselabs.emfjson.junit.tests.TestEmfJsReferences;
import org.eclipselabs.emfjson.junit.tests.TestURIHandlerReadBasics;
import org.eclipselabs.emfjson.junit.tests.TestURIHandlerSaveBasics;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	TestURIHandlerReadBasics.class, TestURIHandlerSaveBasics.class, 
	TestEmfJsReferences.class, TestEmfJsAttributes.class
})
public class TestEmfJsSuite {}
