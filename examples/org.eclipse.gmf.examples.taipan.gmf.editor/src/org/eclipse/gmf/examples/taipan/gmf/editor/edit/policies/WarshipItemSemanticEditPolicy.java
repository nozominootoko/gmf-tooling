/*
 *  Copyright (c) 2006, 2007 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Dmitry Stadnik (Borland) - initial API and implementation
 */
package org.eclipse.gmf.examples.taipan.gmf.editor.edit.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.examples.taipan.Port;
import org.eclipse.gmf.examples.taipan.Ship;
import org.eclipse.gmf.examples.taipan.TaiPanPackage;
import org.eclipse.gmf.examples.taipan.Warship;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.commands.BesiegePortOrderCreateCommand;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.commands.BesiegePortOrderReorientCommand;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.commands.EscortShipsOrderCreateCommand;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.commands.EscortShipsOrderReorientCommand;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.commands.PortRegisterCreateCommand;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.commands.PortRegisterReorientCommand;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.commands.ShipDestinationCreateCommand;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.commands.ShipDestinationReorientCommand;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.parts.BesiegePortOrderEditPart;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.parts.EscortShipsOrderEditPart;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.parts.PortRegisterEditPart;
import org.eclipse.gmf.examples.taipan.gmf.editor.edit.parts.ShipDestinationEditPart;
import org.eclipse.gmf.examples.taipan.gmf.editor.providers.TaiPanElementTypes;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientReferenceRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class WarshipItemSemanticEditPolicy extends TaiPanBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		CompoundCommand cc = getDestroyEdgesCommand();
		View view = (View) getHost().getModel();
		if (view.getEAnnotation("Shortcut") != null) { //$NON-NLS-1$
			req.setElementToDestroy(view);
		}
		cc.add(getGEFWrapper(new DestroyElementCommand(req)));
		return cc.unwrap();
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		if (TaiPanElementTypes.ShipDestination_4001 == req.getElementType()) {
			return req.getTarget() == null ? getCreateStartOutgoingShipDestination_4001Command(req) : null;
		}
		if (TaiPanElementTypes.EscortShipsOrder_4006 == req.getElementType()) {
			return req.getTarget() == null ? getCreateStartOutgoingEscortShipsOrder_4006Command(req) : getCreateCompleteIncomingEscortShipsOrder_4006Command(req);
		}
		if (TaiPanElementTypes.BesiegePortOrder_4005 == req.getElementType()) {
			return req.getTarget() == null ? getCreateStartOutgoingBesiegePortOrder_4005Command(req) : getCreateCompleteOutgoingBesiegePortOrder_4005Command(req);
		}
		if (TaiPanElementTypes.PortRegister_4007 == req.getElementType()) {
			return req.getTarget() == null ? getCreateStartIncomingPortRegister_4007Command(req) : getCreateCompleteIncomingPortRegister_4007Command(req);
		}
		return super.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getCreateStartOutgoingShipDestination_4001Command(CreateRelationshipRequest req) {
		return getGEFWrapper(new ShipDestinationCreateCommand(req));
	}

	/**
	 * @generated
	 */
	protected Command getCreateStartOutgoingEscortShipsOrder_4006Command(CreateRelationshipRequest req) {
		EObject sourceEObject = req.getSource();
		if (false == sourceEObject instanceof Warship) {
			return UnexecutableCommand.INSTANCE;
		}
		Warship source = (Warship) sourceEObject;
		if (!TaiPanBaseItemSemanticEditPolicy.LinkConstraints.canCreateEscortShipsOrder_4006(source, null)) {
			return UnexecutableCommand.INSTANCE;
		}
		return new Command() {
		};
	}

	/**
	 * @generated
	 */
	protected Command getCreateCompleteIncomingEscortShipsOrder_4006Command(CreateRelationshipRequest req) {
		EObject sourceEObject = req.getSource();
		EObject targetEObject = req.getTarget();
		if (false == sourceEObject instanceof Warship || false == targetEObject instanceof Ship) {
			return UnexecutableCommand.INSTANCE;
		}
		Warship source = (Warship) sourceEObject;
		Ship target = (Ship) targetEObject;
		if (!TaiPanBaseItemSemanticEditPolicy.LinkConstraints.canCreateEscortShipsOrder_4006(source, target)) {
			return UnexecutableCommand.INSTANCE;
		}
		if (req.getContainmentFeature() == null) {
			req.setContainmentFeature(TaiPanPackage.eINSTANCE.getWarship_EscortOrder());
		}
		return getGEFWrapper(new EscortShipsOrderCreateCommand(req, source, target));
	}

	/**
	 * @generated
	 */
	protected Command getCreateStartOutgoingBesiegePortOrder_4005Command(CreateRelationshipRequest req) {
		EObject sourceEObject = req.getSource();
		if (false == sourceEObject instanceof Warship) {
			return UnexecutableCommand.INSTANCE;
		}
		Warship source = (Warship) sourceEObject;
		if (!TaiPanBaseItemSemanticEditPolicy.LinkConstraints.canCreateBesiegePortOrder_4005(source, null)) {
			return UnexecutableCommand.INSTANCE;
		}
		return new Command() {
		};
	}

	/**
	 * @generated
	 */
	protected Command getCreateCompleteOutgoingBesiegePortOrder_4005Command(CreateRelationshipRequest req) {
		EObject sourceEObject = req.getTarget();
		EObject targetEObject = req.getSource();
		if (false == sourceEObject instanceof Warship || false == targetEObject instanceof Port) {
			return UnexecutableCommand.INSTANCE;
		}
		Warship source = (Warship) sourceEObject;
		Port target = (Port) targetEObject;
		if (!TaiPanBaseItemSemanticEditPolicy.LinkConstraints.canCreateBesiegePortOrder_4005(source, target)) {
			return UnexecutableCommand.INSTANCE;
		}
		if (req.getContainmentFeature() == null) {
			req.setContainmentFeature(TaiPanPackage.eINSTANCE.getWarship_AttackOrders());
		}
		return getGEFWrapper(new BesiegePortOrderCreateCommand(req, source, target));
	}

	/**
	 * @generated
	 */
	protected Command getCreateStartIncomingPortRegister_4007Command(CreateRelationshipRequest req) {
		return getGEFWrapper(new PortRegisterCreateCommand(req));
	}

	/**
	 * @generated
	 */
	protected Command getCreateCompleteIncomingPortRegister_4007Command(CreateRelationshipRequest req) {
		return getGEFWrapper(new PortRegisterCreateCommand(req));
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case EscortShipsOrderEditPart.VISUAL_ID:
			return getGEFWrapper(new EscortShipsOrderReorientCommand(req));
		case BesiegePortOrderEditPart.VISUAL_ID:
			return getGEFWrapper(new BesiegePortOrderReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

	/**
	 * Returns command to reorient EReference based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientReferenceRelationshipCommand(ReorientReferenceRelationshipRequest req) {
		switch (getVisualID(req)) {
		case ShipDestinationEditPart.VISUAL_ID:
			return getGEFWrapper(new ShipDestinationReorientCommand(req));
		case PortRegisterEditPart.VISUAL_ID:
			return getGEFWrapper(new PortRegisterReorientCommand(req));
		}
		return super.getReorientReferenceRelationshipCommand(req);
	}

}
