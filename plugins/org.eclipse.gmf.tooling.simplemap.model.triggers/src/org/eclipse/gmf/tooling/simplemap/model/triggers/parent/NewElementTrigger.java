/**
 * Copyright (c) 2010-2012 ISBAN S.L
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 * 		Ruben De Dios (ISBAN S.L)
 * 		Andrez Alvarez Mattos (ISBAN S.L)
 */
package org.eclipse.gmf.tooling.simplemap.model.triggers.parent;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.gmfgraph.Canvas;
import org.eclipse.gmf.gmfgraph.DiagramLabel;
import org.eclipse.gmf.gmfgraph.Figure;
import org.eclipse.gmf.gmfgraph.Label;
import org.eclipse.gmf.tooldef.CreationTool;
import org.eclipse.gmf.tooldef.DefaultImage;
import org.eclipse.gmf.tooldef.GMFToolFactory;
import org.eclipse.gmf.tooldef.Palette;
import org.eclipse.gmf.tooling.simplemap.model.triggers.AbstractTrigger;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimpleChildNode;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimpleCompartment;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimpleLabelNode;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimpleLinkMapping;
import org.eclipse.gmf.tooling.simplemap.simplemappings.SimpleNode;

public abstract class NewElementTrigger extends AbstractTrigger {

	private Canvas canvas;

	private Palette palette;

	protected CanvasFactory canvasFactory;

	public NewElementTrigger(TransactionalEditingDomain domain, SimpleChildNode newNode) {
		super(domain);

		this.canvas = newNode.getParentMapping().getCanvas();
		this.palette = newNode.getParentMapping().getPalette();

		canvasFactory = new CanvasFactory(canvas);

	}

	/**
	 * Creamos una nueva herramienta para el nuevo nodo
	 * @return
	 */
	protected CreationTool createNewTool() {
		CreationTool newCreationTool = GMFToolFactory.eINSTANCE.createCreationTool();
		DefaultImage smallIcon = GMFToolFactory.eINSTANCE.createDefaultImage();
		DefaultImage largeIcon = GMFToolFactory.eINSTANCE.createDefaultImage();

		newCreationTool.setSmallIcon(smallIcon);
		newCreationTool.setLargeIcon(largeIcon);

		palette.getTools().add(newCreationTool);

		return newCreationTool;
	}

	protected Label getLabelFigure(Object selected) {
		Figure figure = null;

		if (selected instanceof SimpleNode) {
			DiagramLabel diagramLabel = ((SimpleNode) selected).getDiagramLabel();
			if (diagramLabel != null && diagramLabel.getAccessor() != null)
				figure = diagramLabel.getAccessor().getFigure();
		}

		if (selected instanceof SimpleLabelNode) {
			figure = ((SimpleNode) selected).getDiagramLabel().getFigure().getActualFigure();
		}

		if (selected instanceof SimpleCompartment) {
			figure = getLabelFigure(((SimpleCompartment) selected).getParent());
		}

		if (selected instanceof SimpleLinkMapping) {
			figure = ((SimpleLinkMapping) selected).getDiagramLabel().getFigure().getActualFigure();

		}

		return (Label) figure;
	}

}
