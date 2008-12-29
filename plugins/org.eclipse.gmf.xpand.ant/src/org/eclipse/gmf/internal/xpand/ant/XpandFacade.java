/*
 * Copyright (c) 2007, 2008 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.gmf.internal.xpand.ant;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.gmf.internal.xpand.BufferOutput;
import org.eclipse.gmf.internal.xpand.BuiltinMetaModel;
import org.eclipse.gmf.internal.xpand.ResourceManager;
import org.eclipse.gmf.internal.xpand.expression.ExecutionContext;
import org.eclipse.gmf.internal.xpand.expression.ExecutionContextImpl;
import org.eclipse.gmf.internal.xpand.expression.ExpressionFacade;
import org.eclipse.gmf.internal.xpand.expression.Variable;
import org.eclipse.gmf.internal.xpand.expression.ast.Identifier;
import org.eclipse.gmf.internal.xpand.expression.ast.SyntaxElement;
import org.eclipse.gmf.internal.xpand.model.XpandDefinition;
import org.eclipse.gmf.internal.xpand.model.XpandExecutionContext;
import org.eclipse.gmf.internal.xpand.util.BundleResourceManager;
import org.eclipse.gmf.internal.xpand.util.ClassLoadContext;
import org.eclipse.gmf.internal.xpand.util.ContextFactory;
import org.eclipse.gmf.internal.xpand.xtend.ast.ExtensionFile;
import org.eclipse.gmf.internal.xpand.xtend.ast.ImportStatement;
import org.eclipse.gmf.internal.xpand.xtend.parser.ExtensionFactory;
import org.osgi.framework.Bundle;

/**
 * Redistributable API for Xpand evaluation
 * @author artem
 */
public final class XpandFacade {
	private final LinkedList<Variable> myGlobals = new LinkedList<Variable>();
	private final LinkedList<URL> myLocations = new LinkedList<URL>();
	private final LinkedList<String> myImportedModels = new LinkedList<String>();
	private final LinkedList<String> myExtensionFiles = new LinkedList<String>();	
	private final LinkedList<ClassLoader> myContextLoaders = new LinkedList<ClassLoader>();
	
	private XpandExecutionContext myXpandCtx;
	private ExecutionContext myXtendCtx;
	private ClassLoadContext myClassLoader;

	private final StringBuilder myOut = new StringBuilder();

	public XpandFacade() {
	}

	/**
	 * Sort of copy constructor, create a new facade pre-initialized with values 
	 * of existing one.
	 * @param chain facade to copy settings (globals, locations, metamodels, extensions, loaders) from, can't be <code>null</code>.
	 */
	public XpandFacade(XpandFacade chain) {
		assert chain != null;
		myGlobals.addAll(chain.myGlobals);
		myLocations.addAll(chain.myLocations);
		myImportedModels.addAll(chain.myImportedModels);
		myExtensionFiles.addAll(chain.myExtensionFiles);
		myContextLoaders.addAll(chain.myContextLoaders);
		//
		// not necessary, but doesn't seem to hurt
		myClassLoader = chain.myClassLoader; // read-only, stateless
		myXpandCtx = chain.myXpandCtx; // new state is formed with cloning
		myXtendCtx = chain.myXtendCtx;
	}

	public void addGlobal(String name, Object value) {
		assert name != null;
		for (Iterator<Variable> it = myGlobals.listIterator(); it.hasNext();) {
			if (it.next().getName().equals(name)) {
				it.remove();
			}
		}
		if (name == null || value == null) {
			return;
		}
		myGlobals.addFirst(new Variable(name, value));
		clearAllContexts();
	}

	public void addLocation(String url) throws MalformedURLException {
		addLocation(new URL(url));
	}

	public void addLocation(URL url) {
		assert url != null;
		myLocations.addLast(url);
		clearAllContexts();
	}

	/**
	 * Registers a class loader to load Java classes accessed from templates and/or expressions. 
	 * @param loader ClassLoader to load classes though
	 */
	public void addClassLoadContext(ClassLoader loader) {
		assert loader != null;
		if (loader != null) { // safety
			myContextLoaders.add(loader);
			clearAllContexts();
		}
	}

	/**
	 * Register a bundle to load Java classes from (i.e. JAVA functions in Xtend)
	 * @param bundle - generally obtained from {@link org.eclipse.core.runtime.Platform#getBundle(String)}, should not be null.
	 */
	public void addClassLoadContext(Bundle bundle) {
		assert bundle != null;
		if (bundle != null) { // safety
			myContextLoaders.add(new BundleClassLoader(bundle));
			clearAllContexts();
		}
	}
	
	public void addMetamodel(String metamodel) {
		if (myImportedModels.contains(metamodel)) {
			return;
		}
		myImportedModels.add(metamodel);
		clearExprContext();
	}

	/**
	 * @param extensionFile double-colon separated qualified name of ext file
	 */
	public void addExtensionFile(String extensionFile) {
		if (myExtensionFiles.contains(extensionFile)) {
			return;
		}
		myExtensionFiles.add(extensionFile);
		clearExprContext();
	}

	public <T> T evaluate(String expression, Object target) {
		// XXX perhaps, need to check for target == null and do not set 'this' then
		return evaluate(expression, Collections.singletonMap("this", target));
	}
	
	/**
	 * @param expression xtend expression to evaluate
	 * @param context should not be <code>null</code>
	 * @return
	 */
	public <T> T evaluate(String expression, Map<String,?> context) {
		assert context != null; // nevertheless, prevent NPE.
		ExpressionFacade facade = new ExpressionFacade(getExpressionContext());
		@SuppressWarnings("unchecked")
		T rv = (T) facade.evaluate(expression, context == null ? Collections.<String,Object>emptyMap() : context);
		return rv;
	}

	public String xpand(String templateName, Object target, Object... arguments) {
		if (target == null) {
			return null;
		}
		clearOut();
		XpandExecutionContext ctx = getXpandContext();
		new org.eclipse.gmf.internal.xpand.XpandFacade(ctx).evaluate(templateName, target, arguments);
		return myOut.toString();
	}
	
	public Map<Object, String> xpand(String templateName, Collection<?> target, Object... arguments) {
		// though it's reasonable to keep original order of input elements,
		// is it worth declaring in API?
		LinkedHashMap<Object, String> inputToResult = new LinkedHashMap<Object, String>();
        boolean invokeForCollection = findDefinition(templateName, target, arguments) != null;
        if (invokeForCollection) {
			inputToResult.put(target, xpand(templateName, (Object)target, arguments));
	        return inputToResult;
        }
        for (Object nextInput: target) {
        	if (nextInput == null) {
        		continue;
        	}
			inputToResult.put(nextInput, xpand(templateName, nextInput, arguments));
        }
        return inputToResult;
	}

	private XpandDefinition findDefinition(String templateName, Object target, Object[] arguments) {
		EClassifier targetType = BuiltinMetaModel.getType(target);
		final EClassifier[] paramTypes = new EClassifier[arguments == null ? 0 : arguments.length];
		for (int i = 0; i < paramTypes.length; i++) {
		    paramTypes[i] = BuiltinMetaModel.getType(arguments[i]);
		}
		return getXpandContext().findDefinition(templateName, targetType, paramTypes);
	}

	private void clearAllContexts() {
		myXpandCtx = null;
		myXtendCtx = null;
		myClassLoader = null;
	}
	
	private void clearExprContext() {
		myXtendCtx = null;
	}

	private void clearOut() {
		myOut.setLength(200);
		myOut.trimToSize();
		myOut.setLength(0);
	}

	private XpandExecutionContext getXpandContext() {
		if (myXpandCtx == null) {
			BundleResourceManager rm = new BundleResourceManager(myLocations.toArray(new URL[myLocations.size()]));
			myXpandCtx = ContextFactory.createXpandContext(rm, new BufferOutput(myOut), new LinkedList<Variable>(myGlobals), getClassLoadContext());
		}
		return myXpandCtx;
	}

	private ExecutionContext getExpressionContext() {
		if (myXtendCtx == null) {
			ResourceManager rm;
			if (myLocations.isEmpty()) {
				try {
					// use current default path as root
					// use canonicalFile to get rid of dot after it get resolved to current dir
					rm = new BundleResourceManager(new File(".").getCanonicalFile().toURL());
				} catch (IOException ex) {
					// should not happen
					rm = null;
				}
			} else {
				rm = new BundleResourceManager(myLocations.toArray(new URL[myLocations.size()]));
			}
			ExtensionFile fakeHeaderHolder = null;
			if (!myImportedModels.isEmpty() || !myExtensionFiles.isEmpty()) {
				fakeHeaderHolder = buildExtensionFile();
			} 
			ExecutionContextImpl ecImpl = new ExecutionContextImpl(rm, fakeHeaderHolder, null, new LinkedList<Variable>(myGlobals));
			ecImpl.setContextClassLoader(getClassLoadContext());
			myXtendCtx = ecImpl;
		}
		return myXtendCtx;
	}

	private ClassLoadContext getClassLoadContext() {
		if (myClassLoader == null) {
			if (myContextLoaders.isEmpty()) {
				myClassLoader = new ClassLoadContext.Naive(getClass().getClassLoader());
			} else {
				myClassLoader = new ClassLoadContext.Naive(myContextLoaders.toArray(new ClassLoader[myContextLoaders.size()]));
			}
		}
		return myClassLoader;
	}

	private ExtensionFile buildExtensionFile() {
		List<SyntaxElement> nsImports = new ArrayList<SyntaxElement>();
		for (String next : myImportedModels) {
			ImportStatement extension = new ImportStatement(0, -1, 1, next);
			nsImports.add(extension);
		}
		List<SyntaxElement> extImports = new ArrayList<SyntaxElement>();
		for (String next: myExtensionFiles) {
			ImportStatement extension = new ImportStatement(0, -1, 1, new Identifier(0, -1, 1, next), true);
			extImports.add(extension);
		}
		return new ExtensionFactory("nofile").createExtensionFile(nsImports, extImports, Collections.<SyntaxElement>emptyList());
	}
	
	private static class BundleClassLoader extends ClassLoader {
		private final Bundle myBundle;

		BundleClassLoader(Bundle b) {
			assert b != null;
			myBundle = b;
		}
		
		@Override
		public Class<?> loadClass(String name) throws ClassNotFoundException {
			return myBundle.loadClass(name);
		}
		
		@Override
		protected java.net.URL findResource(String name) {
			return myBundle.getResource(name);
		}
	}
}