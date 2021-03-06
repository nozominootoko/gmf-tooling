/*
 * Copyright (c) 2006, 2007 Borland Software Corporation.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Richard Gronback (Borland) - initial API and implementation
 */
package org.eclipse.gmf.examples.mindmap.rcp.part;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.examples.mindmap.rcp.edit.parts.MapEditPart;
import org.eclipse.gmf.runtime.lite.commands.WrappingCommand;
import org.eclipse.gmf.runtime.lite.services.IDiagramLayouter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorPart;

/**
 * @generated
 */
public class MindmapNewDiagramFileWizard extends Wizard {
	/**
	 * @generated
	 */
	private TransactionalEditingDomain myEditingDomain;
	/**
	 * @generated
	 */
	private URI mySelectedModelFileURI;
	/**
	 * @generated
	 */
	private DiagramURISelectorPage myDiagramURISelectorPage;
	/**
	 * @generated
	 */
	private EObject myDiagramRoot;

	/**
	 * @generated
	 */
	public MindmapNewDiagramFileWizard(URI selectedModelFileURI,
			EObject diagramRoot, TransactionalEditingDomain editingDomain) {
		assert editingDomain != null : "Null editingDomain in MindmapNewDiagramFileWizard constructor"; //$NON-NLS-1$
		mySelectedModelFileURI = selectedModelFileURI;
		myDiagramRoot = diagramRoot;
		myEditingDomain = editingDomain;
		setDefaultPageImageDescriptor(MindmapDiagramEditorPlugin
				.getBundledImageDescriptor("icons/wizban/NewMindmapWizard.gif")); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void addPages() {
		if (mySelectedModelFileURI == null) {
			addPage(new SourceURISelectorPage());
		}
		myDiagramURISelectorPage = new DiagramURISelectorPage();
		addPage(myDiagramURISelectorPage);
		addPage(new RootElementSelectorPage());
	}

	/**
	 * @generated
	 */
	public boolean performFinish() {
		ResourceSet resourceSet = myEditingDomain.getResourceSet();
		URI diagramFileURI = myDiagramURISelectorPage.getNewFileURI();
		final Resource diagramResource = resourceSet
				.createResource(diagramFileURI);
		AbstractCommand command = new AbstractCommand(
				Messages.MindmapNewDiagramFileWizard_CommandLabel) {
			private Diagram myCreatedDiagram;

			protected boolean prepare() {
				int diagramVID = MindmapVisualIDRegistry
						.getDiagramVisualID(myDiagramRoot);
				if (diagramVID != MapEditPart.VISUAL_ID) {
					return false;
				}
				return true;
			}

			public void execute() {
				myCreatedDiagram = MindmapDiagramEditorUtil
						.createDiagramFor(myDiagramRoot);
				assert myCreatedDiagram != null;
				diagramResource.getContents().add(myCreatedDiagram);
				diagramResource.getContents()
						.add(myCreatedDiagram.getElement());
			}

			public void redo() {
				execute();
			}

			public boolean canUndo() {
				return false;
			}
		};
		try {
			new WrappingCommand(myEditingDomain, command).execute();
			diagramResource.save(MindmapDiagramEditorUtil.getSaveOptions());
			IEditorPart editor = MindmapDiagramEditorUtil
					.openEditor(diagramFileURI);
			if (editor != null) {
				IDiagramLayouter layouter = (IDiagramLayouter) editor
						.getAdapter(IDiagramLayouter.class);
				if (layouter != null) {
					GraphicalViewer graphicalViewer = (GraphicalViewer) editor
							.getAdapter(GraphicalViewer.class);
					if (graphicalViewer != null) {
						Command layoutCommand = layouter
								.layout((GraphicalEditPart) graphicalViewer
										.getContents());
						if (layoutCommand != null && layoutCommand.canExecute()) {
							graphicalViewer.getEditDomain().getCommandStack()
									.execute(
											new WrappingCommand(
													myEditingDomain,
													layoutCommand));
							diagramResource.save(MindmapDiagramEditorUtil
									.getSaveOptions());
						}
					}
				}
			}
		} catch (IOException ex) {
			MindmapDiagramEditorPlugin
					.getInstance()
					.logError(
							"Save operation failed for: " + diagramFileURI.toString(), ex); //$NON-NLS-1$
		}
		return true;
	}

	/**
	 * @generated
	 */
	private abstract class URISelectorPage extends WizardPage {
		/**
		 * @generated
		 */
		private Text fileField;

		/**
		 * @generated
		 */
		protected URISelectorPage(String name) {
			super(name);
		}

		/**
		 * @generated
		 */
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE);
			{
				GridLayout layout = new GridLayout();
				layout.numColumns = 1;
				layout.verticalSpacing = 12;
				composite.setLayout(layout);

				GridData data = new GridData();
				data.verticalAlignment = GridData.FILL;
				data.grabExcessVerticalSpace = true;
				data.horizontalAlignment = GridData.FILL;
				composite.setLayoutData(data);
			}
			Label resourceURILabel = new Label(composite, SWT.LEFT);
			{
				resourceURILabel
						.setText(Messages.MindmapNewDiagramFileWizard_FileLabel);

				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				resourceURILabel.setLayoutData(data);
			}

			Composite fileComposite = new Composite(composite, SWT.NONE);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				fileComposite.setLayoutData(data);

				GridLayout layout = new GridLayout();
				layout.marginHeight = 0;
				layout.marginWidth = 0;
				layout.numColumns = 2;
				fileComposite.setLayout(layout);
			}

			fileField = new Text(fileComposite, SWT.BORDER);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				data.horizontalSpan = 1;
				fileField.setLayoutData(data);
			}

			fileField.addModifyListener(validator);
			Button resourceURIBrowseFileSystemButton = new Button(
					fileComposite, SWT.PUSH);
			resourceURIBrowseFileSystemButton
					.setText(Messages.MindmapNewDiagramFileWizard_BrowseButton);

			resourceURIBrowseFileSystemButton
					.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent event) {
							String fileExtension = getFileExtension();
							String filePath = MindmapDiagramEditorUtil
									.openFilePathDialog(getShell(),
											"*." + fileExtension, SWT.OPEN); //$NON-NLS-1$
							if (filePath != null) {
								if (!filePath.endsWith("." + fileExtension)) { //$NON-NLS-1$
									filePath = filePath + "." + fileExtension; //$NON-NLS-1$
								}
								fileField.setText(filePath);
							}
						}
					});
			setPageComplete(validatePage());
			setControl(composite);
		}

		/**
		 * @generated
		 */
		protected ModifyListener validator = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				setPageComplete(validatePage());
			}
		};

		/**
		 * @generated
		 */
		protected final void setFile(File file) {
			fileField.setText(file.getPath());
		}

		/**
		 * @generated
		 */
		protected boolean validatePage() {
			URI fileURI = getFileURI();
			if (fileURI == null || fileURI.isEmpty()) {
				setErrorMessage(null);
				return false;
			}
			if (fileURI.isFile()) {
				File file = new File(fileURI.toFileString());
				String fileProblem = validateFile(file);
				if (fileProblem != null) {
					setErrorMessage(fileProblem);
					return false;
				}
			}
			String requiredExt = getFileExtension();
			String enteredExt = fileURI.fileExtension();
			if (enteredExt == null || !enteredExt.equals(requiredExt)) {
				setErrorMessage(NLS
						.bind(
								Messages.MindmapNewDiagramFileWizard_IncorrectExtension,
								requiredExt));
				return false;
			}
			setErrorMessage(null);
			return true;
		}

		/**
		 * Checks the given file and returns the error message if there are problems or <code>null</code> if the file is OK.
		 * @generated
		 */
		protected abstract String validateFile(File file);

		/**
		 * @generated
		 */
		protected abstract String getFileExtension();

		/**
		 * @generated
		 */
		protected URI getFileURI() {
			try {
				return URI.createFileURI(fileField.getText());
			} catch (Exception exception) {
			}
			return null;
		}
	}

	/**
	 * @generated
	 */
	private class SourceURISelectorPage extends URISelectorPage {
		/**
		 * @generated
		 */
		public SourceURISelectorPage() {
			super(
					Messages.MindmapNewDiagramFileWizard_SourceURISelectorPageName);
			setTitle(Messages.MindmapNewDiagramFileWizard_SourceURISelectorPageTitle);
			setDescription(Messages.MindmapNewDiagramFileWizard_SourceURISelectorPageDescription);
		}

		/**
		 * @generated
		 */
		protected String getFileExtension() {
			return "mindmap"; //$NON-NLS-1$
		}

		/**
		 * @generated
		 */
		protected boolean validatePage() {
			if (super.validatePage()) {
				mySelectedModelFileURI = getFileURI();
				return true;
			}
			return false;
		}

		/**
		 * @generated
		 */
		protected String validateFile(File file) {
			if (!file.exists()) {
				return Messages.MindmapNewDiagramFileWizard_NoSourceFile;
			}
			return null;
		}
	}

	/**
	 * @generated
	 */
	private class DiagramURISelectorPage extends URISelectorPage {
		/**
		 * @generated
		 */
		private URI myNewFileURI;

		/**
		 * @generated
		 */
		public DiagramURISelectorPage() {
			super(
					Messages.MindmapNewDiagramFileWizard_DiagramURISelectorPageName);
			setTitle(Messages.MindmapNewDiagramFileWizard_DiagramURISelectorPageTitle);
			setDescription(Messages.MindmapNewDiagramFileWizard_DiagramURISelectorPageDescription);
		}

		/**
		 * @generated
		 */
		protected String getFileExtension() {
			return "mmd"; //$NON-NLS-1$
		}

		/**
		 * @generated
		 */
		public void setVisible(boolean visible) {
			super.setVisible(visible);
			if (visible && mySelectedModelFileURI != null
					&& getFileURI() == null && mySelectedModelFileURI.isFile()) {
				File originalFile = new File(mySelectedModelFileURI
						.toFileString());
				String originalFileName = mySelectedModelFileURI
						.trimFileExtension().lastSegment();
				File parentFile = originalFile.getParentFile();
				File newFile = new File(parentFile, originalFileName
						+ getFileExtension());
				for (int i = 1; i > 0 && newFile.exists(); i++) {
					newFile = new File(parentFile, originalFileName + i
							+ getFileExtension());
				}
				if (newFile.exists()) {
					return; //failed to set name that does not exist, just leave empty.
				}
				setFile(newFile);
			}
		}

		/**
		 * @generated
		 */
		protected boolean validatePage() {
			myNewFileURI = null;
			if (super.validatePage()) {
				myNewFileURI = getFileURI();
				return true;
			}
			return false;
		}

		/**
		 * @generated
		 */
		public URI getNewFileURI() {
			return myNewFileURI;
		}

		/**
		 * @generated
		 */
		protected String validateFile(File file) {
			if (file.exists()) {
				return Messages.MindmapNewDiagramFileWizard_DiagramFileExists;
			}
			return null;
		}
	}

	/**
	 * @generated
	 */
	private class RootElementSelectorPage extends WizardPage implements
			ISelectionChangedListener {
		/**
		 * @generated
		 */
		private TreeViewer myTreeViewer;

		/**
		 * @generated
		 */
		protected RootElementSelectorPage() {
			super(
					Messages.MindmapNewDiagramFileWizard_RootElementSelectorPageName);
			setTitle(Messages.MindmapNewDiagramFileWizard_RootElementSelectorPageTitle);
			setDescription(Messages.MindmapNewDiagramFileWizard_RootElementSelectorPageDescription);
		}

		/**
		 * @generated
		 */
		public void createControl(Composite parent) {
			initializeDialogUnits(parent);
			Composite topLevel = new Composite(parent, SWT.NONE);
			topLevel.setLayout(new GridLayout());
			topLevel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL
					| GridData.HORIZONTAL_ALIGN_FILL));
			topLevel.setFont(parent.getFont());
			setControl(topLevel);
			createModelBrowser(topLevel);
			setPageComplete(validatePage());
		}

		/**
		 * @generated
		 */
		private void createModelBrowser(Composite parent) {
			Composite panel = new Composite(parent, SWT.NONE);
			panel.setLayoutData(new GridData(GridData.FILL_BOTH));
			GridLayout layout = new GridLayout();
			layout.marginWidth = 0;
			panel.setLayout(layout);

			Label label = new Label(panel, SWT.NONE);
			label
					.setText(Messages.MindmapNewDiagramFileWizard_RootElementSelectorPageLabelText);
			label.setLayoutData(new GridData(
					GridData.HORIZONTAL_ALIGN_BEGINNING));
			myTreeViewer = new TreeViewer(panel, SWT.SINGLE | SWT.H_SCROLL
					| SWT.V_SCROLL | SWT.BORDER);
			GridData layoutData = new GridData(GridData.FILL_BOTH);
			layoutData.heightHint = 300;
			layoutData.widthHint = 300;
			myTreeViewer.getTree().setLayoutData(layoutData);
			myTreeViewer.setContentProvider(new AdapterFactoryContentProvider(
					MindmapDiagramEditorPlugin.getInstance()
							.getItemProvidersAdapterFactory()));
			myTreeViewer.setLabelProvider(new AdapterFactoryLabelProvider(
					MindmapDiagramEditorPlugin.getInstance()
							.getItemProvidersAdapterFactory()));
			myTreeViewer.addSelectionChangedListener(this);
		}

		/**
		 * @generated
		 */
		public void setVisible(boolean visible) {
			if (visible) {
				myTreeViewer.setInput(myEditingDomain.getResourceSet()
						.getResource(mySelectedModelFileURI, true));
				if (myDiagramRoot != null) {
					myTreeViewer.setSelection(new StructuredSelection(
							myDiagramRoot));
				}
			}
			super.setVisible(visible);
		}

		/**
		 * @generated
		 */
		public void selectionChanged(SelectionChangedEvent event) {
			myDiagramRoot = null;
			if (event.getSelection() instanceof IStructuredSelection) {
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				if (selection.size() == 1) {
					Object selectedElement = selection.getFirstElement();
					if (selectedElement instanceof IWrapperItemProvider) {
						selectedElement = ((IWrapperItemProvider) selectedElement)
								.getValue();
					}
					if (selectedElement instanceof FeatureMap.Entry) {
						selectedElement = ((FeatureMap.Entry) selectedElement)
								.getValue();
					}
					if (selectedElement instanceof EObject) {
						myDiagramRoot = (EObject) selectedElement;
					}
				}
			}
			setPageComplete(validatePage());
		}

		/**
		 * @generated
		 */
		private boolean validatePage() {
			if (myDiagramRoot == null) {
				setErrorMessage(Messages.MindmapNewDiagramFileWizard_RootElementSelectorPageNoRootSelected);
				return false;
			}
			boolean result = MapEditPart.VISUAL_ID == MindmapVisualIDRegistry
					.getDiagramVisualID(myDiagramRoot);
			if (!result) {
				setErrorMessage(Messages.MindmapNewDiagramFileWizard_RootElementSelectorPageInvalidRootSelected);
			}
			return result;
		}
	}
}
