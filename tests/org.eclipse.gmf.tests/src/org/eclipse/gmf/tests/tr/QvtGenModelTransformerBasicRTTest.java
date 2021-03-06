/*
 * Copyright (c) 2005, 2008 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.tests.tr;

import org.eclipse.gmf.internal.bridge.genmodel.BasicDiagramRunTimeModelHelper;

public class QvtGenModelTransformerBasicRTTest extends QvtGenModelTransformerTest {

	public QvtGenModelTransformerBasicRTTest(String name) {
		super(name, new BasicDiagramRunTimeModelHelper());
	}
}
