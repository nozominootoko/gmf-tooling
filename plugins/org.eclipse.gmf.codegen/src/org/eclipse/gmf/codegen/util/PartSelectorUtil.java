/*
 * Copyright (c) 2005 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Radek Dvorak (Borland) - initial API and implementation
 */
package org.eclipse.gmf.codegen.util;

import java.util.Iterator;

import org.eclipse.gmf.codegen.gmfgen.GenBaseElement;
import org.eclipse.gmf.codegen.gmfgen.GenCommonBase;
import org.eclipse.gmf.codegen.gmfgen.GenDiagram;

/**
 * Utility class for part selectors
 * 
 * @author dvorak  
 */
public class PartSelectorUtil {
	public static final String ACCEPT_ALL_SELECTOR_NAME = "acceptAllSelector"; //$NON-NLS-1$
	
	private static final String SELECTORS_CLASS_NAME = "PartSelectors"; //$NON-NLS-1$
	private static final String EMF_QUERY_OCL_PLUGIN_ID = "org.eclipse.emf.query.ocl"; //$NON-NLS-1$

	/**
	 * Gets part selector method name for the given element.
	 * 
	 * @param genElement the element to get selector from
	 * @return method name or <code>null</code> if no selector is available for this element
	 */
	public static String getPartSelectorMethodName(GenBaseElement genElement) {
		if(genElement.getModelElementSelector() != null) {
			return "matches_" + genElement.getVisualID(); //$NON-NLS-1$
		}
		return PartSelectorUtil.ACCEPT_ALL_SELECTOR_NAME;
	}

	/**
	 * Answers whether the given element has part selector to generate
	 * @param genElement the gen model element
	 * @return <code>true</code> if the element has selector to generate,
	 * <code>false</code> otherwise 	
	 */	
	public static boolean hasPartSelector(GenBaseElement genElement) {
		return genElement.getModelElementSelector() != null;
	}
	
	/**
	 * Answers whether the given diagram contains part selectors to generate
	 * @param genDiagram 
	 * @return <code>true</code> if there is at least one selector to generate,
	 * <code>false</code> otherwise 	
	 */
	public static boolean hasPartSelectors(GenDiagram genDiagram) {
		for (Iterator it = AccessUtil.getGenEntities(genDiagram); it.hasNext();) {
			GenCommonBase nextBase = (GenCommonBase) it.next();
			GenBaseElement genBaseElement = nextBase instanceof GenBaseElement ? (GenBaseElement)nextBase : null;
			if(genBaseElement != null && hasPartSelector(genBaseElement)) {
				return true;
			}
		}
		return false; 
	}
	
	/** 
	 * @param genDiagram gen model diagram element
	 * @return part selectors class name
	 */		
	public static String getPartSelectorsClassName(GenDiagram genDiagram) {
		return SELECTORS_CLASS_NAME; 		
	}
	
	/** 
	 * @param genDiagram gen model diagram element
	 * @return part selectors full class name
	 */	
	public static String getPartSelectorsFullClassName(GenDiagram genDiagram) {
		return getPartSelectorsPackageName(genDiagram) + "." + getPartSelectorsClassName(genDiagram); //$NON-NLS-1$		
	}
	
	/** 
	 * @param genDiagram gen model diagram element
	 * @return part selectors class package name
	 */
	public static String getPartSelectorsPackageName(GenDiagram genDiagram) {		
		return genDiagram.getEditPartsPackageName(); //$NON-NLS-1$		
	}
	
	
	/** 
	 * @param genDiagram gen model diagram element
	 * @return required plugins used in generated part selectors class.
	 */		
	public static String[] getRequiredPlugins(GenDiagram genDiagram) {
		if(hasPartSelectors(genDiagram)) {
			return new String[] { EMF_QUERY_OCL_PLUGIN_ID };
		} else {
			return new String[0];
		}
	}
}
