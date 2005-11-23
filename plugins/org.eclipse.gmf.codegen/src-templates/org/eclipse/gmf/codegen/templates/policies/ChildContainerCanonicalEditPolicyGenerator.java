package org.eclipse.gmf.codegen.templates.policies;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.gmf.codegen.util.*;

public class ChildContainerCanonicalEditPolicyGenerator {
 
  protected static String nl;
  public static synchronized ChildContainerCanonicalEditPolicyGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    ChildContainerCanonicalEditPolicyGenerator result = new ChildContainerCanonicalEditPolicyGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL;
  protected final String TEXT_3 = NL + "import org.eclipse.core.runtime.IAdaptable;" + NL + "import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;" + NL + "import org.eclipse.gmf.runtime.notation.View;";
  protected final String TEXT_4 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_5 = " extends CanonicalEditPolicy {";
  protected final String TEXT_6 = NL;
  protected final String TEXT_7 = NL + "\t\t/**" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected ";
  protected final String TEXT_8 = " getSemanticChildrenList() {\t" + NL + "\t\t\t";
  protected final String TEXT_9 = " modelElement = (";
  protected final String TEXT_10 = ") ((View) getHost().getModel()).getElement();" + NL + "\t\t\tList result = new ";
  protected final String TEXT_11 = "();" + NL + "\t\t\t";
  protected final String TEXT_12 = " nextValue;";
  protected final String TEXT_13 = "\t" + NL + "\t\t\tfor (";
  protected final String TEXT_14 = " it = ";
  protected final String TEXT_15 = ".iterator(); it.hasNext();) {" + NL + "\t\t\t\tnextValue = (";
  protected final String TEXT_16 = ") it.next();";
  protected final String TEXT_17 = NL + "\t\t\tnextValue = ";
  protected final String TEXT_18 = ";";
  protected final String TEXT_19 = NL + "\t\t\tint nodeVID = ";
  protected final String TEXT_20 = ".INSTANCE.getNodeVisualID((View) getHost().getModel(), nextValue, \"\");" + NL + "\t\t\tif (";
  protected final String TEXT_21 = " == nodeVID) {" + NL + "\t\t\t\tresult.add(nextValue);" + NL + "\t\t\t}";
  protected final String TEXT_22 = NL + "\t\t\t}";
  protected final String TEXT_23 = "\t\t\t" + NL + "\t\t\treturn result;" + NL + "\t\t}";
  protected final String TEXT_24 = NL + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected String getFactoryHint(IAdaptable elementAdapter) {" + NL + "\t\treturn \"\";" + NL + "\t}" + NL + "}";
  protected final String TEXT_25 = NL;

	protected final String getFeatureValueGetter(String containerName, GenFeature feature, boolean isContainerEObject, ImportUtil importManager) {
		StringBuffer result = new StringBuffer();
		if (feature.getGenClass().isExternalInterface()) {
// Using EMF reflective method to access feature value
			result.append("((");
			if (feature.isListType()) {
				result.append(importManager.getImportedName("java.util.Collection"));
			} else {
				result.append(importManager.getImportedName(feature.getTypeGenClass().getQualifiedInterfaceName()));
			}
			result.append(")");
			if (!isContainerEObject) {
// Casting container to EObject - ExternalIntarfce could be not an instance of EObject
				result.append("((");
				result.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
				result.append(")");
			}
			result.append(containerName);
			if (!isContainerEObject) {
				result.append(")");
			}
			result.append(".eGet(");
			result.append(importManager.getImportedName(feature.getGenPackage().getQualifiedPackageInterfaceName()));
			result.append(".eINSTANCE.get");
			result.append(feature.getFeatureAccessorName());
			result.append("()))");
		} else {
			if (isContainerEObject) {
// Casting container to the typed interface
				result.append("((");
				result.append(importManager.getImportedName(feature.getGenClass().getQualifiedInterfaceName()));
				result.append(")");
			}
			result.append(containerName);
			if (isContainerEObject) {
				result.append(")");
			}
			result.append(".");
			result.append(feature.getGetAccessor());
			result.append("()");
		}
		return result.toString();
	}
	
	protected final String getFeatureValueSetterPrefix(String containerName, GenFeature feature, boolean isContainerEObject, ImportUtil importManager) {
		StringBuffer result = new StringBuffer();
		if (feature.getGenClass().isExternalInterface()) {
// Using EMF reflective method to access feature value
			if (!isContainerEObject) {
// Casting container to EObject - ExternalIntarfce could be not an instance of EObject
				result.append("((");
				result.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
				result.append(")");
			}
			result.append(containerName);
			if (!isContainerEObject) {
				result.append(")");
			}
			result.append(".eSet(");
			result.append(importManager.getImportedName(feature.getGenPackage().getQualifiedPackageInterfaceName()));
			result.append(".eINSTANCE.get");
			result.append(feature.getFeatureAccessorName());
			result.append("(), ");
		} else {
			if (isContainerEObject) {
// Casting container to the typed interface
				result.append("((");
				result.append(importManager.getImportedName(feature.getGenClass().getQualifiedInterfaceName()));
				result.append(")");
			}
			result.append(containerName);
			if (isContainerEObject) {
				result.append(")");
			}
			result.append(".set");
			result.append(feature.getAccessorName());
			result.append("(");
		}
		return result.toString();
	}
 
	public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
GenChildContainer genContainer = (GenChildContainer) argument;
GenDiagram genDiagram = genContainer.getDiagram();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(genDiagram.getEditPoliciesPackageName());
    stringBuffer.append(TEXT_2);
    ImportUtil importManager = new ImportUtil(genDiagram.getEditPoliciesPackageName());
    stringBuffer.append(TEXT_3);
    importManager.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genContainer.getCanonicalEditPolicyClassName());
    stringBuffer.append(TEXT_5);
    
{
	Collection genNodes = genContainer.getChildNodes();
	GenNode containerNode;
	if (genContainer instanceof GenCompartment) {
		containerNode = ((GenCompartment) genContainer).getNode();
	} else if (genContainer instanceof GenNode) {
		containerNode = (GenNode) genContainer;
	} else {
		throw new IllegalArgumentException(String.valueOf(genContainer));
	}
	String modelElementInterfaceName = importManager.getImportedName(containerNode.getModelFacet().getMetaClass().getQualifiedInterfaceName());

    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(importManager.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(modelElementInterfaceName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(modelElementInterfaceName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(importManager.getImportedName("java.util.LinkedList"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_12);
    
for (Iterator it = genNodes.iterator(); it.hasNext();) {
	GenNode nextNode = (GenNode) it.next();
	TypeModelFacet typeModelFacet = nextNode.getModelFacet();
	GenFeature childMetaFeature = typeModelFacet.getChildMetaFeature();
	if (childMetaFeature.isListType()) {

    stringBuffer.append(TEXT_13);
    stringBuffer.append(importManager.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(getFeatureValueGetter("modelElement", childMetaFeature, false, importManager));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(importManager.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_16);
    
	} else {

    stringBuffer.append(TEXT_17);
    stringBuffer.append(getFeatureValueGetter("modelElement", childMetaFeature, false, importManager));
    stringBuffer.append(TEXT_18);
    
	}

    stringBuffer.append(TEXT_19);
    stringBuffer.append(importManager.getImportedName(genDiagram.getVisualIDRegistryQualifiedClassName()));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(nextNode.getVisualID());
    stringBuffer.append(TEXT_21);
    
	if (childMetaFeature.isListType()) {

    stringBuffer.append(TEXT_22);
    
	}
}

    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    importManager.emitSortedImports();
    stringBuffer.append(TEXT_25);
    return stringBuffer.toString();
  }
}