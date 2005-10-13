package org.eclipse.gmf.codegen.templates.editor;

import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.gmf.codegen.util.*;
import java.util.*;

public class PluginXML
{
  protected static String nl;
  public static synchronized PluginXML create(String lineSeparator)
  {
    nl = lineSeparator;
    PluginXML result = new PluginXML();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<?eclipse version=\"3.0\"?>" + NL + "<plugin" + NL + "    name=\"";
  protected final String TEXT_2 = "\"" + NL + "    id=\"";
  protected final String TEXT_3 = "\"" + NL + "    version=\"1.0.0\"" + NL + "    class=\"";
  protected final String TEXT_4 = "\"" + NL + "    provider-name = \"";
  protected final String TEXT_5 = "\">" + NL + "" + NL + "  <requires>" + NL + "    <import plugin=\"org.eclipse.core.runtime\"/>" + NL + "    <import plugin=\"org.eclipse.core.resources\"/>" + NL + "    <import plugin=\"org.eclipse.jface\"/>" + NL + "    <import plugin=\"org.eclipse.ui.ide\"/>" + NL + "    <import plugin=\"org.eclipse.ui.views\"/>" + NL + "    <import plugin=\"org.eclipse.ui.workbench\"/>" + NL + "    <import plugin=\"org.eclipse.emf.ecore\"/>";
  protected final String TEXT_6 = NL + "    <import plugin=\"org.eclipse.emf.edit.ui\"/>" + NL + "    <import plugin=\"org.eclipse.gef\" export=\"true\"/>" + NL + "    <import plugin=\"org.eclipse.gmf.runtime.emf.commands.core\"/>" + NL + "    <import plugin=\"org.eclipse.gmf.runtime.diagram.ui\"/>" + NL + "    <import plugin=\"org.eclipse.gmf.runtime.diagram.ui.resources.editor\"/>" + NL + "    <import plugin=\"org.eclipse.gmf.runtime.diagram.ui.resources.editor.ide\"/>";
  protected final String TEXT_7 = NL + "    <import plugin=\"";
  protected final String TEXT_8 = "\" export=\"true\"/>";
  protected final String TEXT_9 = NL + "  </requires>" + NL + "" + NL + "  <runtime>" + NL + "    <library name=\"plugin.jar\">" + NL + "      <export name=\"*\"/>" + NL + "    </library>" + NL + "  </runtime>" + NL + "" + NL + "   <extension point=\"org.eclipse.core.runtime.preferences\">" + NL + "      <initializer class=\"";
  protected final String TEXT_10 = ".PreferencesInitializer\"/>" + NL + "   </extension>" + NL + "" + NL + "  <extension point=\"org.eclipse.team.core.fileTypes\">" + NL + "      <fileTypes" + NL + "         type=\"text\"" + NL + "         extension=\"";
  protected final String TEXT_11 = "\">" + NL + "      </fileTypes>" + NL + "  </extension>" + NL + "" + NL + "   <extension point=\"org.eclipse.emf.ecore.extension_parser\">" + NL + "      <parser" + NL + "         type=\"";
  protected final String TEXT_12 = "\"" + NL + "         class=\"org.eclipse.gmf.runtime.emf.core.resources.MResourceFactory\">" + NL + "      </parser>" + NL + "   </extension>" + NL + "" + NL + "   <extension point=\"org.eclipse.ui.editors\">" + NL + "     <editor" + NL + "        id=\"";
  protected final String TEXT_13 = "ID\"" + NL + "        name=\"";
  protected final String TEXT_14 = " Diagram Editor\"" + NL + "        icon=\"icons/full/obj16/";
  protected final String TEXT_15 = "ModelFile.gif\"" + NL + "        extensions=\"";
  protected final String TEXT_16 = "\"" + NL + "        default=\"true\"" + NL + "        matchingStrategy=\"";
  protected final String TEXT_17 = ".MDiagramEditorMatchingStrategy\"" + NL + "        class=\"";
  protected final String TEXT_18 = ".";
  protected final String TEXT_19 = "\"" + NL + "        contributorClass=\"org.eclipse.gmf.runtime.diagram.ui.internal.parts.MinimalActionBarContributor\">" + NL + "     </editor>" + NL + "   </extension>" + NL + "" + NL + "   <extension point=\"org.eclipse.ui.newWizards\">" + NL + "  \t  <wizard" + NL + "  \t     name=\"";
  protected final String TEXT_20 = " Diagram\"" + NL + "  \t     icon=\"icons/full/obj16/";
  protected final String TEXT_21 = "ModelFile.gif\"" + NL + "  \t     category=\"org.eclipse.ui.Examples\"" + NL + "  \t     class=\"";
  protected final String TEXT_22 = ".CreationWizard\"" + NL + "  \t     id=\"";
  protected final String TEXT_23 = ".CreationWizardID\">" + NL + "  \t  \t <description>" + NL + "  \t  \t\tCreates ";
  protected final String TEXT_24 = " diagram." + NL + "  \t  \t </description>  " + NL + "      </wizard>" + NL + "   </extension>" + NL + "" + NL + "   <extension point=\"org.eclipse.gmf.runtime.diagram.core.viewProviders\">" + NL + "      <viewProvider class=\"";
  protected final String TEXT_25 = ".ViewProvider\">" + NL + "         <Priority name=\"Lowest\"/>" + NL + "         <context viewClass=\"org.eclipse.gmf.runtime.notation.Diagram\" semanticHints=\"";
  protected final String TEXT_26 = "\"/>" + NL + "         <context viewClass=\"org.eclipse.gmf.runtime.notation.Node\" semanticHints=\"\"/>" + NL + "         <context viewClass=\"org.eclipse.gmf.runtime.notation.Edge\" semanticHints=\"\"/>" + NL + "      </viewProvider>" + NL + "   </extension>" + NL + "" + NL + "   <extension point=\"org.eclipse.gmf.runtime.diagram.ui.editpartProviders\">" + NL + "      <editpartProvider class=\"";
  protected final String TEXT_27 = ".EditPartProvider\">" + NL + "         <Priority name=\"Lowest\"/>" + NL + "      </editpartProvider>" + NL + "   </extension>" + NL + "" + NL + "</plugin>";
  protected final String TEXT_28 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    GenDiagram genDiagram = (GenDiagram) argument;
    GenModel genModel = genDiagram.getEMFGenModel();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(genDiagram.getPluginName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(genDiagram.getPluginID());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genDiagram.getPluginQualifiedClassName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genDiagram.getProviderName());
    stringBuffer.append(TEXT_5);
    
	// XXX org.eclipse.emf.edit.ui is for ExtendedImageRegistry. 
	// FIXME move image registry to plugin class code

    stringBuffer.append(TEXT_6);
    
Set requiredPluginIDs = new LinkedHashSet();
requiredPluginIDs.add(genModel.getModelPluginID());
requiredPluginIDs.add(genModel.getEditPluginID());

for (Iterator it = genModel.getAllUsedGenPackagesWithClassifiers().iterator(); it.hasNext();) {
	GenModel nextGenModel = ((GenPackage) it.next()).getGenModel();
	if (nextGenModel.hasEditSupport()) {
		requiredPluginIDs.add(nextGenModel.getEditPluginID());
	}
}

String[] requiredPlugins = genDiagram.getRequiredPluginIDs();
if (requiredPlugins == null) {
	requiredPlugins = new String[0];
}
requiredPluginIDs.addAll(Arrays.asList(requiredPlugins));

if(PartSelectorUtil.hasPartSelectors(genDiagram)) {
	requiredPluginIDs.addAll(Arrays.asList(PartSelectorUtil.getRequiredPlugins(genDiagram)));
}

for (Iterator it = requiredPluginIDs.iterator(); it.hasNext();) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(it.next());
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genDiagram.getEditorPackageName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genDiagram.getDiagramFileExtension());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genDiagram.getDiagramFileExtension());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genDiagram.getEditorQualifiedClassName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genDiagram.getDiagramFileExtension());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genDiagram.getEditorPackageName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genDiagram.getEditorPackageName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genDiagram.getEditorClassName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genDiagram.getEditorPackageName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genDiagram.getEditorPackageName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genDiagram.getProvidersPackageName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genDiagram.getProvidersPackageName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_28);
    return stringBuffer.toString();
  }
}
