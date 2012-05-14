/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core.message;

import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.dialog.core.IDialogConstants;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.message.AbstractMessageTypeUi;
import net.disy.commons.swing.message.MessageTypeUi;
import net.disy.commons.swing.widgets.AutoWrappingLabel;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class DialogMessageComponent extends JPanel {

  private final AutoWrappingLabel messageLabel;
  private final JLabel iconLabel;

  public DialogMessageComponent(final boolean withIcon) {
    super(new BorderLayout());

    setBackground(IDialogConstants.HEADER_BACKGROUND_COLOR);

    iconLabel = new JLabel();
    messageLabel = new AutoWrappingLabel("!Dialog.message!", 330); //$NON-NLS-1$
    messageLabel.setBackground(IDialogConstants.HEADER_BACKGROUND_COLOR);
    messageLabel.setForeground(IDialogConstants.HEADER_TEXT_COLOR);
    messageLabel.setFont(IDialogConstants.MESSAGE_LABEL_FONT);

    final JPanel iconPanel = new JPanel(new GridDialogLayout(1, false, 3, 0));
    iconPanel.add(iconLabel);
    iconPanel.setBackground(IDialogConstants.HEADER_BACKGROUND_COLOR);
    if (withIcon) {
      add(iconPanel, BorderLayout.WEST);
    }
    else {
      add(Box.createHorizontalStrut(10), BorderLayout.WEST);
    }
    add(messageLabel.getContent(), BorderLayout.CENTER);
  }

  public void setMessage(final IBasicMessage message) {
    if (message == null) {
      return;
    }
    iconLabel.setIcon(MessageTypeUi.getInstance().getIcon(message.getType()));
    messageLabel.setForeground(AbstractMessageTypeUi.getColor(message.getType()));
    messageLabel.setText(message.getText());
  }
}