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
package org.eclipse.gmf.graphdef.editor.edit.parts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gmf.gmfgraph.GMFGraphPackage;
import org.eclipse.gmf.gmfgraph.Label;
import org.eclipse.gmf.gmfgraph.Layout;
import org.eclipse.gmf.graphdef.editor.edit.policies.BorderLayoutEditPolicy;
import org.eclipse.gmf.graphdef.editor.edit.policies.FigureContainerXYLayoutEditPolicy;
import org.eclipse.gmf.graphdef.editor.edit.policies.GridLayoutEditPolicy;
import org.eclipse.gmf.graphdef.editor.edit.policies.LabelItemSemanticEditPolicy;
import org.eclipse.gmf.graphdef.editor.part.GMFGraphVisualIDRegistry;
import org.eclipse.gmf.graphdef.editor.providers.GMFGraphElementTypes;
import org.eclipse.gmf.graphdef.editor.sheet.AttachAdapter;
import org.eclipse.gmf.graphdef.editor.sheet.ChangeTracker;
import org.eclipse.gmf.graphdef.editor.sheet.FeatureTracker;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class LabelEditPart extends AbstractFigureEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 3026;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public LabelEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new LabelItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		Layout layout = getGmfgraphElement().getLayout();
		if (layout != null) {
			switch (layout.eClass().getClassifierID()) {
			case GMFGraphPackage.BORDER_LAYOUT:
				return new BorderLayoutEditPolicy();
			case GMFGraphPackage.GRID_LAYOUT:
				return new GridLayoutEditPolicy();
			}
		}
		return new FigureContainerXYLayoutEditPolicy(getMapMode());
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		LabelFigure figure = new LabelFigure();
		return primaryShape = figure;
	}

	/**
	 * @generated
	 */
	public LabelFigure getPrimaryShape() {
		return (LabelFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof LabelTextEditPart) {
			((LabelTextEditPart) childEditPart).setLabel(getPrimaryShape().getFigureLabelFigure_TextLabel());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof LabelTextEditPart) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(0, 0);
		result.setMinimumSize(new Dimension(0, 0));
		return result;
	}

	/**
	 * @generated
	 */
	public EditPolicy getPrimaryDragEditPolicy() {
		EditPolicy result = super.getPrimaryDragEditPolicy();
		if (result instanceof ResizableEditPolicy) {
			ResizableEditPolicy ep = (ResizableEditPolicy) result;
			ep.setResizeDirections(PositionConstants.NONE);
		}
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(GMFGraphVisualIDRegistry.getType(LabelTextEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMARelTypesOnTarget() {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		types.add(GMFGraphElementTypes.ChildAccess_4002);
		return types;
	}

	/**
	 * @generated
	 */
	public List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/getMATypesForSource(IElementType relationshipType) {
		List/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/types = new ArrayList/*<org.eclipse.gmf.runtime.emf.type.core.IElementType>*/();
		if (relationshipType == GMFGraphElementTypes.ChildAccess_4002) {
			types.add(GMFGraphElementTypes.FigureDescriptor_3009);
		}
		return types;
	}

	/**
	 * @generated
	 */
	protected void handleNotificationEvent(Notification notification) {
		Object feature = notification.getFeature();
		if (NotationPackage.eINSTANCE.getSize_Width().equals(feature) || NotationPackage.eINSTANCE.getSize_Height().equals(feature) || NotationPackage.eINSTANCE.getLocation_X().equals(feature)
				|| NotationPackage.eINSTANCE.getLocation_Y().equals(feature)) {
			return;
		}
		super.handleNotificationEvent(notification);
	}

	/**
	 * @generated
	 */
	public class LabelFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureLabelFigure_TextLabel;

		/**
		 * @generated
		 */
		public LabelFigure() {
			this.setLayoutManager(new StackLayout());
			this.setFill(false);
			this.setOutline(false);
			this.setLineWidth(1);
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureLabelFigure_TextLabel = new WrappingLabel();
			fFigureLabelFigure_TextLabel.setText("<<Label>>");

			this.add(fFigureLabelFigure_TextLabel);

		}

		/**
		 * @generated
		 */
		private boolean myUseLocalCoordinates = false;

		/**
		 * @generated
		 */
		protected boolean useLocalCoordinates() {
			return myUseLocalCoordinates;
		}

		/**
		 * @generated
		 */
		protected void setUseLocalCoordinates(boolean useLocalCoordinates) {
			myUseLocalCoordinates = useLocalCoordinates;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureLabelFigure_TextLabel() {
			return fFigureLabelFigure_TextLabel;
		}

	}

	/**
	 * @generated
	 */
	private Collection<Adapter> myDomainElementAdapters = new ArrayList<Adapter>();

	/**
	 * @generated
	 */
	private Label getGmfgraphElement() {
		View view = getNotationView();
		if (view == null) {
			return null;
		}
		EObject element = view.getElement();
		if (element instanceof Label) {
			Label modelFigureElement = (Label) element;
			return modelFigureElement;
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected void removeSemanticListeners() {
		Label modelElement = getGmfgraphElement();
		if (modelElement != null) {
			modelElement.eAdapters().removeAll(myDomainElementAdapters);
			myDomainElementAdapters.clear();
		}
		super.removeSemanticListeners();
	}

	/**
	 * @generated
	 */
	protected void setFigure(IFigure figure) {
		super.setFigure(figure);
		Label modelElement = getGmfgraphElement();
		if (modelElement != null) {
			refreshBounds();
			refreshLayoutData();
			getPrimaryShape().setBackgroundColor(getColor(modelElement.getBackgroundColor()));
			getPrimaryShape().setForegroundColor(getColor(modelElement.getForegroundColor()));
			refreshFont();
		}
	}

	/**
	 * @generated
	 */
	public void activate() {
		if (isActive()) {
			return;
		}
		final Label modelElement = getGmfgraphElement();
		if (modelElement == null) {
			super.activate();
			return;
		}

		ChangeTracker refreshBoundsTracker = new ChangeTracker() {

			public void modelChanged(Notification msg) {
				refreshBounds();
			}
		};
		myDomainElementAdapters.add(new AttachAdapter(GMFGraphPackage.eINSTANCE.getFigure_Location(), refreshBoundsTracker, new FeatureTracker(refreshBoundsTracker, GMFGraphPackage.eINSTANCE
				.getPoint_X()), new FeatureTracker(refreshBoundsTracker, GMFGraphPackage.eINSTANCE.getPoint_Y())));
		myDomainElementAdapters.add(new AttachAdapter(GMFGraphPackage.eINSTANCE.getFigure_PreferredSize(), refreshBoundsTracker, new FeatureTracker(refreshBoundsTracker, GMFGraphPackage.eINSTANCE
				.getDimension_Dx()), new FeatureTracker(refreshBoundsTracker, GMFGraphPackage.eINSTANCE.getDimension_Dy())));

		ChangeTracker refreshLayoutDataTracker = new ChangeTracker() {

			public void modelChanged(Notification msg) {
				refreshLayoutData();
			}
		};
		myDomainElementAdapters.add(new AttachAdapter(GMFGraphPackage.eINSTANCE.getLayoutable_LayoutData(), refreshLayoutDataTracker, new AttachAdapter(GMFGraphPackage.eINSTANCE
				.getXYLayoutData_TopLeft(), refreshLayoutDataTracker, new FeatureTracker(refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE.getPoint_X()), new FeatureTracker(
				refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE.getPoint_Y())), new AttachAdapter(GMFGraphPackage.eINSTANCE.getXYLayoutData_Size(), refreshLayoutDataTracker, new FeatureTracker(
				refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE.getDimension_Dx()), new FeatureTracker(refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE.getDimension_Dy())), new FeatureTracker(
				refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE.getBorderLayoutData_Alignment()), new FeatureTracker(refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE
				.getBorderLayoutData_Vertical()), new FeatureTracker(refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE.getGridLayoutData_GrabExcessHorizontalSpace()), new FeatureTracker(
				refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE.getGridLayoutData_GrabExcessVerticalSpace()), new FeatureTracker(refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE
				.getGridLayoutData_VerticalAlignment()), new FeatureTracker(refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE.getGridLayoutData_HorizontalAlignment()), new FeatureTracker(
				refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE.getGridLayoutData_VerticalSpan()), new FeatureTracker(refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE
				.getGridLayoutData_HorizontalSpan()), new FeatureTracker(refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE.getGridLayoutData_HorizontalIndent()), new AttachAdapter(
				GMFGraphPackage.eINSTANCE.getGridLayoutData_SizeHint(), refreshLayoutDataTracker, new FeatureTracker(refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE.getDimension_Dx()),
				new FeatureTracker(refreshLayoutDataTracker, GMFGraphPackage.eINSTANCE.getDimension_Dy()))));

		ChangeTracker backgroundColorTracker = new ChangeTracker() {

			public void modelChanged(Notification msg) {
				getPrimaryShape().setBackgroundColor(getColor(modelElement.getBackgroundColor()));
			}
		};
		myDomainElementAdapters.add(new AttachAdapter(GMFGraphPackage.eINSTANCE.getFigure_BackgroundColor(), backgroundColorTracker, new FeatureTracker(backgroundColorTracker,
				GMFGraphPackage.eINSTANCE.getConstantColor_Value()), new FeatureTracker(backgroundColorTracker, GMFGraphPackage.eINSTANCE.getRGBColor_Red()), new FeatureTracker(
				backgroundColorTracker, GMFGraphPackage.eINSTANCE.getRGBColor_Green()), new FeatureTracker(backgroundColorTracker, GMFGraphPackage.eINSTANCE.getRGBColor_Blue())));

		ChangeTracker foregroundColorTracker = new ChangeTracker() {

			public void modelChanged(Notification msg) {
				getPrimaryShape().setForegroundColor(getColor(modelElement.getForegroundColor()));
			}
		};
		myDomainElementAdapters.add(new AttachAdapter(GMFGraphPackage.eINSTANCE.getFigure_ForegroundColor(), foregroundColorTracker, new FeatureTracker(foregroundColorTracker,
				GMFGraphPackage.eINSTANCE.getConstantColor_Value()), new FeatureTracker(foregroundColorTracker, GMFGraphPackage.eINSTANCE.getRGBColor_Red()), new FeatureTracker(
				foregroundColorTracker, GMFGraphPackage.eINSTANCE.getRGBColor_Green()), new FeatureTracker(foregroundColorTracker, GMFGraphPackage.eINSTANCE.getRGBColor_Blue())));

		ChangeTracker refreshFontTracker = new ChangeTracker() {

			public void modelChanged(Notification msg) {
				refreshFont();
			}
		};
		myDomainElementAdapters.add(new AttachAdapter(GMFGraphPackage.eINSTANCE.getFigure_Font(), refreshFontTracker, new FeatureTracker(refreshFontTracker, GMFGraphPackage.eINSTANCE
				.getBasicFont_FaceName()), new FeatureTracker(refreshFontTracker, GMFGraphPackage.eINSTANCE.getBasicFont_Height()), new FeatureTracker(refreshFontTracker, GMFGraphPackage.eINSTANCE
				.getBasicFont_Style())));
		modelElement.eAdapters().addAll(myDomainElementAdapters);
		super.activate();
	}

	/**
	 * @generated
	 */
	protected void refreshBounds() {
		Label modelElement = getGmfgraphElement();
		if (modelElement == null) {
			return;
		}
		if (modelElement.getPreferredSize() != null) {
			getFigure().setPreferredSize(getDraw2dDimension(modelElement.getPreferredSize()));
		}
		if (modelElement.getLocation() != null) {
			getFigure().setLocation(getDraw2DPoint(modelElement.getLocation()));
		}
	}

}
