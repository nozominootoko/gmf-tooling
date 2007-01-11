/*
 * Copyright (c) 2006 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Dmitry Stadnik (Borland) - initial API and implementation
 */
package org.eclipse.gmf.examples.taipan.gmf.editor.providers;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.Diagnostician;

import org.eclipse.emf.transaction.TransactionalEditingDomain;

import org.eclipse.emf.transaction.util.TransactionUtil;

import org.eclipse.emf.validation.model.EvaluationMode;

import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;

import org.eclipse.gmf.examples.taipan.gmf.editor.edit.parts.AquatoryEditPart;

import org.eclipse.gmf.examples.taipan.gmf.editor.part.TaiPanDiagramEditorPlugin;
import org.eclipse.gmf.examples.taipan.gmf.editor.part.TaiPanDiagramEditorUtil;
import org.eclipse.gmf.examples.taipan.gmf.editor.part.TaiPanVisualIDRegistry;

import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider;

import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;

import org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;

import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;

import org.eclipse.gmf.runtime.emf.core.util.EMFCoreUtil;

import org.eclipse.gmf.runtime.notation.View;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

import org.eclipse.jface.dialogs.ErrorDialog;

import org.eclipse.jface.operation.IRunnableWithProgress;

import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * @generated
 */
public class TaiPanValidationProvider extends AbstractContributionItemProvider {

	/**
	 * @generated
	 */
	private static boolean constraintsActive = false;

	/**
	 * @generated
	 */
	public static boolean shouldConstraintsBePrivate() {
		return false;
	}

	/**
	 * @generated
	 */
	protected IAction createAction(String actionId, IWorkbenchPartDescriptor partDescriptor) {
		if (ValidateAction.VALIDATE_ACTION_KEY.equals(actionId)) {
			return new ValidateAction(partDescriptor);
		}
		return super.createAction(actionId, partDescriptor);
	}

	/**
	 * @generated
	 */
	public static class ValidateAction extends Action {

		/**
		 * @generated
		 */
		public static final String VALIDATE_ACTION_KEY = "validateAction"; //$NON-NLS-1$
		/**
		 * @generated
		 */
		private IWorkbenchPartDescriptor workbenchPartDescriptor;

		/**
		 * @generated
		 */
		public ValidateAction(IWorkbenchPartDescriptor workbenchPartDescriptor) {
			setId(VALIDATE_ACTION_KEY);
			setText("Validate");
			this.workbenchPartDescriptor = workbenchPartDescriptor;
		}

		/**
		 * @generated
		 */
		public void run() {
			IWorkbenchPart workbenchPart = workbenchPartDescriptor.getPartPage().getActivePart();
			if (workbenchPart instanceof IDiagramWorkbenchPart) {
				final IDiagramWorkbenchPart part = (IDiagramWorkbenchPart) workbenchPart;
				try {

					new IRunnableWithProgress() {

						public void run(IProgressMonitor monitor) throws InterruptedException, InvocationTargetException {
							runValidation(part.getDiagramEditPart(), part.getDiagram());
						}
					}

					.run(new NullProgressMonitor());
				} catch (Exception e) {
					TaiPanDiagramEditorPlugin.getInstance().logError("Validation action failed", e); //$NON-NLS-1$
				}
			}
		}

		/** 
		 * @generated
		 */
		public static void runValidation(View view) {
			try {
				if (TaiPanDiagramEditorUtil.openDiagram(view.eResource())) {
					IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
					if (editorPart instanceof IDiagramWorkbenchPart) {
						runValidation(((IDiagramWorkbenchPart) editorPart).getDiagramEditPart(), view);
					} else {
						runNonUIValidation(view);
					}
				}
			} catch (Exception e) {
				TaiPanDiagramEditorPlugin.getInstance().logError("Validation action failed", e); //$NON-NLS-1$
			}
		}

		/**
		 * @generated
		 */
		public static void runNonUIValidation(View view) {
			DiagramEditPart diagramEditPart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(view.getDiagram());
			runValidation(diagramEditPart, view);
		}

		/**
		 * @generated
		 */
		public static void runValidation(DiagramEditPart diagramEditPart, View view) {
			final View target = view;
			final DiagramEditPart diagramPart = diagramEditPart;
			Runnable task = new Runnable() {

				public void run() {
					try {
						constraintsActive = true;
						validate(diagramPart, target);
					} finally {
						constraintsActive = false;
					}
				}
			};
			TransactionalEditingDomain txDomain = TransactionUtil.getEditingDomain(target);
			if (txDomain != null) {
				try {
					txDomain.runExclusive(task);
				} catch (Exception e) {
					TaiPanDiagramEditorPlugin.getInstance().logError("Validation action failed", e); //$NON-NLS-1$
				}
			} else {
				task.run();
			}
		}

		/**
		 * @generated
		 */
		private static Diagnostic runEMFValidator(View target) {
			if (target.isSetElement() && target.getElement() != null) {
				return new Diagnostician() {

					public String getObjectLabel(EObject eObject) {
						return EMFCoreUtil.getQualifiedName(eObject, true);
					}
				}.validate(target.getElement());
			}
			return Diagnostic.OK_INSTANCE;
		}

		/**
		 * @generated
		 */
		private static void validate(DiagramEditPart diagramEditPart, View target) {
			Diagnostic diagnostic = runEMFValidator(target);
			if (diagnostic.getSeverity() != Diagnostic.OK) {
				ErrorDialog.openError(Display.getCurrent().getActiveShell(), "Validation", "Validation failed.", BasicDiagnostic.toIStatus(diagnostic));
				return;
			}
			IBatchValidator validator = (IBatchValidator) ModelValidationService.getInstance().newValidator(EvaluationMode.BATCH);
			validator.setIncludeLiveConstraints(true);
			if (target.isSetElement() && target.getElement() != null) {
				IStatus status = validator.validate(target.getElement());
				if (status.getSeverity() != IStatus.OK) {
					ErrorDialog.openError(Display.getCurrent().getActiveShell(), "Validation", "Validation failed.", status);
					return;
				}
			}

			MessageBox mb = new MessageBox(Display.getCurrent().getActiveShell(), SWT.OK);
			mb.setText("Validation");
			mb.setMessage("Model is valid.");
			mb.open();
		}
	}

	/**
	 * @generated
	 */
	static boolean isInDefaultEditorContext(Object object) {
		if (shouldConstraintsBePrivate() && !constraintsActive) {
			return false;
		}
		if (object instanceof View) {
			return constraintsActive && AquatoryEditPart.MODEL_ID.equals(TaiPanVisualIDRegistry.getModelID((View) object));
		}
		return true;
	}

} //TaiPanValidationProvider
