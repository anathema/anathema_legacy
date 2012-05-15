/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.message;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.dialog.userdialog.page.AbstractDialogPage;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.message.LargeIconMessageTypeUi;
import net.disy.commons.swing.message.MessageTypeUi;
import net.disy.commons.swing.widgets.AutoWrappingLabel;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.message.IMessage;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessageDialogPage extends AbstractDialogPage {

  private final IMessage message;

  public MessageDialogPage(final IMessage message) {
    super(""); //$NON-NLS-1$
    Ensure.ensureArgumentNotNull(message);
    this.message = message;
  }

  @Override
  public JComponent createContent() {
    final Icon icon = new LargeIconMessageTypeUi().getIcon(message.getType());
    final AutoWrappingLabel label = new AutoWrappingLabel(message.getText(), 294);
    final JPanel panel = new JPanel(new GridDialogLayout(2, false, 13, 0));
    label.setBackground(panel.getBackground());
    panel.add(new JLabel(icon), GridDialogLayoutData.FILL_VERTICAL);
    panel.add(label.getContent(), GridDialogLayoutData.FILL_HORIZONTAL);
    return panel;
  }

  @Override
  public IBasicMessage createCurrentMessage() {
    return getDefaultMessage();
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public String getTitle() {
    return message.getTitle() == null
        ? MessageTypeUi.getInstance().getLabel(message.getType())
        : message.getTitle();
  }
}