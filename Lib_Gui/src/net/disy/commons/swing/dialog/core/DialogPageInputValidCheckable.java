/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core;

import net.disy.commons.swing.dialog.userdialog.IMessageSetable;
import net.disy.commons.swing.dialog.userdialog.page.IDialogPage;
import net.disy.commons.swing.events.IInputValidCheckable;

public class DialogPageInputValidCheckable implements IInputValidCheckable {
  private final IMessageSetable messageSetable;
  private final IDialogPage page;

  public DialogPageInputValidCheckable(final IMessageSetable messageSetable, final IDialogPage page) {
    this.messageSetable = messageSetable;
    this.page = page;
  }

  @Override
  public void checkInputValid() {
    messageSetable.setMessage(page.createCurrentMessage());
    page.updateInputValid();
  }
}