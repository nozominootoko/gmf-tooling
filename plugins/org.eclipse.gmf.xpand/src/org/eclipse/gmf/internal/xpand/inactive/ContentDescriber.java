/*
 * Copyright (c) 2009 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Artem Tikhomirov (Borland) - initial API and implementation
 */
package org.eclipse.gmf.internal.xpand.inactive;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.ITextContentDescriber;

/**
 * Major difference from platform's default content describer (which uses BOM) is that we
 * supply ISO-8859-1 encoding for legacy template files (those with 0xAB and 0xBB), but keep
 * exposing UTF-8 as our default encoding. Besides, we do recognize UTF-8 encoding even if
 * there's no BOM in the file (looking for 0xC2 0xAB and 0xC2 0xBB sequences)
 *   
 * @author artem
 */
public class ContentDescriber implements ITextContentDescriber {

	public int describe(Reader contents, IContentDescription description) throws IOException {
		// no idea what I can tell here, but if I do not implement ITextContentDescriber, attempt to save
		// existing! UTF-8 xpt file under template folder with ISO encoding results in error "can't convert UTF to ISO"
		// - for some stupid reason Eclipse TextEditor tries to come up with new encoding for existing file
		return VALID;
	}

	public int describe(InputStream contents, IContentDescription description) throws IOException {
		StreamDecoder sd = new StreamDecoder(contents, null);
		if (sd.getEncoding() == null) {
			return INDETERMINATE;
		}
		if (description != null) {
			description.setProperty(IContentDescription.CHARSET, sd.getEncoding().name());
		} // otherwise, rely on Eclipse to detect encoding
		return VALID;
	}

	public QualifiedName[] getSupportedOptions() {
		return null; // none
	}
}
