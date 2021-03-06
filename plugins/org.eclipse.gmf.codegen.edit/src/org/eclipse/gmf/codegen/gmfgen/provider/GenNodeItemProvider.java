/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.gmf.codegen.gmfgen.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
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
import org.eclipse.gmf.codegen.gmfgen.GMFGenFactory;
import org.eclipse.gmf.codegen.gmfgen.GMFGenPackage;
import org.eclipse.gmf.codegen.gmfgen.GenNode;

/**
 * This is the item provider adapter for a {@link org.eclipse.gmf.codegen.gmfgen.GenNode} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenNodeItemProvider
	extends GenChildContainerItemProvider
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
	public GenNodeItemProvider(AdapterFactory adapterFactory) {
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

			addGenOutgoingLinksPropertyDescriptor(object);
			addGenIncomingLinksPropertyDescriptor(object);
			addCompartmentsPropertyDescriptor(object);
			addPrimaryDragEditPolicyQualifiedClassNamePropertyDescriptor(object);
			addGraphicalNodeEditPolicyClassNamePropertyDescriptor(object);
			addCreateCommandClassNamePropertyDescriptor(object);
			addReorientedIncomingLinksPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Gen Outgoing Links feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGenOutgoingLinksPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenLinkEnd_genOutgoingLinks_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenLinkEnd_genOutgoingLinks_feature", "_UI_GenLinkEnd_type"),
				 GMFGenPackage.eINSTANCE.getGenLinkEnd_GenOutgoingLinks(),
				 false,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Gen Incoming Links feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGenIncomingLinksPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenLinkEnd_genIncomingLinks_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenLinkEnd_genIncomingLinks_feature", "_UI_GenLinkEnd_type"),
				 GMFGenPackage.eINSTANCE.getGenLinkEnd_GenIncomingLinks(),
				 false,
				 false,
				 false,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Compartments feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCompartmentsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenNode_compartments_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenNode_compartments_feature", "_UI_GenNode_type"),
				 GMFGenPackage.eINSTANCE.getGenNode_Compartments(),
				 true,
				 false,
				 false,
				 null,
				 getString("_UI_DiagramElementPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Primary Drag Edit Policy Qualified Class Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPrimaryDragEditPolicyQualifiedClassNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenNode_primaryDragEditPolicyQualifiedClassName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenNode_primaryDragEditPolicyQualifiedClassName_feature", "_UI_GenNode_type"),
				 GMFGenPackage.eINSTANCE.getGenNode_PrimaryDragEditPolicyQualifiedClassName(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_DiagramElementPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Graphical Node Edit Policy Class Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addGraphicalNodeEditPolicyClassNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenNode_graphicalNodeEditPolicyClassName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenNode_graphicalNodeEditPolicyClassName_feature", "_UI_GenNode_type"),
				 GMFGenPackage.eINSTANCE.getGenNode_GraphicalNodeEditPolicyClassName(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_ClassNamesPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Create Command Class Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCreateCommandClassNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenNode_createCommandClassName_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenNode_createCommandClassName_feature", "_UI_GenNode_type"),
				 GMFGenPackage.eINSTANCE.getGenNode_CreateCommandClassName(),
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 getString("_UI_ClassNamesPropertyCategory"),
				 null));
	}

	/**
	 * This adds a property descriptor for the Reoriented Incoming Links feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addReorientedIncomingLinksPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_GenNode_reorientedIncomingLinks_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_GenNode_reorientedIncomingLinks_feature", "_UI_GenNode_type"),
				 GMFGenPackage.eINSTANCE.getGenNode_ReorientedIncomingLinks(),
				 false,
				 false,
				 false,
				 null,
				 getString("_UI_DiagramElementPropertyCategory"),
				 null));
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
			childrenFeatures.add(GMFGenPackage.eINSTANCE.getGenNode_ModelFacet());
			childrenFeatures.add(GMFGenPackage.eINSTANCE.getGenNode_Labels());
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((GenNode)object).getEditPartClassName();
		return label == null || label.length() == 0 ?
			getString("_UI_GenNode_type") :
			getString("_UI_GenNode_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(GenNode.class)) {
			case GMFGenPackage.GEN_NODE__PRIMARY_DRAG_EDIT_POLICY_QUALIFIED_CLASS_NAME:
			case GMFGenPackage.GEN_NODE__GRAPHICAL_NODE_EDIT_POLICY_CLASS_NAME:
			case GMFGenPackage.GEN_NODE__CREATE_COMMAND_CLASS_NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case GMFGenPackage.GEN_NODE__MODEL_FACET:
			case GMFGenPackage.GEN_NODE__LABELS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
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
				(GMFGenPackage.eINSTANCE.getGenNode_ModelFacet(),
				 GMFGenFactory.eINSTANCE.createTypeModelFacet()));

		newChildDescriptors.add
			(createChildParameter
				(GMFGenPackage.eINSTANCE.getGenNode_ModelFacet(),
				 GMFGenFactory.eINSTANCE.createTypeLinkModelFacet()));

		newChildDescriptors.add
			(createChildParameter
				(GMFGenPackage.eINSTANCE.getGenNode_Labels(),
				 GMFGenFactory.eINSTANCE.createGenNodeLabel()));

		newChildDescriptors.add
			(createChildParameter
				(GMFGenPackage.eINSTANCE.getGenNode_Labels(),
				 GMFGenFactory.eINSTANCE.createGenExternalNodeLabel()));
	}

}
