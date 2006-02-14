/*
 * Copyright (c) 2005 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.bridge.genmodel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.codegen.gmfgen.CompositeFeatureLabelModelFacet;
import org.eclipse.gmf.codegen.gmfgen.EntryBase;
import org.eclipse.gmf.codegen.gmfgen.FeatureLabelModelFacet;
import org.eclipse.gmf.codegen.gmfgen.FeatureLinkModelFacet;
import org.eclipse.gmf.codegen.gmfgen.GMFGenFactory;
import org.eclipse.gmf.codegen.gmfgen.GenAuditContainer;
import org.eclipse.gmf.codegen.gmfgen.GenAuditRule;
import org.eclipse.gmf.codegen.gmfgen.GenChildContainer;
import org.eclipse.gmf.codegen.gmfgen.GenChildNode;
import org.eclipse.gmf.codegen.gmfgen.GenCompartment;
import org.eclipse.gmf.codegen.gmfgen.GenDiagram;
import org.eclipse.gmf.codegen.gmfgen.GenEditorGenerator;
import org.eclipse.gmf.codegen.gmfgen.GenElementInitializer;
import org.eclipse.gmf.codegen.gmfgen.GenFeatureSeqInitializer;
import org.eclipse.gmf.codegen.gmfgen.GenFeatureValueSpec;
import org.eclipse.gmf.codegen.gmfgen.GenLink;
import org.eclipse.gmf.codegen.gmfgen.GenLinkConstraints;
import org.eclipse.gmf.codegen.gmfgen.GenLinkLabel;
import org.eclipse.gmf.codegen.gmfgen.GenNode;
import org.eclipse.gmf.codegen.gmfgen.GenNodeLabel;
import org.eclipse.gmf.codegen.gmfgen.GenPlugin;
import org.eclipse.gmf.codegen.gmfgen.GenSeverity;
import org.eclipse.gmf.codegen.gmfgen.GenTopLevelNode;
import org.eclipse.gmf.codegen.gmfgen.LabelModelFacet;
import org.eclipse.gmf.codegen.gmfgen.LinkEntry;
import org.eclipse.gmf.codegen.gmfgen.LinkLabelAlignment;
import org.eclipse.gmf.codegen.gmfgen.LinkModelFacet;
import org.eclipse.gmf.codegen.gmfgen.ModelElementSelector;
import org.eclipse.gmf.codegen.gmfgen.NodeEntry;
import org.eclipse.gmf.codegen.gmfgen.Palette;
import org.eclipse.gmf.codegen.gmfgen.TextLabelModelFacet;
import org.eclipse.gmf.codegen.gmfgen.ToolGroup;
import org.eclipse.gmf.codegen.gmfgen.TypeLinkModelFacet;
import org.eclipse.gmf.codegen.gmfgen.TypeModelFacet;
import org.eclipse.gmf.codegen.gmfgen.ValueExpression;
import org.eclipse.gmf.codegen.gmfgen.Viewmap;
import org.eclipse.gmf.gmfgraph.Compartment;
import org.eclipse.gmf.internal.bridge.NaiveIdentifierDispenser;
import org.eclipse.gmf.internal.bridge.VisualIdentifierDispenser;
import org.eclipse.gmf.internal.bridge.naming.gen.GenModelNamingMediator;
import org.eclipse.gmf.mappings.AbstractNodeMapping;
import org.eclipse.gmf.mappings.AuditContainer;
import org.eclipse.gmf.mappings.AuditRule;
import org.eclipse.gmf.mappings.CanvasMapping;
import org.eclipse.gmf.mappings.ChildNodeMapping;
import org.eclipse.gmf.mappings.CompartmentMapping;
import org.eclipse.gmf.mappings.Constraint;
import org.eclipse.gmf.mappings.ElementInitializer;
import org.eclipse.gmf.mappings.FeatureSeqInitializer;
import org.eclipse.gmf.mappings.FeatureValueSpec;
import org.eclipse.gmf.mappings.LabelMapping;
import org.eclipse.gmf.mappings.LinkConstraints;
import org.eclipse.gmf.mappings.LinkLabelMapping;
import org.eclipse.gmf.mappings.LinkMapping;
import org.eclipse.gmf.mappings.Mapping;
import org.eclipse.gmf.mappings.NodeLabelMapping;
import org.eclipse.gmf.mappings.NodeMapping;
import org.eclipse.gmf.mappings.Severity;
import org.eclipse.gmf.mappings.ToolOwner;
import org.eclipse.gmf.tooldef.AbstractTool;
import org.eclipse.gmf.tooldef.BundleImage;
import org.eclipse.gmf.tooldef.CreationTool;
import org.eclipse.gmf.tooldef.ToolContainer;

/**
 * Creates generation model from diagram definition.
 * @author artem
 */
public class DiagramGenModelTransformer extends MappingTransformer {

	private GenEditorGenerator myGenModel;
	private GenModelMatcher myGenModelMatch;
	private final DiagramRunTimeModelHelper myDRTHelper;
	private final ViewmapProducer myViewmaps = new InnerClassViewmapProducer();
	private final VisualIdentifierDispenser myVisualIDs;

	private final GenModelNamingMediator myNamingStrategy;

	public DiagramGenModelTransformer(DiagramRunTimeModelHelper drtHelper, GenModelNamingMediator namingStrategy) {
		myDRTHelper = drtHelper;
		myNamingStrategy = namingStrategy;
		myVisualIDs = new NaiveIdentifierDispenser();
	}

	/**
	 * Optionally set GenModel to match ECore elements against. 
	 * Should be invoked prior to {@link MappingTransformer#transform(Mapping)}, otherwise has no effect.
	 * Useful for tests (and other cases) when GenModel is not known to EMF 
	 * (and thus can't be obtained using EMF techniques).
	 * @param emfGenModel EMF GenModel for domain model
	 */
	public void setEMFGenModel(GenModel emfGenModel) {
		myGenModelMatch = new GenModelMatcher(emfGenModel);
	}

	public GenEditorGenerator getResult() {
		return getGenEssence();
	}

	private GenEditorGenerator getGenEssence() {
		if (myGenModel == null) {
			myGenModel = GMFGenFactory.eINSTANCE.createGenEditorGenerator();
		}
		// init editor as well - transformer does not set any property to it, just make sure it's not null
		if (myGenModel.getEditor() == null) {
			myGenModel.setEditor(GMFGenFactory.eINSTANCE.createGenEditorView());
		}
		return myGenModel;
	}

	private GenDiagram getGenDiagram() {
		if (getGenEssence().getDiagram() == null) {
			getGenEssence().setDiagram(GMFGenFactory.eINSTANCE.createGenDiagram());
		}
		return getGenEssence().getDiagram();
	}

	private GenPlugin getGenPlugin() {
		if (getGenEssence().getPlugin() == null) {
			getGenEssence().setPlugin(GMFGenFactory.eINSTANCE.createGenPlugin());
		}
		return getGenEssence().getPlugin();
	}

	private Palette getGenPalette() {
		Palette p = getGenDiagram().getPalette();
		if (p == null) {
			p = GMFGenFactory.eINSTANCE.createPalette();
			getGenDiagram().setPalette(p);
		}
		return p;
	}

	protected void process(CanvasMapping mapping) {
		assert mapping.getDomainModel() != null;
		if (myGenModelMatch == null) {
			myGenModelMatch = new GenModelMatcher(mapping.getDomainModel());
		}
		GenPackage primaryPackage = findGenPackage(mapping.getDomainModel());
		getGenEssence().setDomainGenModel(primaryPackage == null ? null : primaryPackage.getGenModel());
		if (getGenEssence().getDomainGenModel() != null) {
			getGenEssence().setModelID(getGenEssence().getDomainGenModel().getModelName());
		}
		getGenDiagram().setDomainDiagramElement(findGenClass(mapping.getDomainMetaElement()));
		getGenDiagram().setDiagramRunTimeClass(findRunTimeClass(mapping));
		getGenDiagram().setVisualID(myVisualIDs.get(getGenDiagram()));
		getGenPlugin().setName(mapping.getDomainModel().getName() + " Plugin");
		getGenDiagram().setViewmap(myViewmaps.create(mapping.getDiagramCanvas()));

		// set class names
		myNamingStrategy.feed(getGenDiagram(), mapping);
		
		// process audit rules
		if(mapping.eContainer() != null) {
			AuditContainer audits = ((Mapping)mapping.eContainer()).getAudits();
			if(audits != null) {
				getGenEssence().setAudits(createGenAuditContainer(audits));
			}
		}
	}

	protected void process(NodeMapping nme) {
		assertAbstractNodeMapping(nme);
		assert nme.getDiagramNode() != null;
		
		GenTopLevelNode genNode = GMFGenFactory.eINSTANCE.createGenTopLevelNode();
		getGenDiagram().getTopLevelNodes().add(genNode);
		genNode.setDiagramRunTimeClass(findRunTimeClass(nme));
		genNode.setModelFacet(createModelFacet(nme));
		genNode.setVisualID(myVisualIDs.get(genNode));
		if (nme.getEditFeature() != null) {
			FeatureLabelModelFacet modelFacet = GMFGenFactory.eINSTANCE.createFeatureLabelModelFacet();
			modelFacet.setMetaFeature(findGenFeature(nme.getEditFeature()));
			GenNodeLabel label = GMFGenFactory.eINSTANCE.createGenNodeLabel();
			label.setModelFacet(modelFacet);
			label.setVisualID(myVisualIDs.get(label));
			label.setDiagramRunTimeClass(getNodeLabelRunTimeClass());
			label.setViewmap(createLabelViewmap());

			// set class names
			myNamingStrategy.feed(label, nme, null);

			genNode.getLabels().add(label);
		}
		genNode.setViewmap(myViewmaps.create(nme.getDiagramNode()));
		handleNodeTool(nme, genNode);

		// set class names
		myNamingStrategy.feed(genNode, nme);
		
		processAbstractNode(nme, genNode);
	}

	private void process(ChildNodeMapping childNodeMapping, GenChildContainer container) {
		assertAbstractNodeMapping(childNodeMapping);
		assert childNodeMapping.getDiagramNode() != null;

		GenChildNode childNode = GMFGenFactory.eINSTANCE.createGenChildNode();
		childNode.setModelFacet(createModelFacet(childNodeMapping));
		
		childNode.setDiagramRunTimeClass(findRunTimeClass(childNodeMapping));
		childNode.setViewmap(myViewmaps.create(childNodeMapping.getDiagramNode()));
		childNode.setVisualID(myVisualIDs.get(childNode));

		// set class names
		myNamingStrategy.feed(childNode, childNodeMapping);

		if (childNodeMapping.getEditFeature() != null) {
			FeatureLabelModelFacet modelFacet = GMFGenFactory.eINSTANCE.createFeatureLabelModelFacet();
			modelFacet.setMetaFeature(findGenFeature(childNodeMapping.getEditFeature()));
			modelFacet.setDefaultText("<...>");
			GenNodeLabel label = GMFGenFactory.eINSTANCE.createGenNodeLabel();
			label.setModelFacet(modelFacet);
			label.setVisualID(myVisualIDs.get(label));
			label.setDiagramRunTimeClass(getNodeLabelRunTimeClass());
			label.setViewmap(createLabelViewmap());

			// set class names
			myNamingStrategy.feed(label, childNodeMapping, null);

			childNode.getLabels().add(label);
		}

		container.getChildNodes().add(childNode);
		getGenDiagram().getChildNodes().add(childNode);
		handleNodeTool(childNodeMapping, childNode);
		processAbstractNode(childNodeMapping, childNode);
		
		if (childNodeMapping.getChildMappings().size() > 0) {
			// TODO just layout from childNodeMapping.getDiagramNode()
			container.setListLayout(false);
		}
	}
	
	private void processAbstractNode(AbstractNodeMapping mapping, GenNode genNode) {
		Map compartments2GenCompartmentsMap = new HashMap();
		for (Iterator it = mapping.getCompartmentMappings().iterator(); it.hasNext();) {
			CompartmentMapping compartmentMapping = (CompartmentMapping) it.next();
			GenCompartment compartmentGen = createGenCompartment(compartmentMapping);
			genNode.getCompartments().add(compartmentGen);
			compartments2GenCompartmentsMap.put(compartmentMapping, compartmentGen);
		}

		for (Iterator it = mapping.getChildMappings().iterator(); it.hasNext();) {
			ChildNodeMapping childNodeMapping = (ChildNodeMapping) it.next();
// Currently childNodeMapping should has compartment but we plan to make this reference optional
			CompartmentMapping compartmentMapping = childNodeMapping.getCompartment();
			GenChildContainer genChildContainer;
			if (compartmentMapping != null && compartments2GenCompartmentsMap.containsKey(compartmentMapping)) {
				genChildContainer = (GenChildContainer) compartments2GenCompartmentsMap.get(compartmentMapping);
			} else {
				genChildContainer = genNode;
			}
			process(childNodeMapping, genChildContainer);
		}
		for (Iterator labels = mapping.getLabelMappings().iterator(); labels.hasNext();) {
			NodeLabelMapping labelMapping = (NodeLabelMapping) labels.next();
			GenNodeLabel label = createNodeLabel(genNode, labelMapping);

			// set class names
			myNamingStrategy.feed(label, mapping, labelMapping);

			genNode.getLabels().add(label);
		}
	}

	private GenCompartment createGenCompartment(CompartmentMapping mapping) {
		Compartment compartment = mapping.getCompartment(); 
		assert compartment != null;
		GenCompartment childCompartment = GMFGenFactory.eINSTANCE.createGenCompartment();
		getGenDiagram().getCompartments().add(childCompartment);
		childCompartment.setVisualID(myVisualIDs.get(childCompartment));
		childCompartment.setDiagramRunTimeClass(getChildContainerRunTimeClass());
		childCompartment.setViewmap(myViewmaps.create(mapping.getCompartment()));
		childCompartment.setCanCollapse(compartment.isCollapsible());
		childCompartment.setNeedsTitle(compartment.isNeedsTitle());
		childCompartment.setTitle(compartment.getName());

		// set class names
		myNamingStrategy.feed(childCompartment, mapping);

		return childCompartment;
	}

	protected void process(LinkMapping lme) {
		assert lme.getDiagramLink() != null;
		assert lme.getLinkMetaFeature() != null;
		GenLink gl = GMFGenFactory.eINSTANCE.createGenLink();
		getGenDiagram().getLinks().add(gl);
		gl.setModelFacet(createModelFacet(lme));
		if (lme.getTool() instanceof CreationTool) {
			LinkEntry le = GMFGenFactory.eINSTANCE.createLinkEntry();
			le.setEntryID(myVisualIDs.get(le));
			findToolGroup(lme.getTool()).getLinkTools().add(le);
			le.getGenLink().add(gl);
			setupCommonToolEntry(le, lme.getTool());
		}
		EAttribute editFeature = lme.getLabelEditFeature();
		if (editFeature != null) {
			FeatureLabelModelFacet modelFacet = GMFGenFactory.eINSTANCE.createFeatureLabelModelFacet();
			modelFacet.setMetaFeature(findGenFeature(lme.getLabelEditFeature()));
			GenLinkLabel label = GMFGenFactory.eINSTANCE.createGenLinkLabel();
			label.setModelFacet(modelFacet);
			label.setVisualID(myVisualIDs.get(label));
			label.setDiagramRunTimeClass(getLinkLabelRunTimeClass());
			label.setViewmap(createLabelViewmap());

			// set class names
			myNamingStrategy.feed(label, lme, null);

			gl.getLabels().add(label);
		}
		for (Iterator labels = lme.getLabelMappings().iterator(); labels.hasNext();) {
			LinkLabelMapping labelMapping = (LinkLabelMapping) labels.next();
			GenLinkLabel label = createLinkLabel(gl, labelMapping);

			// set class names
			myNamingStrategy.feed(label, lme, labelMapping);

			gl.getLabels().add(label);
		}
		gl.setDiagramRunTimeClass(findRunTimeClass(lme));
		gl.setVisualID(myVisualIDs.get(gl));

		// set class names
		myNamingStrategy.feed(gl, lme);

		gl.setViewmap(myViewmaps.create(lme.getDiagramLink()));

		if(lme.getCreationConstraints() != null) {
			gl.setCreationConstraints(createLinkCreationConstraints(lme.getCreationConstraints()));
		}
	}

	private GenNodeLabel createNodeLabel(GenNode node, NodeLabelMapping mapping) {
		GenNodeLabel label;
		if (mapping.isExternal()) {
			label = GMFGenFactory.eINSTANCE.createGenExternalNodeLabel();
		} else {
			label = GMFGenFactory.eINSTANCE.createGenNodeLabel();
		}
		label.setVisualID(myVisualIDs.get(label));
		label.setDiagramRunTimeClass(findRunTimeClass(mapping));
		label.setViewmap(createLabelViewmap());
		label.setModelFacet(createLabelModelFacet(mapping));
		return label;
	}

	private GenLinkLabel createLinkLabel(GenLink link, LinkLabelMapping mapping) {
		GenLinkLabel label = GMFGenFactory.eINSTANCE.createGenLinkLabel();
		label.setVisualID(myVisualIDs.get(label));
		label.setDiagramRunTimeClass(findRunTimeClass(mapping));
		label.setViewmap(createLabelViewmap());
		label.setModelFacet(createLabelModelFacet(mapping));
		label.setAlignment(getLinkLabelAlignment(mapping.getAlignment()));
		return label;
	}

	private LinkLabelAlignment getLinkLabelAlignment(org.eclipse.gmf.mappings.LinkLabelAlignment alignment) {
		switch (alignment.getValue()) {
		case org.eclipse.gmf.mappings.LinkLabelAlignment.SOURCE: return LinkLabelAlignment.SOURCE_LITERAL;
		case org.eclipse.gmf.mappings.LinkLabelAlignment.MIDDLE: return LinkLabelAlignment.MIDDLE_LITERAL;
		case org.eclipse.gmf.mappings.LinkLabelAlignment.TARGET: return LinkLabelAlignment.TARGET_LITERAL;
		default: throw new IllegalStateException();
		}
	}

	private LabelModelFacet createLabelModelFacet(LabelMapping mapping) {
		String text = mapping.getText();
		if (text != null && text.length() > 0) {
			TextLabelModelFacet modelFacet = GMFGenFactory.eINSTANCE.createTextLabelModelFacet();
			modelFacet.setText(text);
			return modelFacet;
		}
		if (mapping.getFeatures().size() == 1) {
			FeatureLabelModelFacet modelFacet = GMFGenFactory.eINSTANCE.createFeatureLabelModelFacet();
			modelFacet.setMetaFeature(findGenFeature((EAttribute) mapping.getFeatures().get(0)));
			String defaultText = mapping.getDefaultText();
			if (defaultText == null || defaultText.length() == 0) {
				defaultText = "<...>";
			}
			modelFacet.setDefaultText(defaultText);
			modelFacet.setViewPattern(mapping.getViewPattern());
			modelFacet.setEditPattern(mapping.getEditPattern());
			return modelFacet;
		}
		if (mapping.getFeatures().size() > 1) {
			CompositeFeatureLabelModelFacet modelFacet = GMFGenFactory.eINSTANCE.createCompositeFeatureLabelModelFacet();
			for (Iterator features = mapping.getFeatures().iterator(); features.hasNext();) {
				modelFacet.getMetaFeatures().add(findGenFeature((EAttribute) features.next()));
			}
			modelFacet.setViewPattern(mapping.getViewPattern());
			modelFacet.setEditPattern(mapping.getEditPattern());
			return modelFacet;
		}
		throw new IllegalArgumentException("Model facet of a label is undefined " + mapping);
	}

	/**
	 * FIXME Use child from gmfgraph with dedicated figure
	 * @return
	 */
	private Viewmap createLabelViewmap() {
		return DefaultViewmapProducer.createLabelViewmap();
	}

	private GenClass findRunTimeClass(NodeMapping nme) {
		return myDRTHelper.get(nme);
	}

	private GenClass findRunTimeClass(ChildNodeMapping childNodeMapping) {
		return myDRTHelper.get(childNodeMapping);
	}

	private GenClass findRunTimeClass(LinkMapping lme) {
		return myDRTHelper.get(lme);
	}

	private GenClass findRunTimeClass(CanvasMapping mapping) {
		return myDRTHelper.get(mapping);
	}

	private GenClass getChildContainerRunTimeClass() {
		return myDRTHelper.getChildContainerDefault();
	}

	private GenClass getNodeLabelRunTimeClass() {
		return myDRTHelper.getNodeLabelDefault();
	}

	private GenClass getLinkLabelRunTimeClass() {
		return myDRTHelper.getLinkLabelDefault();
	}

	private GenClass findRunTimeClass(NodeLabelMapping mapping) {
		return myDRTHelper.get(mapping);
	}

	private GenClass findRunTimeClass(LinkLabelMapping mapping) {
		return myDRTHelper.get(mapping);
	}

	private void handleNodeTool(ToolOwner nme, GenNode genNode) {
		if (nme.getTool() != null && nme.getTool() instanceof CreationTool) {
			// XXX handle other tool types (action, whatever)
			NodeEntry ne = GMFGenFactory.eINSTANCE.createNodeEntry();
			ne.setEntryID(myVisualIDs.get(ne));
			findToolGroup(nme.getTool()).getNodeTools().add(ne);
			ne.getGenNode().add(genNode);
			setupCommonToolEntry(ne, nme.getTool());
		}
	}

	private void setupCommonToolEntry(EntryBase te, AbstractTool tool) {
		te.setTitleKey(tool.getTitle() == null ? "" : tool.getTitle()); // same at (*1*)
		te.setDescriptionKey(tool.getDescription());
		// FIXME need to change this once better tooling definition is in place. 
		if (tool.getLargeIcon() instanceof BundleImage) {
			// XXX assume same bundle
			te.setLargeIconPath(((BundleImage) tool.getLargeIcon()).getPath());
		}
		if (tool.getSmallIcon() instanceof BundleImage) {
			// XXX assume same bundle
			te.setSmallIconPath(((BundleImage) tool.getSmallIcon()).getPath());
		}
	}

	/**
	 * TODO initialize palette with set of groups known from Mapping. Perhaps, don't even 
	 * create missed group in that case.
	 * FIXME and don't rely on title as unique key
	 */
	private ToolGroup findToolGroup(AbstractTool tool) {
		assert tool.eContainer() != null;
		ToolContainer tc = (ToolContainer) tool.eContainer();
		String groupName = tc.getTitle() == null ? "" : tc.getTitle(); // same at (*1*)
		for (Iterator it = getGenPalette().getGroups().iterator(); it.hasNext();) {
			ToolGroup next = (ToolGroup) it.next();
			if (groupName.equals(next.getTitleKey())) {
				return next;
			}
		}
		ToolGroup tg = GMFGenFactory.eINSTANCE.createToolGroup();
		tg.setEntryID(myVisualIDs.get(tg));
		getGenPalette().getGroups().add(tg);
		setupCommonToolEntry(tg, tc);
		return tg;
	}
	
	private void assertAbstractNodeMapping(AbstractNodeMapping mapping) {
		assert mapping.getDomainContext() != null;
		assert checkDirectEditAttrValidity(mapping);
	}

	private boolean checkDirectEditAttrValidity(AbstractNodeMapping nme) {
		if (nme.getEditFeature() == null) {
			return true;
		}
		EClassifier attrContainer = nme.getEditFeature().getEContainingClass();
		return attrContainer == nme.getDomainContext() || nme.getDomainContext().getEAllSuperTypes().contains(attrContainer);
	}

	private GenPackage findGenPackage(EPackage ePackage) {
		return myGenModelMatch.findGenPackage(ePackage);
	}

	private GenClass findGenClass(EClass eClass) {
		return myGenModelMatch.findGenClass(eClass);
	}

	private GenFeature findGenFeature(EStructuralFeature feature) {
		return myGenModelMatch.findGenFeature(feature);
	}
	
	private TypeModelFacet createModelFacet(AbstractNodeMapping anm) {
		TypeModelFacet typeModelFacet = setupModelFacet(anm.getDomainContext(), anm.getContainmentFeature(), null);
		return setupAux(typeModelFacet, anm.getDomainSpecialization(), anm.getDomainInitializer());
	}

	private LinkModelFacet createModelFacet(LinkMapping lme) {
		if (lme.getDomainMetaElement() != null) {
			TypeLinkModelFacet mf = GMFGenFactory.eINSTANCE.createTypeLinkModelFacet();
			mf.setMetaClass(findGenClass(lme.getDomainMetaElement()));
			mf.setContainmentMetaFeature(findGenFeature(lme.getContainmentFeature()));
			mf.setChildMetaFeature(mf.getContainmentMetaFeature());
			mf.setSourceMetaFeature(findGenFeature(lme.getSourceMetaFeature()));
			mf.setTargetMetaFeature(findGenFeature(lme.getLinkMetaFeature()));
			setupAux(mf, lme.getDomainSpecialization(), lme.getDomainInitializer());
			return mf;
		} else {
			FeatureLinkModelFacet mf = GMFGenFactory.eINSTANCE.createFeatureLinkModelFacet();
			mf.setMetaFeature(findGenFeature(lme.getLinkMetaFeature()));
			return mf;
		}
	}

	private GenLinkConstraints createLinkCreationConstraints(LinkConstraints constraints) {
		LinkMapping lme = constraints.getLinkMapping();
		if(lme == null) {
			return null;
		}				
		GenLinkConstraints genConstraints = GMFGenFactory.eINSTANCE.createGenLinkConstraints();
		Constraint sourceConstraint = constraints.getSourceEnd();
		if(sourceConstraint != null) {
			genConstraints.setSourceEnd(createExpression(sourceConstraint.getBody(), sourceConstraint.getLanguage()));
		}
		Constraint targetConstraint = constraints.getTargetEnd();
		if(targetConstraint != null) {
			genConstraints.setTargetEnd(createExpression(targetConstraint.getBody(), targetConstraint.getLanguage()));
		}		
		return genConstraints; 
	}

	private TypeModelFacet setupModelFacet(EClass domainMetaElement, EStructuralFeature containmentFeature, EStructuralFeature childFeature) {
		TypeModelFacet mf = GMFGenFactory.eINSTANCE.createTypeModelFacet();
		mf.setMetaClass(findGenClass(domainMetaElement));
		mf.setContainmentMetaFeature(findGenFeature(containmentFeature));
		mf.setChildMetaFeature(childFeature == null ? mf.getContainmentMetaFeature() : findGenFeature(childFeature));
		return mf;
	}

	/**
	 * @return typeModelFacet argument for convenience
	 */
	private TypeModelFacet setupAux(TypeModelFacet typeModelFacet, Constraint spec, ElementInitializer init) {
		// construct model element selector for domain EClass specializations if any exist
		if(spec != null) {
			typeModelFacet.setModelElementSelector(createModelElementSelector(spec));
		}
		if(init != null) {
			typeModelFacet.setModelElementInitializer(createElementInitializer(init));			
		}
		return typeModelFacet;
	}

	private GenElementInitializer createElementInitializer(ElementInitializer elementInitializer) {
		if(elementInitializer instanceof FeatureSeqInitializer) {
			FeatureSeqInitializer fsInitializer = (FeatureSeqInitializer) elementInitializer;
			GenFeatureSeqInitializer fSeqInitializer = GMFGenFactory.eINSTANCE.createGenFeatureSeqInitializer();
			
			for (Iterator it = fsInitializer.getInitializers().iterator(); it.hasNext();) {
				FeatureValueSpec nextValSpec = (FeatureValueSpec) it.next();
				
				GenFeatureValueSpec nextGenValSpec = GMFGenFactory.eINSTANCE.createGenFeatureValueSpec();				
				nextGenValSpec.setBody(nextValSpec.getBody());
				nextGenValSpec.setLanguage(nextValSpec.getLanguage());
				nextGenValSpec.setFeature(findGenFeature(nextValSpec.getFeature()));
				
				fSeqInitializer.getInitializers().add(nextGenValSpec);
			}
			return fSeqInitializer;
		}
		return null;
	}
	
	private ValueExpression createExpression(String body, String lang) {
		if(body == null) {
			return null;
		}
		ValueExpression valueExpression = GMFGenFactory.eINSTANCE.createValueExpression();
		valueExpression.setBody(body);
		valueExpression.setLanguage(lang);
		return valueExpression;
	}
	
	private static ModelElementSelector createModelElementSelector(Constraint metaElementConstraint) {
		ModelElementSelector modelElementSelector = GMFGenFactory.eINSTANCE.createModelElementSelector();
		modelElementSelector.setBody(metaElementConstraint.getBody());
		modelElementSelector.setLanguage(metaElementConstraint.getLanguage());		
		return modelElementSelector;
	}
	
	private GenAuditContainer createGenAuditContainer(AuditContainer ac) {
		GenAuditContainer gac = GMFGenFactory.eINSTANCE.createGenAuditContainer();
		gac.setId(ac.getId());
		gac.setName(ac.getName());
		gac.setDescription(ac.getDescription());
		for(Iterator it = ac.getChildContainers().iterator(); it.hasNext();) {
			AuditContainer nextChild = (AuditContainer) it.next();
			gac.getChildContainers().add(createGenAuditContainer(nextChild));
		}
		for (Iterator it = ac.getAudits().iterator(); it.hasNext();) {
			gac.getAudits().add(createGenAudit((AuditRule) it.next()));
		}
		return gac;
	}
	
	private GenAuditRule createGenAudit(AuditRule audit) {
		GenAuditRule genAudit = GMFGenFactory.eINSTANCE.createGenAuditRule();
		genAudit.setId(audit.getId());
		genAudit.setName(audit.getName());
		genAudit.setMessage(audit.getMessage());
		genAudit.setDescription(audit.getDescription());
		genAudit.setUseInLiveMode(audit.isUseInLiveMode());
		
		if(audit.getTarget() != null) {
			genAudit.setTarget(findGenClass(audit.getTarget()));
		}
		Constraint rule = audit.getRule();
		if(rule != null) {
			genAudit.setRule(createExpression(rule.getBody(), rule.getLanguage()));
		}

		Severity severity = audit.getSeverity();
		GenSeverity genSeverity = null;
		if(severity == Severity.INFO_LITERAL) {
			genSeverity = GenSeverity.INFO_LITERAL;
		} else if(severity == Severity.WARNING_LITERAL) {
			genSeverity = GenSeverity.WARNING_LITERAL;
		} else if(severity == Severity.ERROR_LITERAL) {
			genSeverity = GenSeverity.ERROR_LITERAL;
		}
		if(genSeverity != null) {
			genAudit.setSeverity(genSeverity);
		}
		return genAudit;
	} 
}
