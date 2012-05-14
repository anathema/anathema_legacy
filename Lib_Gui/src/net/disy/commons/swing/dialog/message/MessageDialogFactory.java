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
import net.disy.commons.swing.dialog.foldout.FoldOutDialog;
import net.disy.commons.swing.dialog.foldout.IFoldOutPage;
import net.disy.commons.swing.dialog.message.internal.MessageDetailsFoldOutPage;
import net.disy.commons.swing.dialog.userdialog.UserDialog;

import java.awt.Component;

public class MessageDialogFactory {

  public static UserDialog createMessageDialog(final Component parentComponent, final IMessage message) {
    UserDialog userDialog;
    if (message.getDetail() == null) {
      userDialog = new UserDialog(parentComponent, new MessageUserDialogConfiguration(message));
      userDialog.getDialog().setResizable(false);
    } else {
      final IFoldOutPage foldOutPage = new MessageDetailsFoldOutPage(message.getDetail());
      final FoldOutMessageDialogConfiguration dialogConfiguration = new FoldOutMessageDialogConfiguration(message, foldOutPage);
      userDialog = new FoldOutDialog(parentComponent, dialogConfiguration);
    }
    return userDialog;
  }

  public static void showMessageDialog(final Component parent, final IMessage message) {
    createMessageDialog(parent, message).show();
  }
}