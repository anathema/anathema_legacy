/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.wizard;

import net.disy.commons.swing.dialog.core.IDialogContainer;

/**
 * Interface for containers that can host a wizard. It displays wizard pages, at most one of which
 * is considered the current page. <code>getCurrentPage</code> returns the current page; 
 * <code>showPage</code> programmatically changes the the current page. 
 *
 *The class {@link WizardDialog} provides a fully functional implementation of this interface which
 *will meet the needs of most clients. However, clients are also free to implement this interface if
 *{@link WizardDialog} does not suit their needs.
 *
 */
public interface IWizardContainer extends IDialogContainer {

  public IWizardPage getCurrentPage();
}