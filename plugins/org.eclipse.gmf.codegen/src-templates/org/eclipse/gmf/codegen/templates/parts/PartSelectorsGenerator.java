package org.eclipse.gmf.codegen.templates.parts;

import java.util.*;
import org.eclipse.gmf.codegen.gmfgen.*;
import org.eclipse.gmf.codegen.util.*;
import org.eclipse.emf.ecore.*;

public class PartSelectorsGenerator
{
  protected static String nl;
  public static synchronized PartSelectorsGenerator create(String lineSeparator)
  {
    nl = lineSeparator;
    PartSelectorsGenerator result = new PartSelectorsGenerator();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";" + NL;
  protected final String TEXT_3 = NL + "import org.eclipse.emf.query.ocl.conditions.OclConstraintCondition;" + NL + "import org.eclipse.emf.ecore.EClass;" + NL + "import org.eclipse.emf.ecore.EObject;";
  protected final String TEXT_4 = NL + NL + "/**" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_5 = " {";
  protected final String TEXT_6 = NL + "\t/**" + NL + "\t* <pre>";
  protected final String TEXT_7 = "</pre>" + NL + "\t* @generated" + NL + "\t*/" + NL + "\tprivate static final ConditionElement cond_";
  protected final String TEXT_8 = " = new ConditionElement(\"";
  protected final String TEXT_9 = "\");";
  protected final String TEXT_10 = NL + NL + "\t/**" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate PartSelectors() {" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t* @generated\t" + NL + "\t*" + NL + "\t* @return allways returns <code>true</code>" + NL + "\t*/\t\t" + NL + "\tpublic static boolean ";
  protected final String TEXT_11 = "(Object element) {" + NL + "\t\treturn true;" + NL + "\t}" + NL + "\t\t";
  protected final String TEXT_12 = NL + "\t/**\t" + NL + "\t* <pre>";
  protected final String TEXT_13 = "</pre>" + NL + "\t* @generated" + NL + "\t*/" + NL + "\tpublic static boolean ";
  protected final String TEXT_14 = "(EObject contextInstance) {" + NL + "\t\treturn cond_";
  protected final String TEXT_15 = ".isSatisfied(contextInstance);" + NL + "\t}";
  protected final String TEXT_16 = NL + "\t";
  protected final String TEXT_17 = NL + "\t/**" + NL + "\t* @generated\t" + NL + "\t*/" + NL + "\tprivate static class ConditionElement {" + NL + "\t\t/**" + NL + "\t\t* @generated\t" + NL + "\t\t*/\t\t\t\t\t" + NL + "\t\tprivate EClass evalContext;" + NL + "\t\t/**" + NL + "\t\t* @generated\t" + NL + "\t\t*/\t\t\t\t\t\t" + NL + "\t\tprivate OclConstraintCondition condition;" + NL + "\t\t/**" + NL + "\t\t* @generated\t" + NL + "\t\t*/\t\t\t\t\t\t" + NL + "\t\tprivate String body;\t\t" + NL + "\t\t\t\t" + NL + "\t\t/**" + NL + "\t\t* @generated\t" + NL + "\t\t*/\t\t\t\t" + NL + "\t\tpublic ConditionElement(String expressionBody) {\t\t\t" + NL + "\t\t\tbody = expressionBody;" + NL + "\t\t}" + NL + "\t\t/**" + NL + "\t\t* @generated\t" + NL + "\t\t*/\t\t\t\t\t\t" + NL + "\t\tpublic boolean isSatisfied(EObject object) {\t\t" + NL + "\t\t\ttry {" + NL + "\t\t\t\tBoolean result = (object != null) ? evaluate(object) : Boolean.FALSE;" + NL + "\t\t\t\treturn result.booleanValue();" + NL + "\t\t\t} catch(IllegalArgumentException e) {" + NL + "\t\t\t\te.printStackTrace();" + NL + "\t\t\t\treturn false;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\t/**" + NL + "\t\t* @generated\t" + NL + "\t\t*/\t\t" + NL + "\t\tpublic Boolean evaluate(EObject context) {" + NL + "\t\t\tthis.evalContext = context.eClass();" + NL + "\t\t\tif(condition == null) {\t\t\t\t\t" + NL + "\t\t\t\tcondition = new OclConstraintCondition(body, evalContext);" + NL + "\t\t\t}" + NL + "\t\t\tif(condition != null) {" + NL + "\t\t\t\treturn booleanCast(condition.evaluate(context));" + NL + "\t\t\t}" + NL + "\t\t\treturn Boolean.FALSE;" + NL + "\t\t}" + NL + "\t\t/**" + NL + "\t\t* @generated\t" + NL + "\t\t*/\t\t\t\t\t" + NL + "\t\tprivate static Boolean booleanCast(Object value) {" + NL + "\t\t\tif(value == null) {" + NL + "\t\t\t\treturn null;" + NL + "\t\t\t} else if(value instanceof Boolean) {" + NL + "\t\t\t\t return (Boolean)value;\t" + NL + "\t\t\t}" + NL + "\t\t\t// illegal type " + NL + "\t\t\t// TODO - add log here " + NL + "\t\t\treturn Boolean.FALSE;" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_18 = "\t" + NL + "}";
  protected final String TEXT_19 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
GenDiagram genDiagram = (GenDiagram)argument;
ImportUtil importManager = new ImportUtil(genDiagram.getEditPartsPackageName());

    stringBuffer.append(TEXT_1);
    stringBuffer.append(PartSelectorUtil.getPartSelectorsPackageName(genDiagram));
    stringBuffer.append(TEXT_2);
    if(PartSelectorUtil.hasPartSelectors(genDiagram)){
    stringBuffer.append(TEXT_3);
    }
    importManager.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(PartSelectorUtil.getPartSelectorsClassName(genDiagram));
    stringBuffer.append(TEXT_5);
    
for(Iterator it = AccessUtil.getGenEntities(genDiagram); it.hasNext();) {
	EObject nextElement = (EObject)it.next();
	GenBaseElement  genElement = nextElement instanceof GenBaseElement ? (GenBaseElement) nextElement : null;
	if(genElement == null || !PartSelectorUtil.hasPartSelector(genElement)) continue;	
		
	ModelElementSelector selector = genElement.getModelElementSelector();			
	if(selector.getBody() == null) continue;
	
    stringBuffer.append(TEXT_6);
    stringBuffer.append(selector.getBody());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(PartSelectorUtil.getPartSelectorMethodName(genElement)
		);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(selector.getBody());
    stringBuffer.append(TEXT_9);
    }
    stringBuffer.append(TEXT_10);
    stringBuffer.append(PartSelectorUtil.ACCEPT_ALL_SELECTOR_NAME);
    stringBuffer.append(TEXT_11);
    
for(Iterator it = AccessUtil.getGenEntities(genDiagram); it.hasNext();) {
	EObject nextElement = (EObject)it.next();
	GenBaseElement  genElement = nextElement instanceof GenBaseElement ? (GenBaseElement) nextElement : null;
	if(genElement == null || !PartSelectorUtil.hasPartSelector(genElement)) continue;	
		
	ModelElementSelector selector = genElement.getModelElementSelector();			
	if(selector.getBody() == null) continue;
	
    stringBuffer.append(TEXT_12);
    stringBuffer.append(selector.getBody());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(PartSelectorUtil.getPartSelectorMethodName(genElement));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(PartSelectorUtil.getPartSelectorMethodName(genElement));
    stringBuffer.append(TEXT_15);
    
}
    stringBuffer.append(TEXT_16);
     if(PartSelectorUtil.hasPartSelectors(genDiagram)) {
    stringBuffer.append(TEXT_17);
     } 
    stringBuffer.append(TEXT_18);
    importManager.emitSortedImports();
    stringBuffer.append(TEXT_19);
    return stringBuffer.toString();
  }
}
