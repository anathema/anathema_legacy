/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.foldout;

import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.gui.dialog.DialogMessages;
import net.sf.anathema.lib.gui.dialog.userdialog.DefaultDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.IDialogButtonConfiguration;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;

public abstract class AbstractFoldOutDialogConfiguration<P extends IDialogPage>
    extends
    DefaultDialogConfiguration<P> implements IFoldOutDialogConfiguration<P> {

  private final IFoldOutPage foldOutPage;

  public AbstractFoldOutDialogConfiguration(
      final P dialogPage,
      final IFoldOutPage foldOutPage,
      final IDialogButtonConfiguration buttonConfiguration) {
    super(dialogPage, buttonConfiguration);
    this.foldOutPage = foldOutPage;
  }

  @Override
  public IActionConfiguration getFoldOutButtonConfiguration() {
    final String label = DialogMessages
        .getString("FoldOutDialog.Button.showDetails.text"); //$NON-NLS-1$
    return new ActionConfiguration(label);
  }

  @Override
  public IActionConfiguration getFoldInButtonConfiguration() {
    final String label = DialogMessages
        .getString("FoldOutDialog.Button.hideDetails.text"); //$NON-NLS-1$
    return new ActionConfiguration(label);
  }

  @Override
  public IFoldOutPage getFoldOutPage() {
    return foldOutPage;
  }

  @Override
  public boolean isInitiallyFoldedOut() {
    return false;
  }
}