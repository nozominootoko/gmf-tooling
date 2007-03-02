/*
 *  Copyright (c) 2007 Borland Software Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 */
package org.eclipse.gmf.graphdef.editor.sheet;

public abstract class AbstractCustomPropertySection extends org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection {

	public abstract java.util.Collection getSavedSelection();

	public abstract org.eclipse.jface.resource.ImageRegistry getImageRegistry();

	private static final String KIND_KEY = "kind"; //$NON-NLS-1$

	private static final int FILL = 101;

	private static final int OUTLINE = 102;

	private static final int XOR_FILL = 103;

	private static final int XOR_OUTLINE = 104;

	private static final int CORNER_WIDTH = 201;

	private static final int CORNER_HEIGHT = 202;

	private static final int LINE_WIDTH = 203;

	private LineWidthListener myListener;

	private org.eclipse.swt.widgets.Group myLineStyleGroup;

	private org.eclipse.swt.widgets.Group myLineWidthGroup;

	private org.eclipse.swt.widgets.Group myFillOutlineGroup;

	private org.eclipse.swt.widgets.Group myCornerGroup;

	private org.eclipse.swt.widgets.Spinner myLineWidthSpinner;

	private org.eclipse.swt.widgets.Button myLineStyle_Solid;

	private org.eclipse.swt.widgets.Button myLineStyle_Dash;

	private org.eclipse.swt.widgets.Button myLineStyle_Dot;

	private org.eclipse.swt.widgets.Button myLineStyle_DashDot;

	private org.eclipse.swt.widgets.Button myLineStyle_DashDotDot;

	private org.eclipse.swt.widgets.Button myLineStyle_Custom;

	private org.eclipse.swt.widgets.Button myFillCheckbox;

	private org.eclipse.swt.widgets.Button myOutlineCheckbox;

	private org.eclipse.swt.widgets.Button myXorFillCheckbox;

	private org.eclipse.swt.widgets.Button myXorOutlineCheckbox;

	private org.eclipse.swt.widgets.Spinner myCornerWidthSpinner;

	private org.eclipse.swt.widgets.Spinner myCornerHeightSpinner;

	private org.eclipse.swt.widgets.Composite mySectionComposite;

	protected org.eclipse.emf.ecore.EObject getEObject() {
		if (getSavedSelection() != null && !getSavedSelection().isEmpty()) {
			return (org.eclipse.emf.ecore.EObject) getSavedSelection().iterator().next();
		}
		return super.getEObject();
	}

	public void createControls(org.eclipse.swt.widgets.Composite parent, org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		mySectionComposite = getWidgetFactory().createFlatFormComposite(parent);

		myLineStyleGroup = createLineStyleGroup(mySectionComposite, getLineStyleLabel());
		myLineStyle_Solid = createLineStyleRadio(myLineStyleGroup, null, false, getImageRegistry().get(org.eclipse.gmf.gmfgraph.LineKind.LINE_SOLID_LITERAL.getLiteral()),
				org.eclipse.swt.SWT.LINE_SOLID);
		myLineStyle_Dash = createLineStyleRadio(myLineStyleGroup, myLineStyle_Solid, false, getImageRegistry().get(org.eclipse.gmf.gmfgraph.LineKind.LINE_DASH_LITERAL.getLiteral()),
				org.eclipse.swt.SWT.LINE_DASH);
		myLineStyle_Dot = createLineStyleRadio(myLineStyleGroup, myLineStyle_Dash, false, getImageRegistry().get(org.eclipse.gmf.gmfgraph.LineKind.LINE_DOT_LITERAL.getLiteral()),
				org.eclipse.swt.SWT.LINE_DOT);
		myLineStyle_DashDot = createLineStyleRadio(myLineStyleGroup, myLineStyle_Dot, false, getImageRegistry().get(org.eclipse.gmf.gmfgraph.LineKind.LINE_DASHDOT_LITERAL.getLiteral()),
				org.eclipse.swt.SWT.LINE_DASHDOT);
		myLineStyle_DashDotDot = createLineStyleRadio(myLineStyleGroup, myLineStyle_DashDot, false, getImageRegistry().get(org.eclipse.gmf.gmfgraph.LineKind.LINE_DASHDOTDOT_LITERAL.getLiteral()),
				org.eclipse.swt.SWT.LINE_DASHDOTDOT);
		myLineStyle_Custom = createLineStyleRadio(myLineStyleGroup, myLineStyle_DashDotDot, true, getImageRegistry().get(org.eclipse.gmf.gmfgraph.LineKind.LINE_CUSTOM_LITERAL.getLiteral()),
				org.eclipse.swt.SWT.LINE_CUSTOM);

		myLineWidthGroup = createLineWidthGroup(mySectionComposite, getLineWidthLabel());
		// org.eclipse.swt.custom.CLabel lineWidthLabel =
		// createLabelWidget(myLineWidthGroup, getLineWidthLabel(), null);
		myLineWidthSpinner = createSpinnerWidget(myLineWidthGroup, null, LINE_WIDTH);

		myFillOutlineGroup = createFillOutlineGroup(mySectionComposite, getFillOutlineLabel());
		myFillCheckbox = createCheckbox(myFillOutlineGroup, getFillPropertyNameLabel(), null, null, FILL);
		myOutlineCheckbox = createCheckbox(myFillOutlineGroup, getOutlinePropertyNameLabel(), myFillCheckbox, null, OUTLINE);
		myXorFillCheckbox = createCheckbox(myFillOutlineGroup, getXorFillPropertyNameLabel(), null, myFillCheckbox, XOR_FILL);
		myXorOutlineCheckbox = createCheckbox(myFillOutlineGroup, getXorOutlinePropertyNameLabel(), myXorFillCheckbox, myOutlineCheckbox, XOR_OUTLINE);

		myCornerGroup = createCornerGroup(mySectionComposite, getCornerDimensionsLabel());
		org.eclipse.swt.custom.CLabel cornerWidthLabel = createLabelWidget(myCornerGroup, getCornerWidthLabel(), null);
		myCornerWidthSpinner = createSpinnerWidget(myCornerGroup, cornerWidthLabel, CORNER_WIDTH);
		org.eclipse.swt.custom.CLabel cornerHeigthLabel = createLabelWidget(myCornerGroup, getCornerHeightLabel(), myCornerWidthSpinner);
		myCornerHeightSpinner = createSpinnerWidget(myCornerGroup, cornerHeigthLabel, CORNER_HEIGHT);

		getListener().startListeningTo(myLineWidthSpinner);
		getListener().startListeningTo(myLineStyle_Solid);
		getListener().startListeningTo(myLineStyle_Dash);
		getListener().startListeningTo(myLineStyle_Dot);
		getListener().startListeningTo(myLineStyle_DashDot);
		getListener().startListeningTo(myLineStyle_DashDotDot);
		getListener().startListeningTo(myLineStyle_Custom);
		getListener().startListeningTo(myFillCheckbox);
		getListener().startListeningTo(myOutlineCheckbox);
		getListener().startListeningTo(myXorFillCheckbox);
		getListener().startListeningTo(myXorOutlineCheckbox);
		getListener().startListeningTo(myCornerWidthSpinner);
		getListener().startListeningTo(myCornerHeightSpinner);
	}

	public void dispose() {
		getListener().stopListeningTo(myLineWidthSpinner);
		getListener().stopListeningTo(myLineStyle_Solid);
		getListener().stopListeningTo(myLineStyle_Dash);
		getListener().stopListeningTo(myLineStyle_Dot);
		getListener().stopListeningTo(myLineStyle_DashDot);
		getListener().stopListeningTo(myLineStyle_DashDotDot);
		getListener().stopListeningTo(myLineStyle_Custom);
		getListener().stopListeningTo(myFillCheckbox);
		getListener().stopListeningTo(myOutlineCheckbox);
		getListener().stopListeningTo(myXorFillCheckbox);
		getListener().stopListeningTo(myXorOutlineCheckbox);
		getListener().stopListeningTo(myCornerWidthSpinner);
		getListener().stopListeningTo(myCornerHeightSpinner);
		super.dispose();
	}

	protected org.eclipse.swt.widgets.Button createLineStyleRadio(org.eclipse.swt.widgets.Composite parent, org.eclipse.swt.widgets.Control topControl, boolean last,
			org.eclipse.swt.graphics.Image image, int kind) {
		org.eclipse.swt.widgets.Button radio = getWidgetFactory().createButton(parent, "", org.eclipse.swt.SWT.RADIO);
		radio.setImage(image);
		radio.setData(KIND_KEY, new Integer(kind));
		org.eclipse.swt.layout.FormData data = new org.eclipse.swt.layout.FormData();
		if (topControl == null) {
			data.top = new org.eclipse.swt.layout.FormAttachment(0);
		} else {
			data.top = new org.eclipse.swt.layout.FormAttachment(topControl, 5);
		}
		data.left = new org.eclipse.swt.layout.FormAttachment(0);
		if (last) {
			data.bottom = new org.eclipse.swt.layout.FormAttachment(100);
		}
		radio.setLayoutData(data);
		return radio;
	}

	protected org.eclipse.swt.widgets.Group createLineWidthGroup(org.eclipse.swt.widgets.Composite parent, String label) {
		org.eclipse.swt.widgets.Group group = getWidgetFactory().createGroup(parent, label);
		org.eclipse.swt.layout.FormData data = new org.eclipse.swt.layout.FormData();
		data.top = new org.eclipse.swt.layout.FormAttachment(0, 0);
		data.left = new org.eclipse.swt.layout.FormAttachment(myLineStyleGroup);
		group.setLayoutData(data);
		group.setLayout(createStandardFormLayout());
		return group;
	}

	protected org.eclipse.swt.widgets.Group createLineStyleGroup(org.eclipse.swt.widgets.Composite parent, String label) {
		org.eclipse.swt.widgets.Group group = getWidgetFactory().createGroup(parent, label);
		org.eclipse.swt.layout.FormData data = new org.eclipse.swt.layout.FormData();
		data.top = new org.eclipse.swt.layout.FormAttachment(0, 0);
		data.left = new org.eclipse.swt.layout.FormAttachment(0);
		group.setLayoutData(data);
		group.setLayout(createStandardFormLayout());
		return group;
	}

	protected org.eclipse.swt.widgets.Group createFillOutlineGroup(org.eclipse.swt.widgets.Composite parent, String label) {
		org.eclipse.swt.widgets.Group group = getWidgetFactory().createGroup(parent, label);
		org.eclipse.swt.layout.FormData data = new org.eclipse.swt.layout.FormData();
		data.top = new org.eclipse.swt.layout.FormAttachment(myLineWidthGroup);
		data.left = new org.eclipse.swt.layout.FormAttachment(myLineStyleGroup);
		group.setLayoutData(data);
		group.setLayout(createStandardFormLayout());
		return group;
	}

	protected org.eclipse.swt.widgets.Group createCornerGroup(org.eclipse.swt.widgets.Composite parent, String label) {
		org.eclipse.swt.widgets.Group group = getWidgetFactory().createGroup(parent, label);
		org.eclipse.swt.layout.FormData data = new org.eclipse.swt.layout.FormData();
		data.top = new org.eclipse.swt.layout.FormAttachment(myFillOutlineGroup);
		data.left = new org.eclipse.swt.layout.FormAttachment(myLineStyleGroup);
		data.bottom = new org.eclipse.swt.layout.FormAttachment(100);
		group.setLayoutData(data);
		group.setLayout(createStandardFormLayout());
		return group;
	}

	protected org.eclipse.swt.widgets.Layout createStandardFormLayout() {
		org.eclipse.swt.layout.FormLayout layout = new org.eclipse.swt.layout.FormLayout();
		layout.marginWidth = org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants.HSPACE + 2;
		layout.marginHeight = org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants.VSPACE;
		layout.spacing = org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants.VMARGIN + 1;
		return layout;
	}

	protected org.eclipse.swt.widgets.Button createCheckbox(org.eclipse.swt.widgets.Group parent, String label, org.eclipse.swt.widgets.Control topControl,
			org.eclipse.swt.widgets.Control leftControl, int kind) {
		org.eclipse.swt.widgets.Button checkbox = getWidgetFactory().createButton(parent, label, org.eclipse.swt.SWT.CHECK);
		checkbox.setData(KIND_KEY, new Integer(kind));
		org.eclipse.swt.layout.FormData data = new org.eclipse.swt.layout.FormData();
		if (topControl == null) {
			data.top = new org.eclipse.swt.layout.FormAttachment(0);
		} else {
			data.top = new org.eclipse.swt.layout.FormAttachment(topControl);
		}
		if (leftControl == null) {
			data.left = new org.eclipse.swt.layout.FormAttachment(0);
		} else {
			data.right = new org.eclipse.swt.layout.FormAttachment(100, -org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants.HSPACE);
			data.left = new org.eclipse.swt.layout.FormAttachment(50);
		}
		checkbox.setLayoutData(data);
		return checkbox;
	}

	private String getCornerDimensionsLabel() {
		return "Corner Dimensions";
	}

	private String getCornerWidthLabel() {
		return "Corner Width";
	}

	private String getCornerHeightLabel() {
		return "Corner Height";
	}

	private String getFillPropertyNameLabel() {
		return "Fill";
	}

	private String getOutlinePropertyNameLabel() {
		return "Outline";
	}

	private String getXorFillPropertyNameLabel() {
		return "XOR Fill";
	}

	private String getXorOutlinePropertyNameLabel() {
		return "XOR Outline";
	}

	private String getFillOutlineLabel() {
		return "Mix Styles";
	}

	private String getLineStyleLabel() {
		return "Line Style";
	}

	private String getLineWidthLabel() {
		return "Line Width";
	}

	protected void setLineWidth(final int lineWidth) {
		modifyModel(new RunnableWithShape() {

			public void run() {
				getShape().setLineWidth(lineWidth);
			}
		}, "Setting line width");
	}

	protected void setLineKind(final int lineKind) {
		modifyModel(new RunnableWithShape() {

			public void run() {
				getShape().setLineKind(org.eclipse.gmf.gmfgraph.LineKind.get(lineKind));
			}
		}, "Setting line style");
	}

	protected void setFill(final boolean value) {
		modifyModel(new RunnableWithShape() {

			public void run() {
				getShape().setFill(value);
			}
		}, "Setting shape fill");
	}

	protected void setOutline(final boolean value) {
		modifyModel(new RunnableWithShape() {

			public void run() {
				getShape().setOutline(value);
			}
		}, "Setting shape outline");
	}

	protected void setXorFill(final boolean value) {
		modifyModel(new RunnableWithShape() {

			public void run() {
				getShape().setXorFill(value);
			}
		}, "Setting shape XOR fill");
	}

	protected void setXorOutline(final boolean value) {
		modifyModel(new RunnableWithShape() {

			public void run() {
				getShape().setXorOutline(value);
			}
		}, "Setting shape XOR outline");
	}

	protected void setCornerWidth(final int cornerWidth) {
		modifyModel(new RunnableWithShape() {

			public void run() {
				((org.eclipse.gmf.gmfgraph.RoundedRectangle) getShape()).setCornerWidth(cornerWidth);
			}
		}, "Setting corner width");
	}

	protected void setCornerHeight(final int cornerHeight) {
		modifyModel(new RunnableWithShape() {

			public void run() {
				((org.eclipse.gmf.gmfgraph.RoundedRectangle) getShape()).setCornerHeight(cornerHeight);
			}
		}, "Setting corner height");
	}

	public static abstract class RunnableWithShape implements Runnable {

		private org.eclipse.gmf.gmfgraph.Shape myShape;

		public org.eclipse.gmf.gmfgraph.Shape getShape() {
			return myShape;
		}

		public void setTargetShape(org.eclipse.gmf.gmfgraph.Shape shape) {
			myShape = shape;
		}
	}

	protected void modifyModel(RunnableWithShape runnable, String commandName) {
		java.util.ArrayList commands = new java.util.ArrayList();
		for (java.util.Iterator it = getSavedSelection().iterator(); it.hasNext();) {
			final org.eclipse.emf.ecore.EObject next = (org.eclipse.emf.ecore.EObject) it.next();
			if (next instanceof org.eclipse.gmf.gmfgraph.Shape) {
				final org.eclipse.gmf.gmfgraph.Shape shape = (org.eclipse.gmf.gmfgraph.Shape) next;
				runnable.setTargetShape(shape);
				commands.add(createCommand(commandName, next, runnable));
			}
		}
		executeAsCompositeCommand(commandName, commands);
		refresh();
	}

	private LineWidthListener getListener() {
		if (myListener == null) {
			myListener = new LineWidthListener();
		}
		return myListener;
	}

	private class LineWidthListener implements org.eclipse.swt.events.ModifyListener, org.eclipse.swt.events.SelectionListener {

		private boolean nonUserChange;

		public boolean isNonUserChange() {
			return nonUserChange;
		}

		private void applyChangesFrom(org.eclipse.swt.widgets.Widget widget) {
			if (!isNonUserChange()) {
				if (widget instanceof org.eclipse.swt.widgets.Spinner) {
					Integer kind = (Integer) widget.getData(KIND_KEY);
					int value = ((org.eclipse.swt.widgets.Spinner) widget).getSelection();
					switch (kind.intValue()) {
					case CORNER_WIDTH: {
						setCornerWidth(value);
						break;
					}
					case CORNER_HEIGHT: {
						setCornerHeight(value);
						break;
					}
					case LINE_WIDTH: {
						setLineWidth(value);
						break;
					}
					}
				} else if (widget instanceof org.eclipse.swt.widgets.Button) {
					boolean turnedOn = ((org.eclipse.swt.widgets.Button) widget).getSelection();
					Integer kind = (Integer) widget.getData(KIND_KEY);
					switch (kind.intValue()) {
					case org.eclipse.swt.SWT.LINE_SOLID:
					case org.eclipse.swt.SWT.LINE_DASH:
					case org.eclipse.swt.SWT.LINE_DOT:
					case org.eclipse.swt.SWT.LINE_DASHDOT:
					case org.eclipse.swt.SWT.LINE_DASHDOTDOT:
					case org.eclipse.swt.SWT.LINE_CUSTOM: {
						setLineKind(kind.intValue());
						break;
					}
					case FILL: {
						setFill(turnedOn);
						break;
					}
					case OUTLINE: {
						setOutline(turnedOn);
						break;
					}
					case XOR_FILL: {
						setXorFill(turnedOn);
						break;
					}
					case XOR_OUTLINE: {
						setXorOutline(turnedOn);
						break;
					}
					}
				}
			}
		}

		public void modifyText(org.eclipse.swt.events.ModifyEvent e) {
			applyChangesFrom(e.widget);
		}

		public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
			widgetSelected(e);
		}

		public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
			applyChangesFrom(e.widget);
		}

		public void startListeningTo(org.eclipse.swt.widgets.Spinner spinner) {
			spinner.addModifyListener(this);
			spinner.addSelectionListener(this);
		}

		public void stopListeningTo(org.eclipse.swt.widgets.Spinner spinner) {
			spinner.removeModifyListener(this);
			spinner.removeSelectionListener(this);
		}

		public void startListeningTo(org.eclipse.swt.widgets.Button button) {
			button.addSelectionListener(this);
		}

		public void stopListeningTo(org.eclipse.swt.widgets.Button button) {
			button.removeSelectionListener(this);
		}

		public void startNonUserChange() {
			nonUserChange = true;
		}

		public void finishNonUserChange() {
			nonUserChange = false;
		}
	}

	protected org.eclipse.swt.custom.CLabel createLabelWidget(org.eclipse.swt.widgets.Composite parent, String labelText, org.eclipse.swt.widgets.Control leftWidget) {
		org.eclipse.swt.custom.CLabel label = getWidgetFactory().createCLabel(parent, labelText);
		org.eclipse.swt.layout.FormData data = new org.eclipse.swt.layout.FormData();
		data.top = new org.eclipse.swt.layout.FormAttachment(0);
		if (leftWidget != null) {
			data.left = new org.eclipse.swt.layout.FormAttachment(leftWidget);
		} else {
			data.left = new org.eclipse.swt.layout.FormAttachment(0);
		}
		label.setLayoutData(data);
		return label;
	}

	protected org.eclipse.swt.widgets.Spinner createSpinnerWidget(org.eclipse.swt.widgets.Composite parent, org.eclipse.swt.widgets.Control leftWidget, int kind) {
		org.eclipse.swt.widgets.Spinner spinner = new org.eclipse.swt.widgets.Spinner(parent, org.eclipse.swt.SWT.BORDER);
		spinner.setMinimum(1);
		spinner.setMaximum(999);
		spinner.setSelection(100);
		spinner.setIncrement(1);
		spinner.setPageIncrement(100);
		spinner.setBackground(parent.getBackground());
		spinner.setForeground(parent.getForeground());
		spinner.setData(KIND_KEY, kind);
		org.eclipse.swt.layout.FormData data = new org.eclipse.swt.layout.FormData();
		if (leftWidget != null) {
			data.left = new org.eclipse.swt.layout.FormAttachment(leftWidget);
		} else {
			data.left = new org.eclipse.swt.layout.FormAttachment(0);
		}
		data.top = new org.eclipse.swt.layout.FormAttachment(0);
		spinner.setLayoutData(data);
		return spinner;
	}

	private int getLineKind(Object object) {
		if (object instanceof org.eclipse.gmf.gmfgraph.Shape) {
			org.eclipse.gmf.gmfgraph.Shape shape = (org.eclipse.gmf.gmfgraph.Shape) object;
			return shape.getLineKind().getValue();
		}
		return -1;
	}

	private int getLineWidth(Object object) {
		if (object instanceof org.eclipse.gmf.gmfgraph.Shape) {
			org.eclipse.gmf.gmfgraph.Shape shape = (org.eclipse.gmf.gmfgraph.Shape) object;
			return shape.getLineWidth();
		}
		return -1;
	}

	private boolean getFill(Object object) {
		if (object instanceof org.eclipse.gmf.gmfgraph.Shape) {
			org.eclipse.gmf.gmfgraph.Shape shape = (org.eclipse.gmf.gmfgraph.Shape) object;
			return shape.isFill();
		}
		return false;
	}

	private boolean getOutline(Object object) {
		if (object instanceof org.eclipse.gmf.gmfgraph.Shape) {
			org.eclipse.gmf.gmfgraph.Shape shape = (org.eclipse.gmf.gmfgraph.Shape) object;
			return shape.isOutline();
		}
		return false;
	}

	private boolean getXorFill(Object object) {
		if (object instanceof org.eclipse.gmf.gmfgraph.Shape) {
			org.eclipse.gmf.gmfgraph.Shape shape = (org.eclipse.gmf.gmfgraph.Shape) object;
			return shape.isXorFill();
		}
		return false;
	}

	private boolean getXorOutline(Object object) {
		if (object instanceof org.eclipse.gmf.gmfgraph.Shape) {
			org.eclipse.gmf.gmfgraph.Shape shape = (org.eclipse.gmf.gmfgraph.Shape) object;
			return shape.isXorOutline();
		}
		return false;
	}

	private int getCornerWidth(Object object) {
		if (object instanceof org.eclipse.gmf.gmfgraph.RoundedRectangle) {
			org.eclipse.gmf.gmfgraph.RoundedRectangle cornered = (org.eclipse.gmf.gmfgraph.RoundedRectangle) object;
			return cornered.getCornerWidth();
		}
		return -1;
	}

	private int getCornerHeight(Object object) {
		if (object instanceof org.eclipse.gmf.gmfgraph.RoundedRectangle) {
			org.eclipse.gmf.gmfgraph.RoundedRectangle cornered = (org.eclipse.gmf.gmfgraph.RoundedRectangle) object;
			return cornered.getCornerHeight();
		}
		return -1;
	}

	public void refresh() {
		getListener().startNonUserChange();
		try {
			for (java.util.Iterator it = getSavedSelection().iterator(); it.hasNext();) {
				final Object next = it.next();
				mySectionComposite.setVisible(next instanceof org.eclipse.gmf.gmfgraph.Shape);
				if (!mySectionComposite.isVisible()) {
					return;
				}
				executeAsReadAction(new Runnable() {

					public void run() {
						int lineWidth = getLineWidth(next);
						if (lineWidth != -1) {
							myLineWidthSpinner.setSelection(lineWidth);
						}
						int lineKind = getLineKind(next);
						myLineStyle_Solid.setSelection(lineKind == org.eclipse.swt.SWT.LINE_SOLID);
						myLineStyle_Dash.setSelection(lineKind == org.eclipse.swt.SWT.LINE_DASH);
						myLineStyle_Dot.setSelection(lineKind == org.eclipse.swt.SWT.LINE_DOT);
						myLineStyle_DashDot.setSelection(lineKind == org.eclipse.swt.SWT.LINE_DASHDOT);
						myLineStyle_DashDotDot.setSelection(lineKind == org.eclipse.swt.SWT.LINE_DASHDOTDOT);
						myLineStyle_Custom.setSelection(lineKind == org.eclipse.swt.SWT.LINE_CUSTOM);
						myFillCheckbox.setSelection(getFill(next));
						myOutlineCheckbox.setSelection(getOutline(next));
						myXorFillCheckbox.setSelection(getXorFill(next));
						myXorOutlineCheckbox.setSelection(getXorOutline(next));
						myCornerGroup.setVisible(next instanceof org.eclipse.gmf.gmfgraph.RoundedRectangle);
						if (myCornerGroup.isVisible()) {
							int cornerWidth = getCornerWidth(next);
							if (cornerWidth != -1) {
								myCornerWidthSpinner.setSelection(cornerWidth);
							}
							int cornerHeight = getCornerHeight(next);
							if (cornerHeight != -1) {
								myCornerHeightSpinner.setSelection(cornerHeight);
							}
						}
					}
				});
			}
		} finally {
			getListener().finishNonUserChange();
		}
	}

	private org.eclipse.swt.widgets.Button getLineKindButton(int lineKind) {
		switch (lineKind) {
		case org.eclipse.swt.SWT.LINE_SOLID:
			return myLineStyle_Solid;
		case org.eclipse.swt.SWT.LINE_DOT:
			return myLineStyle_Dot;
		case org.eclipse.swt.SWT.LINE_DASH:
			return myLineStyle_Dash;
		case org.eclipse.swt.SWT.LINE_DASHDOT:
			return myLineStyle_DashDot;
		case org.eclipse.swt.SWT.LINE_DASHDOTDOT:
			return myLineStyle_DashDotDot;
		case org.eclipse.swt.SWT.LINE_CUSTOM:
			return myLineStyle_Custom;
		default:
			return null;
		}
	}
}
