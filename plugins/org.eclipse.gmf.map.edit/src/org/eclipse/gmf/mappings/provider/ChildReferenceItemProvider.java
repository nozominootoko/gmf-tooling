/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.mappings.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.gmf.mappings.ChildReference;
import org.eclipse.gmf.mappings.GMFMapFactory;
import org.eclipse.gmf.mappings.GMFMapPackage;
import org.eclipse.gmf.mappings.NodeMapping;
import org.eclipse.gmf.mappings.NodeReference;
import org.eclipse.gmf.mappings.presentation.FilterUtil;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmf.mappings.ChildReference} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ChildReferenceItemProvider
	extends NodeReferenceItemProvider
	implements	
		IEditingDomainItemProvider,	
		IStructuredItemContentProvider,	
		ITreeItemContentProvider,	
		IItemLabelProvider,	
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChildReferenceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addCompartmentPropertyDescriptor(object);
			addReferencedChildPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Compartment feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addCompartmentPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ChildReference_compartment_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ChildReference_compartment_feature", "_UI_ChildReference_type"),
				 GMFMapPackage.eINSTANCE.getChildReference_Compartment(),
				 true,
				 null,
				 null,
				 null) {
						protected Collection<?> getComboBoxObjects(Object object) {
							@SuppressWarnings("unchecked")
							Collection<EObject> original = (Collection<EObject>) super.getComboBoxObjects(object);
							return FilterUtil.filterByNodeMapping(original, (ChildReference) object);
						}
			});
	}

	/**
	 * This adds a property descriptor for the Referenced Child feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addReferencedChildPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(new ItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ChildReference_referencedChild_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ChildReference_referencedChild_feature", "_UI_ChildReference_type"),
				 GMFMapPackage.eINSTANCE.getChildReference_ReferencedChild(),
				 true,
				 null,
				 null,
				 null) {
						protected Collection<?> getComboBoxObjects(Object object) {
							return FilterUtil.sort(super.getComboBoxObjects(object));
						}
			});
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(GMFMapPackage.eINSTANCE.getChildReference_OwnedChild());
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns ChildReference.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ChildReference"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getText(Object object) {
        if (object instanceof NodeReference) { 
        	NodeReference reference = (NodeReference) object;
            String result = " <"; 
            if (reference.getContainmentFeature() != null) { 
                 result += reference.getContainmentFeature().getName(); 
            } 
            if (reference.getChildrenFeature() != null) {
            	result += "|";
            	result += reference.getChildrenFeature().getName();
            }
            if (reference.isSetChild()) {
            	NodeMapping mapping = reference.getChild();
                if (mapping.getDomainMetaElement() != null) {
                	result += ":";
                	result += mapping.getDomainMetaElement().getName();
                }
                result += "/";
                if (mapping.getDiagramNode() != null) { 
                    result += reference.getChild().getDiagramNode().getName(); 
                } 
            }
            result += ">"; 
            return getString("_UI_ChildReference_type") + result; 

        }
		return getString("_UI_ChildReference_type");
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void notifyChangedGen(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ChildReference.class)) {
			case GMFMapPackage.CHILD_REFERENCE__OWNED_CHILD:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	public void notifyChanged(Notification notification) {
		switch (notification.getFeatureID(NodeMapping.class)) {
		case GMFMapPackage.NODE_REFERENCE__CONTAINMENT_FEATURE:
		case GMFMapPackage.NODE_REFERENCE__CHILDREN_FEATURE:
			fireNotifyChanged(new ViewerNotification(notification, null));
			break;
		}
		notifyChangedGen(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(GMFMapPackage.eINSTANCE.getChildReference_OwnedChild(),
				 GMFMapFactory.eINSTANCE.createNodeMapping()));
	}

}
