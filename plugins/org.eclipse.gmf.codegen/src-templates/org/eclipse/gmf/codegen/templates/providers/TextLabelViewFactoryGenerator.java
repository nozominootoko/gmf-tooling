package org.eclipse.gmf.codegen.templates.providers;

import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.gmf.codegen.util.*;

public class TextLabelViewFactoryGenerator
{
  protected static String nl;
  public static synchronized TextLabelViewFactoryGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    TextLabelViewFactoryGenerator result = new TextLabelViewFactoryGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL;
  protected final String TEXT_3 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_4 = " extends ";
  protected final String TEXT_5 = " {" + NL + "" + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void decorateView(View containerView, View view, IAdaptable semanticAdapter, String semanticHint, int index, boolean persisted) {" + NL + "\t\tsuper.decorateView(containerView, view, semanticAdapter, semanticHint, index, persisted);";
  protected final String TEXT_6 = NL;
  protected final String TEXT_7 = "EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();" + NL + "annotation.setSource(\"VisualID\");" + NL + "view.getEAnnotations().add(annotation);" + NL + "annotation.getDetails().put(\"value\", \"";
  protected final String TEXT_8 = "\");" + NL + "\t}" + NL + "}";

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
GenLabel label = (GenLabel) argument;
GenDiagram diagram = label.getDiagram();
boolean isFlowLayout = label instanceof GenNodeLabel ? ((GenNodeLabel) label).getNode().getChildContainersPlacement() == CompartmentPlacementKind.FLOW_LITERAL : false;
ImportUtil importManager = new ImportUtil(diagram.getNotationViewFactoriesPackageName());

    stringBuffer.append(TEXT_1);
    stringBuffer.append(diagram.getNotationViewFactoriesPackageName());
    stringBuffer.append(TEXT_2);
    
importManager.markImportLocation(stringBuffer);
importManager.addImport("org.eclipse.core.runtime.IAdaptable");
importManager.addImport("org.eclipse.emf.ecore.EAnnotation");
importManager.addImport("org.eclipse.emf.ecore.EcoreFactory");
importManager.addImport("org.eclipse.gmf.runtime.notation.View");

    stringBuffer.append(TEXT_3);
    stringBuffer.append(label instanceof GenLinkLabel ? ((GenLinkLabel) label).getTextNotationViewFactoryClassName() : label.getNotationViewFactoryClassName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(isFlowLayout ? importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.view.factories.AbstractShapeViewFactory") : importManager.getImportedName("org.eclipse.gmf.runtime.diagram.ui.view.factories.BasicNodeViewFactory"));
    stringBuffer.append(TEXT_5);
    GenCommonBase genElement = label;
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genElement.getVisualID());
    stringBuffer.append(TEXT_8);
    importManager.emitSortedImports();
    return stringBuffer.toString();
  }
}
