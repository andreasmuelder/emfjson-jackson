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
package com.emfjson.model.search;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Result</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.emfjson.model.search.Result#getId <em>Id</em>}</li>
 *   <li>{@link com.emfjson.model.search.Result#getText <em>Text</em>}</li>
 *   <li>{@link com.emfjson.model.search.Result#getToUserId <em>To User Id</em>}</li>
 *   <li>{@link com.emfjson.model.search.Result#getToUser <em>To User</em>}</li>
 *   <li>{@link com.emfjson.model.search.Result#getFromUser <em>From User</em>}</li>
 *   <li>{@link com.emfjson.model.search.Result#getMetadata <em>Metadata</em>}</li>
 *   <li>{@link com.emfjson.model.search.Result#getSinceId <em>Since Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.emfjson.model.search.SearchPackage#getResult()
 * @model annotation="JSON root='true' element='results'"
 * @generated
 */
public interface Result extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see com.emfjson.model.search.SearchPackage#getResult_Id()
	 * @model
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link com.emfjson.model.search.Result#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>To User Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To User Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To User Id</em>' attribute.
	 * @see #setToUserId(String)
	 * @see com.emfjson.model.search.SearchPackage#getResult_ToUserId()
	 * @model annotation="JSON element='to_user_id'"
	 * @generated
	 */
	String getToUserId();

	/**
	 * Sets the value of the '{@link com.emfjson.model.search.Result#getToUserId <em>To User Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To User Id</em>' attribute.
	 * @see #getToUserId()
	 * @generated
	 */
	void setToUserId(String value);

	/**
	 * Returns the value of the '<em><b>To User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To User</em>' attribute.
	 * @see #setToUser(String)
	 * @see com.emfjson.model.search.SearchPackage#getResult_ToUser()
	 * @model annotation="JSON element='to_user'"
	 * @generated
	 */
	String getToUser();

	/**
	 * Sets the value of the '{@link com.emfjson.model.search.Result#getToUser <em>To User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To User</em>' attribute.
	 * @see #getToUser()
	 * @generated
	 */
	void setToUser(String value);

	/**
	 * Returns the value of the '<em><b>From User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From User</em>' attribute.
	 * @see #setFromUser(String)
	 * @see com.emfjson.model.search.SearchPackage#getResult_FromUser()
	 * @model annotation="JSON element='from_user'"
	 * @generated
	 */
	String getFromUser();

	/**
	 * Sets the value of the '{@link com.emfjson.model.search.Result#getFromUser <em>From User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From User</em>' attribute.
	 * @see #getFromUser()
	 * @generated
	 */
	void setFromUser(String value);

	/**
	 * Returns the value of the '<em><b>Metadata</b></em>' containment reference list.
	 * The list contents are of type {@link com.emfjson.model.search.Metadata}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metadata</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metadata</em>' containment reference list.
	 * @see com.emfjson.model.search.SearchPackage#getResult_Metadata()
	 * @model containment="true"
	 * @generated
	 */
	EList<Metadata> getMetadata();

	/**
	 * Returns the value of the '<em><b>Since Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Since Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Since Id</em>' attribute.
	 * @see #setSinceId(String)
	 * @see com.emfjson.model.search.SearchPackage#getResult_SinceId()
	 * @model annotation="JSON element='since_id'"
	 * @generated
	 */
	String getSinceId();

	/**
	 * Sets the value of the '{@link com.emfjson.model.search.Result#getSinceId <em>Since Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Since Id</em>' attribute.
	 * @see #getSinceId()
	 * @generated
	 */
	void setSinceId(String value);

	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see com.emfjson.model.search.SearchPackage#getResult_Text()
	 * @model
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link com.emfjson.model.search.Result#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

} // Result
