/*
 * Copyright (c) 2006, 2008 Borland Software Corp.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Alexander Shatalin (Borland) - initial API and implementation
 */
package org.eclipse.gmf.ecore.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gmf.ecore.edit.parts.EAnnotation2EditPart;
import org.eclipse.gmf.ecore.edit.parts.EAnnotationDetailsEditPart;
import org.eclipse.gmf.ecore.edit.parts.EAnnotationEditPart;
import org.eclipse.gmf.ecore.edit.parts.EAnnotationSourceEditPart;
import org.eclipse.gmf.ecore.edit.parts.EAttributeEditPart;
import org.eclipse.gmf.ecore.edit.parts.EClass2EditPart;
import org.eclipse.gmf.ecore.edit.parts.EClassAttributesEditPart;
import org.eclipse.gmf.ecore.edit.parts.EClassClassAnnotationsEditPart;
import org.eclipse.gmf.ecore.edit.parts.EClassEditPart;
import org.eclipse.gmf.ecore.edit.parts.EClassNameEditPart;
import org.eclipse.gmf.ecore.edit.parts.EClassOperationsEditPart;
import org.eclipse.gmf.ecore.edit.parts.EDataType2EditPart;
import org.eclipse.gmf.ecore.edit.parts.EDataTypeDataTypeAnnotationsEditPart;
import org.eclipse.gmf.ecore.edit.parts.EDataTypeEditPart;
import org.eclipse.gmf.ecore.edit.parts.EDataTypeNameEditPart;
import org.eclipse.gmf.ecore.edit.parts.EEnum2EditPart;
import org.eclipse.gmf.ecore.edit.parts.EEnumEditPart;
import org.eclipse.gmf.ecore.edit.parts.EEnumEnumAnnotationsEditPart;
import org.eclipse.gmf.ecore.edit.parts.EEnumLiteralEditPart;
import org.eclipse.gmf.ecore.edit.parts.EEnumLiteralsEditPart;
import org.eclipse.gmf.ecore.edit.parts.EEnumNameEditPart;
import org.eclipse.gmf.ecore.edit.parts.EOperationEditPart;
import org.eclipse.gmf.ecore.edit.parts.EPackage2EditPart;
import org.eclipse.gmf.ecore.edit.parts.EPackage3EditPart;
import org.eclipse.gmf.ecore.edit.parts.EPackageClassesEditPart;
import org.eclipse.gmf.ecore.edit.parts.EPackageDataTypesEditPart;
import org.eclipse.gmf.ecore.edit.parts.EPackageEditPart;
import org.eclipse.gmf.ecore.edit.parts.EPackageEnumsEditPart;
import org.eclipse.gmf.ecore.edit.parts.EPackageNameEditPart;
import org.eclipse.gmf.ecore.edit.parts.EPackagePackageAnnotationsEditPart;
import org.eclipse.gmf.ecore.edit.parts.EPackagePackagesEditPart;
import org.eclipse.gmf.ecore.edit.parts.EReference2EditPart;
import org.eclipse.gmf.ecore.edit.parts.EReferenceEditPart;
import org.eclipse.gmf.ecore.edit.parts.EReferenceLowerBoundUpperBound2EditPart;
import org.eclipse.gmf.ecore.edit.parts.EReferenceLowerBoundUpperBoundEditPart;
import org.eclipse.gmf.ecore.edit.parts.EReferenceName2EditPart;
import org.eclipse.gmf.ecore.edit.parts.EReferenceNameEditPart;
import org.eclipse.gmf.ecore.edit.parts.EStringToStringMapEntryEditPart;
import org.eclipse.gmf.ecore.expressions.EcoreAbstractExpression;
import org.eclipse.gmf.ecore.expressions.EcoreOCLFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class EcoreVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "org.eclipse.gmf.ecore.editor/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (EPackageEditPart.MODEL_ID.equals(view.getType())) {
				return EPackageEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return org.eclipse.gmf.ecore.part.EcoreVisualIDRegistry.getVisualID(view.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
				EcoreDiagramEditorPlugin.getInstance().logError("Unable to parse view type as a visualID number: " + type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (EcorePackage.eINSTANCE.getEPackage().isSuperTypeOf(domainElement.eClass()) && isDiagram((EPackage) domainElement)) {
			return EPackageEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = org.eclipse.gmf.ecore.part.EcoreVisualIDRegistry.getModelID(containerView);
		if (!EPackageEditPart.MODEL_ID.equals(containerModelID) && !"Ecore".equals(containerModelID)) { //$NON-NLS-1$
			return -1;
		}
		int containerVisualID;
		if (EPackageEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.eclipse.gmf.ecore.part.EcoreVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = EPackageEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case EPackageEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEClass().isSuperTypeOf(domainElement.eClass())) {
				return EClassEditPart.VISUAL_ID;
			}
			if (EcorePackage.eINSTANCE.getEPackage().isSuperTypeOf(domainElement.eClass())) {
				return EPackage2EditPart.VISUAL_ID;
			}
			if (EcorePackage.eINSTANCE.getEAnnotation().isSuperTypeOf(domainElement.eClass())) {
				return EAnnotationEditPart.VISUAL_ID;
			}
			if (EcorePackage.eINSTANCE.getEDataType().isSuperTypeOf(domainElement.eClass()) && isEDataType_2004((EDataType) domainElement)) {
				return EDataTypeEditPart.VISUAL_ID;
			}
			if (EcorePackage.eINSTANCE.getEEnum().isSuperTypeOf(domainElement.eClass())) {
				return EEnumEditPart.VISUAL_ID;
			}
			break;
		case EClassAttributesEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEAttribute().isSuperTypeOf(domainElement.eClass())) {
				return EAttributeEditPart.VISUAL_ID;
			}
			break;
		case EClassOperationsEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEOperation().isSuperTypeOf(domainElement.eClass())) {
				return EOperationEditPart.VISUAL_ID;
			}
			break;
		case EClassClassAnnotationsEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEAnnotation().isSuperTypeOf(domainElement.eClass())) {
				return EAnnotation2EditPart.VISUAL_ID;
			}
			break;
		case EPackageClassesEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEClass().isSuperTypeOf(domainElement.eClass())) {
				return EClass2EditPart.VISUAL_ID;
			}
			break;
		case EPackagePackagesEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEPackage().isSuperTypeOf(domainElement.eClass())) {
				return EPackage3EditPart.VISUAL_ID;
			}
			break;
		case EPackageDataTypesEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEDataType().isSuperTypeOf(domainElement.eClass()) && isEDataType_3006((EDataType) domainElement)) {
				return EDataType2EditPart.VISUAL_ID;
			}
			break;
		case EPackageEnumsEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEEnum().isSuperTypeOf(domainElement.eClass())) {
				return EEnum2EditPart.VISUAL_ID;
			}
			break;
		case EPackagePackageAnnotationsEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEAnnotation().isSuperTypeOf(domainElement.eClass())) {
				return EAnnotation2EditPart.VISUAL_ID;
			}
			break;
		case EAnnotationDetailsEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEStringToStringMapEntry().isSuperTypeOf(domainElement.eClass())) {
				return EStringToStringMapEntryEditPart.VISUAL_ID;
			}
			break;
		case EDataTypeDataTypeAnnotationsEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEAnnotation().isSuperTypeOf(domainElement.eClass())) {
				return EAnnotation2EditPart.VISUAL_ID;
			}
			break;
		case EEnumLiteralsEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEEnumLiteral().isSuperTypeOf(domainElement.eClass())) {
				return EEnumLiteralEditPart.VISUAL_ID;
			}
			break;
		case EEnumEnumAnnotationsEditPart.VISUAL_ID:
			if (EcorePackage.eINSTANCE.getEAnnotation().isSuperTypeOf(domainElement.eClass())) {
				return EAnnotation2EditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = org.eclipse.gmf.ecore.part.EcoreVisualIDRegistry.getModelID(containerView);
		if (!EPackageEditPart.MODEL_ID.equals(containerModelID) && !"Ecore".equals(containerModelID)) { //$NON-NLS-1$
			return false;
		}
		int containerVisualID;
		if (EPackageEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.eclipse.gmf.ecore.part.EcoreVisualIDRegistry.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = EPackageEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case EPackageEditPart.VISUAL_ID:
			if (EClassEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EPackage2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EAnnotationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EDataTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EEnumEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EClassEditPart.VISUAL_ID:
			if (EClassNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EClassAttributesEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EClassOperationsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EClassClassAnnotationsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EPackage2EditPart.VISUAL_ID:
			if (EPackageNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EPackageClassesEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EPackagePackagesEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EPackageDataTypesEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EPackageEnumsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EPackagePackageAnnotationsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EAnnotationEditPart.VISUAL_ID:
			if (EAnnotationSourceEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EAnnotationDetailsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EDataTypeEditPart.VISUAL_ID:
			if (EDataTypeNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EDataTypeDataTypeAnnotationsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EEnumEditPart.VISUAL_ID:
			if (EEnumNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EEnumLiteralsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EEnumEnumAnnotationsEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EClassAttributesEditPart.VISUAL_ID:
			if (EAttributeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EClassOperationsEditPart.VISUAL_ID:
			if (EOperationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EClassClassAnnotationsEditPart.VISUAL_ID:
			if (EAnnotation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EPackageClassesEditPart.VISUAL_ID:
			if (EClass2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EPackagePackagesEditPart.VISUAL_ID:
			if (EPackage3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EPackageDataTypesEditPart.VISUAL_ID:
			if (EDataType2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EPackageEnumsEditPart.VISUAL_ID:
			if (EEnum2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EPackagePackageAnnotationsEditPart.VISUAL_ID:
			if (EAnnotation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EAnnotationDetailsEditPart.VISUAL_ID:
			if (EStringToStringMapEntryEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EDataTypeDataTypeAnnotationsEditPart.VISUAL_ID:
			if (EAnnotation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EEnumLiteralsEditPart.VISUAL_ID:
			if (EEnumLiteralEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EEnumEnumAnnotationsEditPart.VISUAL_ID:
			if (EAnnotation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EReferenceEditPart.VISUAL_ID:
			if (EReferenceNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EReferenceLowerBoundUpperBoundEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EReference2EditPart.VISUAL_ID:
			if (EReferenceName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EReferenceLowerBoundUpperBound2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (EcorePackage.eINSTANCE.getEReference().isSuperTypeOf(domainElement.eClass()) && isEReference_4002((EReference) domainElement)) {
			return EReferenceEditPart.VISUAL_ID;
		}
		if (EcorePackage.eINSTANCE.getEReference().isSuperTypeOf(domainElement.eClass()) && isEReference_4003((EReference) domainElement)) {
			return EReference2EditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(EPackage element) {
		return true;
	}

	/**
	 * @generated
	 */
	private static boolean isEDataType_2004(EDataType domainElement) {
		Object result = EcoreOCLFactory.getExpression(2, EcorePackage.eINSTANCE.getEDataType(), null).evaluate(domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isEDataType_3006(EDataType domainElement) {
		Object result = EcoreOCLFactory.getExpression(1, EcorePackage.eINSTANCE.getEDataType(), null).evaluate(domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isEReference_4002(EReference domainElement) {
		Object result = EcoreOCLFactory.getExpression(3, EcorePackage.eINSTANCE.getEReference(), null).evaluate(domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

	/**
	 * @generated
	 */
	private static boolean isEReference_4003(EReference domainElement) {
		Object result = EcoreOCLFactory.getExpression(5, EcorePackage.eINSTANCE.getEReference(), null).evaluate(domainElement);
		return result instanceof Boolean && ((Boolean) result).booleanValue();
	}

}
