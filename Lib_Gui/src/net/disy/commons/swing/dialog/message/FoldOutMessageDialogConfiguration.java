/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.message;

import net.disy.commons.core.message.IMessage;
import net.disy.commons.swing.dialog.core.DialogHeaderPanelConfiguration;
import net.disy.commons.swing.dialog.core.IDialogHeaderPanelConfiguration;
import net.disy.commons.swing.dialog.foldout.AbstractFoldOutDialogConfiguration;
import net.disy.commons.swing.dialog.foldout.IFoldOutPage;
import net.disy.commons.swing.dialog.userdialog.buttons.DialogButtonConfigurationFactory;

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