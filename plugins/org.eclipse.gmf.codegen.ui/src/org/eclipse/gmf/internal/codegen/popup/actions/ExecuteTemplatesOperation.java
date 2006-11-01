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
package org.eclipse.gmf.internal.codegen.popup.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.gmf.codegen.gmfgen.GenDiagram;
import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator;
import org.eclipse.gmf.codegen.util.Generator;
import org.eclipse.gmf.internal.codegen.CodeGenUIPlugin;
import org.eclipse.gmf.internal.common.migrate.ModelLoadHelper;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author dstadnik
 */
public class ExecuteTemplatesOperation implements IRunnableWithProgress {

	private static final String ASK_OK = "ask_ok"; //$NON-NLS-1$

	private String name;

	private Shell shell;

	private URI genModelURI;

	protected IStatus myRunStatus;

	private GenEditorGenerator myGenModel;

	public String getName() {
		if (name == null) {
			return "Execute Templates"; //$NON-NLS-1$
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Shell getShell() {
		if (shell == null) {
			return Display.getDefault().getActiveShell();
		}
		return shell;
	}

	public void setShell(Shell shell) {
		this.shell = shell;
	}

	public URI getGenModelURI() {
		return genModelURI;
	}

	public void setGenModelURI(URI uri) {
		genModelURI = uri;
	}

	// TODO Jobs
	public void run() {
		if (getGenModelURI() == null) {
			return;
		}
		try {
			IStatus loadStatus = loadGenModel();
			if (!canProcessGMFGenModel(loadStatus)) {
				return;
			}

			assert getGenModel() != null;
			IStatus isGenModelValid = validateGenModel();
			if (!isGenModelValid.isOK()) {
				final String[] buttons = new String[] { IDialogConstants.PROCEED_LABEL, IDialogConstants.CANCEL_LABEL };
				final int[] buttonIDs = new int[] { IDialogConstants.PROCEED_ID, IDialogConstants.CANCEL_ID };
				final String msg = CodeGenUIPlugin.getBundleString("generatecode.badsrc"); //$NON-NLS-1$
				ErrorDialogEx dlg = new ErrorDialogEx(getShell(), getName(), msg, isGenModelValid, buttons, buttonIDs, 0);
				if (dlg.open() == IDialogConstants.CANCEL_ID) {
					return;
				}
			}

			doRunWithStatus();

			if (getRunStatus().isOK()) {
				if (!MessageDialogWithToggle.ALWAYS.equals(getPreferences().getString(ASK_OK))) {
					String okMsg = CodeGenUIPlugin.getBundleString("generatecode.ok"); //$NON-NLS-1$
					String neverMsg = CodeGenUIPlugin.getBundleString("generatecode.neveragain"); //$NON-NLS-1$
					MessageDialogWithToggle.openInformation(getShell(), getName(), okMsg, neverMsg, false, getPreferences(), ASK_OK);
				}
			} else if (myRunStatus.matches(IStatus.ERROR)) {
				CodeGenUIPlugin.getDefault().getLog().log(getRunStatus());
				ErrorDialog.openError(getShell(), getName(), CodeGenUIPlugin.getBundleString("generatecode.err"), getRunStatus()); //$NON-NLS-1$
			} else if (myRunStatus.matches(IStatus.WARNING)) {
				ErrorDialog.openError(getShell(), getName(), CodeGenUIPlugin.getBundleString("generatecode.warn"), getRunStatus()); //$NON-NLS-1$
			} else if (myRunStatus.matches(IStatus.INFO)) {
				ErrorDialog.openError(getShell(), getName(), CodeGenUIPlugin.getBundleString("generatecode.info"), getRunStatus()); //$NON-NLS-1$
			}
		} catch (InterruptedException ex) {
			// presumably, user canceled the operation, don't bother him with additional messages
		} finally {
			unloadGenModel();
		}
	}

	/**
	 * Checks if loaded gmfgen model can be processed further.
	 * <p>
	 * Note: Must be called after {@link #loadGenModel()}.
	 * 
	 * @param loadStatus
	 *            the result status of loading the gmfgen model
	 * @param action
	 *            the action in execution
	 * @return <code>true</code> if gmfgen model is available with OK status or in case of load problems, user decided to proceed, <code>false</code> otherwise.
	 */
	private boolean canProcessGMFGenModel(IStatus loadStatus) {
		if (!loadStatus.isOK()) {
			String[] buttons = new String[] { IDialogConstants.PROCEED_LABEL, IDialogConstants.CANCEL_LABEL };
			int[] buttonIDs = new int[] { IDialogConstants.PROCEED_ID, IDialogConstants.CANCEL_ID };
			if (myGenModel == null) {
				// we cannot proceed further as there is no gmfgen, allow only cancel
				buttons = new String[] { buttons[1] };
				buttonIDs = new int[] { buttonIDs[1] };
			}
			ErrorDialogEx dlg = new ErrorDialogEx(getShell(), getName(), CodeGenUIPlugin.getBundleString("generatecode.badsrc"), //$NON-NLS-1$ 
					loadStatus, buttons, buttonIDs, 0);
			if (dlg.open() == IDialogConstants.CANCEL_ID) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return
	 */
	private void doRunWithStatus() throws InterruptedException {
		myRunStatus = Status.CANCEL_STATUS;
		try {
			new ProgressMonitorDialog(getShell()).run(true, true, this);
		} catch (InvocationTargetException ex) {
			Throwable targetException = ex.getTargetException();
			if (targetException instanceof CoreException) {
				myRunStatus = ((CoreException) targetException).getStatus();
			} else {
				myRunStatus = new Status(IStatus.ERROR, CodeGenUIPlugin.getPluginID(), 0, "Exception occurred while generating code", targetException);
			}
		}
	}

	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		Generator g = new Generator(getGenModel(), CodeGenUIPlugin.getDefault().getEmitters(getGenModel()));
		g.run(monitor);
		myRunStatus = g.getRunStatus();
	}

	private IStatus getRunStatus() {
		return myRunStatus;
	}

	protected final GenEditorGenerator getGenModel() {
		return myGenModel;
	}

	private IStatus loadGenModel() {
		ResourceSet srcResSet = new ResourceSetImpl();
		ModelLoadHelper loadHelper = new ModelLoadHelper(srcResSet, getGenModelURI());
		Object root = loadHelper.getContentsRoot();
		if (root instanceof GenDiagram) {
			myGenModel = ((GenDiagram) root).getEditorGen();
		} else if (root instanceof GenEditorGenerator) {
			myGenModel = (GenEditorGenerator) root;
		}
		if (myGenModel != null && myGenModel.getDomainGenModel() != null) {
			myGenModel.getDomainGenModel().reconcile();
		}
		return loadHelper.getStatus();
	}

	private void unloadGenModel() {
		if (myGenModel != null && myGenModel.eResource() != null) {
			myGenModel.eResource().unload();
		}
		myGenModel = null;
	}

	private IStatus validateGenModel() {
		return BasicDiagnostic.toIStatus(Diagnostician.INSTANCE.validate(getGenModel()));
	}

	private static IPreferenceStore getPreferences() {
		return CodeGenUIPlugin.getDefault().getPreferenceStore();
	}
}
