/*
 *  Copyright (c) 2006, 2009 Borland Software Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *      Borland Software Corporation - initial API and implementation
 */
package org.eclipse.gmf.graphdef.editor.providers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gmf.graphdef.editor.edit.parts.CanvasEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.CompartmentEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.ConnectionEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.DiagramLabelEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Ellipse2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Ellipse3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.EllipseEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureDescriptorEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.FigureGalleryFiguresEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Label2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Label3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.LabelEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.NodeEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Polygon2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Polygon3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.PolygonEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Polyline2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Polyline3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.PolylineEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Rectangle2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.Rectangle3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RectangleEditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RoundedRectangle2EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RoundedRectangle3EditPart;
import org.eclipse.gmf.graphdef.editor.edit.parts.RoundedRectangleEditPart;
import org.eclipse.gmf.graphdef.editor.part.GMFGraphDiagramEditorPlugin;
import org.eclipse.gmf.graphdef.editor.part.Messages;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.ModelingAssistantProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

/**
 * @generated
 */
public class GMFGraphModelingAssistantProvider extends ModelingAssistantProvider {

	/**
	 * @generated
	 */
	public List getTypesForPopupBar(IAdaptable host) {
		IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
		if (editPart instanceof FigureDescriptorEditPart) {
			ArrayList types = new ArrayList(6);
			types.add(GMFGraphElementTypes.Rectangle_3010);
			types.add(GMFGraphElementTypes.Ellipse_3015);
			types.add(GMFGraphElementTypes.RoundedRectangle_3016);
			types.add(GMFGraphElementTypes.Polyline_3017);
			types.add(GMFGraphElementTypes.Polygon_3024);
			types.add(GMFGraphElementTypes.Label_3027);
			return types;
		}
		if (editPart instanceof RectangleEditPart) {
			ArrayList types = new ArrayList(6);
			types.add(GMFGraphElementTypes.Rectangle_3011);
			types.add(GMFGraphElementTypes.Ellipse_3012);
			types.add(GMFGraphElementTypes.RoundedRectangle_3013);
			types.add(GMFGraphElementTypes.Polyline_3014);
			types.add(GMFGraphElementTypes.Polygon_3023);
			types.add(GMFGraphElementTypes.Label_3026);
			return types;
		}
		if (editPart instanceof Rectangle2EditPart) {
			ArrayList types = new ArrayList(6);
			types.add(GMFGraphElementTypes.Rectangle_3011);
			types.add(GMFGraphElementTypes.Ellipse_3012);
			types.add(GMFGraphElementTypes.RoundedRectangle_3013);
			types.add(GMFGraphElementTypes.Polyline_3014);
			types.add(GMFGraphElementTypes.Polygon_3023);
			types.add(GMFGraphElementTypes.Label_3026);
			return types;
		}
		if (editPart instanceof EllipseEditPart) {
			ArrayList types = new ArrayList(6);
			types.add(GMFGraphElementTypes.Rectangle_3011);
			types.add(GMFGraphElementTypes.Ellipse_3012);
			types.add(GMFGraphElementTypes.RoundedRectangle_3013);
			types.add(GMFGraphElementTypes.Polyline_3014);
			types.add(GMFGraphElementTypes.Polygon_3023);
			types.add(GMFGraphElementTypes.Label_3026);
			return types;
		}
		if (editPart instanceof RoundedRectangleEditPart) {
			ArrayList types = new ArrayList(6);
			types.add(GMFGraphElementTypes.Rectangle_3011);
			types.add(GMFGraphElementTypes.Ellipse_3012);
			types.add(GMFGraphElementTypes.RoundedRectangle_3013);
			types.add(GMFGraphElementTypes.Polyline_3014);
			types.add(GMFGraphElementTypes.Polygon_3023);
			types.add(GMFGraphElementTypes.Label_3026);
			return types;
		}
		if (editPart instanceof PolylineEditPart) {
			ArrayList types = new ArrayList(1);
			types.add(GMFGraphElementTypes.Point_3022);
			return types;
		}
		if (editPart instanceof PolygonEditPart) {
			ArrayList types = new ArrayList(1);
			types.add(GMFGraphElementTypes.Point_3022);
			return types;
		}
		if (editPart instanceof Ellipse2EditPart) {
			ArrayList types = new ArrayList(6);
			types.add(GMFGraphElementTypes.Rectangle_3011);
			types.add(GMFGraphElementTypes.Ellipse_3012);
			types.add(GMFGraphElementTypes.RoundedRectangle_3013);
			types.add(GMFGraphElementTypes.Polyline_3014);
			types.add(GMFGraphElementTypes.Polygon_3023);
			types.add(GMFGraphElementTypes.Label_3026);
			return types;
		}
		if (editPart instanceof RoundedRectangle2EditPart) {
			ArrayList types = new ArrayList(6);
			types.add(GMFGraphElementTypes.Rectangle_3011);
			types.add(GMFGraphElementTypes.Ellipse_3012);
			types.add(GMFGraphElementTypes.RoundedRectangle_3013);
			types.add(GMFGraphElementTypes.Polyline_3014);
			types.add(GMFGraphElementTypes.Polygon_3023);
			types.add(GMFGraphElementTypes.Label_3026);
			return types;
		}
		if (editPart instanceof Polyline2EditPart) {
			ArrayList types = new ArrayList(1);
			types.add(GMFGraphElementTypes.Point_3022);
			return types;
		}
		if (editPart instanceof Polygon2EditPart) {
			ArrayList types = new ArrayList(1);
			types.add(GMFGraphElementTypes.Point_3022);
			return types;
		}
		if (editPart instanceof Rectangle3EditPart) {
			ArrayList types = new ArrayList(6);
			types.add(GMFGraphElementTypes.Rectangle_3011);
			types.add(GMFGraphElementTypes.Ellipse_3012);
			types.add(GMFGraphElementTypes.RoundedRectangle_3013);
			types.add(GMFGraphElementTypes.Polyline_3014);
			types.add(GMFGraphElementTypes.Polygon_3023);
			types.add(GMFGraphElementTypes.Label_3026);
			return types;
		}
		if (editPart instanceof Ellipse3EditPart) {
			ArrayList types = new ArrayList(6);
			types.add(GMFGraphElementTypes.Rectangle_3011);
			types.add(GMFGraphElementTypes.Ellipse_3012);
			types.add(GMFGraphElementTypes.RoundedRectangle_3013);
			types.add(GMFGraphElementTypes.Polyline_3014);
			types.add(GMFGraphElementTypes.Polygon_3023);
			types.add(GMFGraphElementTypes.Label_3026);
			return types;
		}
		if (editPart instanceof RoundedRectangle3EditPart) {
			ArrayList types = new ArrayList(6);
			types.add(GMFGraphElementTypes.Rectangle_3011);
			types.add(GMFGraphElementTypes.Ellipse_3012);
			types.add(GMFGraphElementTypes.RoundedRectangle_3013);
			types.add(GMFGraphElementTypes.Polyline_3014);
			types.add(GMFGraphElementTypes.Polygon_3023);
			types.add(GMFGraphElementTypes.Label_3026);
			return types;
		}
		if (editPart instanceof Polyline3EditPart) {
			ArrayList types = new ArrayList(1);
			types.add(GMFGraphElementTypes.Point_3022);
			return types;
		}
		if (editPart instanceof Polygon3EditPart) {
			ArrayList types = new ArrayList(1);
			types.add(GMFGraphElementTypes.Point_3022);
			return types;
		}
		if (editPart instanceof FigureGalleryFiguresEditPart) {
			ArrayList types = new ArrayList(7);
			types.add(GMFGraphElementTypes.FigureDescriptor_3009);
			types.add(GMFGraphElementTypes.Rectangle_3018);
			types.add(GMFGraphElementTypes.Ellipse_3019);
			types.add(GMFGraphElementTypes.RoundedRectangle_3020);
			types.add(GMFGraphElementTypes.Polyline_3021);
			types.add(GMFGraphElementTypes.Polygon_3025);
			types.add(GMFGraphElementTypes.Label_3028);
			return types;
		}
		if (editPart instanceof CanvasEditPart) {
			ArrayList types = new ArrayList(5);
			types.add(GMFGraphElementTypes.Compartment_2005);
			types.add(GMFGraphElementTypes.Node_2006);
			types.add(GMFGraphElementTypes.Connection_2007);
			types.add(GMFGraphElementTypes.FigureGallery_2008);
			types.add(GMFGraphElementTypes.DiagramLabel_2009);
			return types;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof CompartmentEditPart) {
			return ((CompartmentEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof ConnectionEditPart) {
			return ((ConnectionEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof DiagramLabelEditPart) {
			return ((DiagramLabelEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		if (sourceEditPart instanceof FigureDescriptorEditPart) {
			return ((FigureDescriptorEditPart) sourceEditPart).getMARelTypesOnSource();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof FigureDescriptorEditPart) {
			return ((FigureDescriptorEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof RectangleEditPart) {
			return ((RectangleEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Rectangle2EditPart) {
			return ((Rectangle2EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof EllipseEditPart) {
			return ((EllipseEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof RoundedRectangleEditPart) {
			return ((RoundedRectangleEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof PolylineEditPart) {
			return ((PolylineEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof PolygonEditPart) {
			return ((PolygonEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof LabelEditPart) {
			return ((LabelEditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Ellipse2EditPart) {
			return ((Ellipse2EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof RoundedRectangle2EditPart) {
			return ((RoundedRectangle2EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Polyline2EditPart) {
			return ((Polyline2EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Polygon2EditPart) {
			return ((Polygon2EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Label2EditPart) {
			return ((Label2EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Rectangle3EditPart) {
			return ((Rectangle3EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Ellipse3EditPart) {
			return ((Ellipse3EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof RoundedRectangle3EditPart) {
			return ((RoundedRectangle3EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Polyline3EditPart) {
			return ((Polyline3EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Polygon3EditPart) {
			return ((Polygon3EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		if (targetEditPart instanceof Label3EditPart) {
			return ((Label3EditPart) targetEditPart).getMARelTypesOnTarget();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof CompartmentEditPart) {
			return ((CompartmentEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof ConnectionEditPart) {
			return ((ConnectionEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof DiagramLabelEditPart) {
			return ((DiagramLabelEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		if (sourceEditPart instanceof FigureDescriptorEditPart) {
			return ((FigureDescriptorEditPart) sourceEditPart).getMARelTypesOnSourceAndTarget(targetEditPart);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForSource(IAdaptable target, IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		if (targetEditPart instanceof FigureDescriptorEditPart) {
			return ((FigureDescriptorEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof RectangleEditPart) {
			return ((RectangleEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Rectangle2EditPart) {
			return ((Rectangle2EditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof EllipseEditPart) {
			return ((EllipseEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof RoundedRectangleEditPart) {
			return ((RoundedRectangleEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof PolylineEditPart) {
			return ((PolylineEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof PolygonEditPart) {
			return ((PolygonEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof LabelEditPart) {
			return ((LabelEditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Ellipse2EditPart) {
			return ((Ellipse2EditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof RoundedRectangle2EditPart) {
			return ((RoundedRectangle2EditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Polyline2EditPart) {
			return ((Polyline2EditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Polygon2EditPart) {
			return ((Polygon2EditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Label2EditPart) {
			return ((Label2EditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Rectangle3EditPart) {
			return ((Rectangle3EditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Ellipse3EditPart) {
			return ((Ellipse3EditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof RoundedRectangle3EditPart) {
			return ((RoundedRectangle3EditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Polyline3EditPart) {
			return ((Polyline3EditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Polygon3EditPart) {
			return ((Polygon3EditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		if (targetEditPart instanceof Label3EditPart) {
			return ((Label3EditPart) targetEditPart).getMATypesForSource(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public List getTypesForTarget(IAdaptable source, IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		if (sourceEditPart instanceof CompartmentEditPart) {
			return ((CompartmentEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof NodeEditPart) {
			return ((NodeEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof ConnectionEditPart) {
			return ((ConnectionEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof DiagramLabelEditPart) {
			return ((DiagramLabelEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		if (sourceEditPart instanceof FigureDescriptorEditPart) {
			return ((FigureDescriptorEditPart) sourceEditPart).getMATypesForTarget(relationshipType);
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForSource(IAdaptable target, IElementType relationshipType) {
		return selectExistingElement(target, getTypesForSource(target, relationshipType));
	}

	/**
	 * @generated
	 */
	public EObject selectExistingElementForTarget(IAdaptable source, IElementType relationshipType) {
		return selectExistingElement(source, getTypesForTarget(source, relationshipType));
	}

	/**
	 * @generated
	 */
	protected EObject selectExistingElement(IAdaptable host, Collection types) {
		if (types.isEmpty()) {
			return null;
		}
		IGraphicalEditPart editPart = (IGraphicalEditPart) host.getAdapter(IGraphicalEditPart.class);
		if (editPart == null) {
			return null;
		}
		Diagram diagram = (Diagram) editPart.getRoot().getContents().getModel();
		Collection elements = new HashSet();
		for (Iterator it = diagram.getElement().eAllContents(); it.hasNext();) {
			EObject element = (EObject) it.next();
			if (isApplicableElement(element, types)) {
				elements.add(element);
			}
		}
		if (elements.isEmpty()) {
			return null;
		}
		return selectElement((EObject[]) elements.toArray(new EObject[elements.size()]));
	}

	/**
	 * @generated
	 */
	protected boolean isApplicableElement(EObject element, Collection types) {
		IElementType type = ElementTypeRegistry.getInstance().getElementType(element);
		return types.contains(type);
	}

	/**
	 * @generated
	 */
	protected EObject selectElement(EObject[] elements) {
		Shell shell = Display.getCurrent().getActiveShell();
		ILabelProvider labelProvider = new AdapterFactoryLabelProvider(GMFGraphDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());
		ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, labelProvider);
		dialog.setMessage(Messages.GMFGraphModelingAssistantProviderMessage);
		dialog.setTitle(Messages.GMFGraphModelingAssistantProviderTitle);
		dialog.setMultipleSelection(false);
		dialog.setElements(elements);
		EObject selected = null;
		if (dialog.open() == Window.OK) {
			selected = (EObject) dialog.getFirstResult();
		}
		return selected;
	}
}
