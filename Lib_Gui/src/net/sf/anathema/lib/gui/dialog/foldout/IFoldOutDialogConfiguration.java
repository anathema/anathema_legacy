/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.foldout;

import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.IDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;

public interface IFoldOutDialogConfiguration<P extends IDialogPage> extends IDialogConfiguration<P> {

  public IFoldOutPage getFoldOutPage();

  public IActionConfiguration getFoldOutButtonConfiguration();

  public IActionConfiguration getFoldInButtonConfiguration();

  public boolean isInitiallyFoldedOut();
}