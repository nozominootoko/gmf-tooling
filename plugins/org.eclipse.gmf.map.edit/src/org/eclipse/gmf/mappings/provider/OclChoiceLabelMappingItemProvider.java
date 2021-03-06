/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.mappings.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.gmf.mappings.GMFMapFactory;
import org.eclipse.gmf.mappings.GMFMapPackage;
import org.eclipse.gmf.mappings.OclChoiceLabelMapping;
import org.eclipse.gmf.mappings.presentation.FilterUtil;

/**
 * This is the item provider adapter for a
 * {@link org.eclipse.gmf.mappings.OclChoiceLabelMapping} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class OclChoiceLabelMappingItemProvider extends LabelMappingItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider,
		IItemPropertySource {

	/**
	 * This constructs an instance from a factory and a notifier. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OclChoiceLabelMappingItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addFeaturePropertyDescriptor(object);
			addItemsExpressionPropertyDescriptor(object);
			addShowExpressionPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Feature feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected void addFeaturePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_OclChoiceLabelMapping_feature_feature"), getString("_UI_PropertyDescriptor_description", "_UI_OclChoiceLabelMapping_feature_feature", "_UI_OclChoiceLabelMapping_type"),
				GMFMapPackage.eINSTANCE.getOclChoiceLabelMapping_Feature(), true, false, true, null, null, null) {

			protected Collection<?> getComboBoxObjects(Object object) {
				@SuppressWarnings("unchecked")
				Collection<EStructuralFeature> original = (Collection<EStructuralFeature>) super.getComboBoxObjects(object);
				Collection<EStructuralFeature> filteredByContainer = FilterUtil.filterByContainerMetaclass(original, ((OclChoiceLabelMapping) object).getMapEntry());
				Collection<EStructuralFeature> result = new ArrayList<EStructuralFeature>();
				for (EStructuralFeature feature : filteredByContainer) {
					if (feature instanceof EAttribute) {
						result.add(feature);
					} else if (feature instanceof EReference) {
						EReference reference = (EReference) feature;
						if (!reference.isContainment() && reference.getUpperBound() == 1) {
							result.add(feature);
						}
					}
				}
				return result;
			}
		});
	}

	/**
	 * This adds a property descriptor for the Items Expression feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addItemsExpressionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_OclChoiceLabelMapping_itemsExpression_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_OclChoiceLabelMapping_itemsExpression_feature", "_UI_OclChoiceLabelMapping_type"),
				 GMFMapPackage.eINSTANCE.getOclChoiceLabelMapping_ItemsExpression(),
				 true,
				 false,
				 false,
				 null,
				 getString("_UI_VisualrepresentationPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Show Expression feature. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addShowExpressionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_OclChoiceLabelMapping_showExpression_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_OclChoiceLabelMapping_showExpression_feature", "_UI_OclChoiceLabelMapping_type"),
				 GMFMapPackage.eINSTANCE.getOclChoiceLabelMapping_ShowExpression(),
				 true,
				 false,
				 false,
				 null,
				 getString("_UI_VisualrepresentationPropertyCategory"),
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(GMFMapPackage.eINSTANCE.getOclChoiceLabelMapping_ItemsExpression());
			childrenFeatures.add(GMFMapPackage.eINSTANCE.getOclChoiceLabelMapping_ShowExpression());
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns OclChoiceLabelMapping.gif.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/OclChoiceLabelMapping"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		OclChoiceLabelMapping oclChoiceLabelMapping = (OclChoiceLabelMapping)object;
		return getString("_UI_OclChoiceLabelMapping_type") + " " + oclChoiceLabelMapping.isReadOnly();
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(OclChoiceLabelMapping.class)) {
			case GMFMapPackage.OCL_CHOICE_LABEL_MAPPING__ITEMS_EXPRESSION:
			case GMFMapPackage.OCL_CHOICE_LABEL_MAPPING__SHOW_EXPRESSION:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
	 * describing the children that can be created under this object. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(GMFMapPackage.eINSTANCE.getOclChoiceLabelMapping_ItemsExpression(),
				 GMFMapFactory.eINSTANCE.createValueExpression()));

		newChildDescriptors.add
			(createChildParameter
				(GMFMapPackage.eINSTANCE.getOclChoiceLabelMapping_ItemsExpression(),
				 GMFMapFactory.eINSTANCE.createConstraint()));

		newChildDescriptors.add
			(createChildParameter
				(GMFMapPackage.eINSTANCE.getOclChoiceLabelMapping_ShowExpression(),
				 GMFMapFactory.eINSTANCE.createValueExpression()));

		newChildDescriptors.add
			(createChildParameter
				(GMFMapPackage.eINSTANCE.getOclChoiceLabelMapping_ShowExpression(),
				 GMFMapFactory.eINSTANCE.createConstraint()));
	}

	/**
	 * This returns the label text for
	 * {@link org.eclipse.emf.edit.command.CreateChildCommand}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == GMFMapPackage.eINSTANCE.getOclChoiceLabelMapping_ItemsExpression() ||
			childFeature == GMFMapPackage.eINSTANCE.getOclChoiceLabelMapping_ShowExpression();

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
