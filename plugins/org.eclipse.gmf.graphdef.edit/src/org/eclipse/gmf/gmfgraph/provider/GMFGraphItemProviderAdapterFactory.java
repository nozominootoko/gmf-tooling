/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.gmfgraph.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.gmf.gmfgraph.util.GMFGraphAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GMFGraphItemProviderAdapterFactory extends GMFGraphAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection supportedTypes = new ArrayList();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GMFGraphItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);		
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Canvas} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CanvasItemProvider canvasItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Canvas}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createCanvasAdapter() {
		if (canvasItemProvider == null) {
			canvasItemProvider = new CanvasItemProvider(this);
		}

		return canvasItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.FigureGallery} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FigureGalleryItemProvider figureGalleryItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.FigureGallery}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createFigureGalleryAdapter() {
		if (figureGalleryItemProvider == null) {
			figureGalleryItemProvider = new FigureGalleryItemProvider(this);
		}

		return figureGalleryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Node} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeItemProvider nodeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Node}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createNodeAdapter() {
		if (nodeItemProvider == null) {
			nodeItemProvider = new NodeItemProvider(this);
		}

		return nodeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Connection} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectionItemProvider connectionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Connection}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createConnectionAdapter() {
		if (connectionItemProvider == null) {
			connectionItemProvider = new ConnectionItemProvider(this);
		}

		return connectionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Compartment} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompartmentItemProvider compartmentItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Compartment}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createCompartmentAdapter() {
		if (compartmentItemProvider == null) {
			compartmentItemProvider = new CompartmentItemProvider(this);
		}

		return compartmentItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Child} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChildItemProvider childItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Child}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createChildAdapter() {
		if (childItemProvider == null) {
			childItemProvider = new ChildItemProvider(this);
		}

		return childItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.DiagramLabel} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramLabelItemProvider diagramLabelItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.DiagramLabel}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createDiagramLabelAdapter() {
		if (diagramLabelItemProvider == null) {
			diagramLabelItemProvider = new DiagramLabelItemProvider(this);
		}

		return diagramLabelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.GeneralFacet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeneralFacetItemProvider generalFacetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.GeneralFacet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGeneralFacetAdapter() {
		if (generalFacetItemProvider == null) {
			generalFacetItemProvider = new GeneralFacetItemProvider(this);
		}

		return generalFacetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.GradientFacet} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GradientFacetItemProvider gradientFacetItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.GradientFacet}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGradientFacetAdapter() {
		if (gradientFacetItemProvider == null) {
			gradientFacetItemProvider = new GradientFacetItemProvider(this);
		}

		return gradientFacetItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.FigureRef} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FigureRefItemProvider figureRefItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.FigureRef}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createFigureRefAdapter() {
		if (figureRefItemProvider == null) {
			figureRefItemProvider = new FigureRefItemProvider(this);
		}

		return figureRefItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Label} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LabelItemProvider labelItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Label}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createLabelAdapter() {
		if (labelItemProvider == null) {
			labelItemProvider = new LabelItemProvider(this);
		}

		return labelItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.LabeledContainer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LabeledContainerItemProvider labeledContainerItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.LabeledContainer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createLabeledContainerAdapter() {
		if (labeledContainerItemProvider == null) {
			labeledContainerItemProvider = new LabeledContainerItemProvider(this);
		}

		return labeledContainerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Rectangle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RectangleItemProvider rectangleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Rectangle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createRectangleAdapter() {
		if (rectangleItemProvider == null) {
			rectangleItemProvider = new RectangleItemProvider(this);
		}

		return rectangleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.RoundedRectangle} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoundedRectangleItemProvider roundedRectangleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.RoundedRectangle}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createRoundedRectangleAdapter() {
		if (roundedRectangleItemProvider == null) {
			roundedRectangleItemProvider = new RoundedRectangleItemProvider(this);
		}

		return roundedRectangleItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Ellipse} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EllipseItemProvider ellipseItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Ellipse}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createEllipseAdapter() {
		if (ellipseItemProvider == null) {
			ellipseItemProvider = new EllipseItemProvider(this);
		}

		return ellipseItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Polyline} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PolylineItemProvider polylineItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Polyline}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createPolylineAdapter() {
		if (polylineItemProvider == null) {
			polylineItemProvider = new PolylineItemProvider(this);
		}

		return polylineItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Polygon} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PolygonItemProvider polygonItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Polygon}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createPolygonAdapter() {
		if (polygonItemProvider == null) {
			polygonItemProvider = new PolygonItemProvider(this);
		}

		return polygonItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.PolylineConnection} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PolylineConnectionItemProvider polylineConnectionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.PolylineConnection}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createPolylineConnectionAdapter() {
		if (polylineConnectionItemProvider == null) {
			polylineConnectionItemProvider = new PolylineConnectionItemProvider(this);
		}

		return polylineConnectionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.PolylineDecoration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PolylineDecorationItemProvider polylineDecorationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.PolylineDecoration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createPolylineDecorationAdapter() {
		if (polylineDecorationItemProvider == null) {
			polylineDecorationItemProvider = new PolylineDecorationItemProvider(this);
		}

		return polylineDecorationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.PolygonDecoration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PolygonDecorationItemProvider polygonDecorationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.PolygonDecoration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createPolygonDecorationAdapter() {
		if (polygonDecorationItemProvider == null) {
			polygonDecorationItemProvider = new PolygonDecorationItemProvider(this);
		}

		return polygonDecorationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.CustomFigure} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomFigureItemProvider customFigureItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.CustomFigure}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createCustomFigureAdapter() {
		if (customFigureItemProvider == null) {
			customFigureItemProvider = new CustomFigureItemProvider(this);
		}

		return customFigureItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.CustomDecoration} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomDecorationItemProvider customDecorationItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.CustomDecoration}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createCustomDecorationAdapter() {
		if (customDecorationItemProvider == null) {
			customDecorationItemProvider = new CustomDecorationItemProvider(this);
		}

		return customDecorationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.CustomConnection} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomConnectionItemProvider customConnectionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.CustomConnection}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createCustomConnectionAdapter() {
		if (customConnectionItemProvider == null) {
			customConnectionItemProvider = new CustomConnectionItemProvider(this);
		}

		return customConnectionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.RGBColor} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RGBColorItemProvider rgbColorItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.RGBColor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createRGBColorAdapter() {
		if (rgbColorItemProvider == null) {
			rgbColorItemProvider = new RGBColorItemProvider(this);
		}

		return rgbColorItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.ConstantColor} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConstantColorItemProvider constantColorItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.ConstantColor}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createConstantColorAdapter() {
		if (constantColorItemProvider == null) {
			constantColorItemProvider = new ConstantColorItemProvider(this);
		}

		return constantColorItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.BasicFont} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BasicFontItemProvider basicFontItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.BasicFont}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createBasicFontAdapter() {
		if (basicFontItemProvider == null) {
			basicFontItemProvider = new BasicFontItemProvider(this);
		}

		return basicFontItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Point} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PointItemProvider pointItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Point}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createPointAdapter() {
		if (pointItemProvider == null) {
			pointItemProvider = new PointItemProvider(this);
		}

		return pointItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Dimension} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DimensionItemProvider dimensionItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Dimension}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createDimensionAdapter() {
		if (dimensionItemProvider == null) {
			dimensionItemProvider = new DimensionItemProvider(this);
		}

		return dimensionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.Insets} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InsetsItemProvider insetsItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.Insets}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createInsetsAdapter() {
		if (insetsItemProvider == null) {
			insetsItemProvider = new InsetsItemProvider(this);
		}

		return insetsItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.LineBorder} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LineBorderItemProvider lineBorderItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.LineBorder}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createLineBorderAdapter() {
		if (lineBorderItemProvider == null) {
			lineBorderItemProvider = new LineBorderItemProvider(this);
		}

		return lineBorderItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.MarginBorder} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MarginBorderItemProvider marginBorderItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.MarginBorder}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createMarginBorderAdapter() {
		if (marginBorderItemProvider == null) {
			marginBorderItemProvider = new MarginBorderItemProvider(this);
		}

		return marginBorderItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.CompoundBorder} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CompoundBorderItemProvider compoundBorderItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.CompoundBorder}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createCompoundBorderAdapter() {
		if (compoundBorderItemProvider == null) {
			compoundBorderItemProvider = new CompoundBorderItemProvider(this);
		}

		return compoundBorderItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.CustomBorder} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomBorderItemProvider customBorderItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.CustomBorder}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createCustomBorderAdapter() {
		if (customBorderItemProvider == null) {
			customBorderItemProvider = new CustomBorderItemProvider(this);
		}

		return customBorderItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.CustomLayoutData} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomLayoutDataItemProvider customLayoutDataItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.CustomLayoutData}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createCustomLayoutDataAdapter() {
		if (customLayoutDataItemProvider == null) {
			customLayoutDataItemProvider = new CustomLayoutDataItemProvider(this);
		}

		return customLayoutDataItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.GridLayoutData} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GridLayoutDataItemProvider gridLayoutDataItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.GridLayoutData}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGridLayoutDataAdapter() {
		if (gridLayoutDataItemProvider == null) {
			gridLayoutDataItemProvider = new GridLayoutDataItemProvider(this);
		}

		return gridLayoutDataItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.BorderLayoutData} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BorderLayoutDataItemProvider borderLayoutDataItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.BorderLayoutData}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createBorderLayoutDataAdapter() {
		if (borderLayoutDataItemProvider == null) {
			borderLayoutDataItemProvider = new BorderLayoutDataItemProvider(this);
		}

		return borderLayoutDataItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.CustomLayout} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomLayoutItemProvider customLayoutItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.CustomLayout}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createCustomLayoutAdapter() {
		if (customLayoutItemProvider == null) {
			customLayoutItemProvider = new CustomLayoutItemProvider(this);
		}

		return customLayoutItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.GridLayout} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GridLayoutItemProvider gridLayoutItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.GridLayout}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createGridLayoutAdapter() {
		if (gridLayoutItemProvider == null) {
			gridLayoutItemProvider = new GridLayoutItemProvider(this);
		}

		return gridLayoutItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.BorderLayout} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BorderLayoutItemProvider borderLayoutItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.BorderLayout}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createBorderLayoutAdapter() {
		if (borderLayoutItemProvider == null) {
			borderLayoutItemProvider = new BorderLayoutItemProvider(this);
		}

		return borderLayoutItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.gmf.gmfgraph.CustomAttribute} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CustomAttributeItemProvider customAttributeItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.gmf.gmfgraph.CustomAttribute}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter createCustomAttributeAdapter() {
		if (customAttributeItemProvider == null) {
			customAttributeItemProvider = new CustomAttributeItemProvider(this);
		}

		return customAttributeItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class) || (((Class)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (canvasItemProvider != null) canvasItemProvider.dispose();
		if (figureGalleryItemProvider != null) figureGalleryItemProvider.dispose();
		if (nodeItemProvider != null) nodeItemProvider.dispose();
		if (connectionItemProvider != null) connectionItemProvider.dispose();
		if (compartmentItemProvider != null) compartmentItemProvider.dispose();
		if (childItemProvider != null) childItemProvider.dispose();
		if (diagramLabelItemProvider != null) diagramLabelItemProvider.dispose();
		if (generalFacetItemProvider != null) generalFacetItemProvider.dispose();
		if (gradientFacetItemProvider != null) gradientFacetItemProvider.dispose();
		if (figureRefItemProvider != null) figureRefItemProvider.dispose();
		if (labelItemProvider != null) labelItemProvider.dispose();
		if (labeledContainerItemProvider != null) labeledContainerItemProvider.dispose();
		if (rectangleItemProvider != null) rectangleItemProvider.dispose();
		if (roundedRectangleItemProvider != null) roundedRectangleItemProvider.dispose();
		if (ellipseItemProvider != null) ellipseItemProvider.dispose();
		if (polylineItemProvider != null) polylineItemProvider.dispose();
		if (polygonItemProvider != null) polygonItemProvider.dispose();
		if (polylineConnectionItemProvider != null) polylineConnectionItemProvider.dispose();
		if (polylineDecorationItemProvider != null) polylineDecorationItemProvider.dispose();
		if (polygonDecorationItemProvider != null) polygonDecorationItemProvider.dispose();
		if (customFigureItemProvider != null) customFigureItemProvider.dispose();
		if (customDecorationItemProvider != null) customDecorationItemProvider.dispose();
		if (customConnectionItemProvider != null) customConnectionItemProvider.dispose();
		if (rgbColorItemProvider != null) rgbColorItemProvider.dispose();
		if (constantColorItemProvider != null) constantColorItemProvider.dispose();
		if (basicFontItemProvider != null) basicFontItemProvider.dispose();
		if (pointItemProvider != null) pointItemProvider.dispose();
		if (dimensionItemProvider != null) dimensionItemProvider.dispose();
		if (insetsItemProvider != null) insetsItemProvider.dispose();
		if (lineBorderItemProvider != null) lineBorderItemProvider.dispose();
		if (marginBorderItemProvider != null) marginBorderItemProvider.dispose();
		if (compoundBorderItemProvider != null) compoundBorderItemProvider.dispose();
		if (customBorderItemProvider != null) customBorderItemProvider.dispose();
		if (customLayoutDataItemProvider != null) customLayoutDataItemProvider.dispose();
		if (gridLayoutDataItemProvider != null) gridLayoutDataItemProvider.dispose();
		if (borderLayoutDataItemProvider != null) borderLayoutDataItemProvider.dispose();
		if (customLayoutItemProvider != null) customLayoutItemProvider.dispose();
		if (gridLayoutItemProvider != null) gridLayoutItemProvider.dispose();
		if (borderLayoutItemProvider != null) borderLayoutItemProvider.dispose();
		if (customAttributeItemProvider != null) customAttributeItemProvider.dispose();
	}

}
