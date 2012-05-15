/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.message;

import net.sf.anathema.lib.gui.dialog.core.DialogHeaderPanelConfiguration;
import net.sf.anathema.lib.gui.dialog.core.IDialogHeaderPanelConfiguration;
import net.sf.anathema.lib.gui.dialog.foldout.AbstractFoldOutDialogConfiguration;
import net.sf.anathema.lib.gui.dialog.foldout.IFoldOutPage;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.DialogButtonConfigurationFactory;
import net.sf.anathema.lib.message.IMessage;

public class FoldOutMessageDialogConfiguration
    extends
    AbstractFoldOutDialogConfiguration<MessageDialogPage> {

  public FoldOutMessageDialogConfiguration(final IMessage message, final IFoldOutPage foldOutPage) {
    super(new MessageDialogPage(message), foldOutPage, DialogButtonConfigurationFactory
        .createOkOnly());
  }

  @Override
  public IDialogHeaderPanelConfiguration getHeaderPanelConfiguration() {
    return DialogHeaderPanelConfiguration.createInvisible();
  }
}