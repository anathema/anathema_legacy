/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.core;

import net.sf.anathema.lib.gui.dialog.events.IInputValidCheckable;
import net.sf.anathema.lib.gui.dialog.userdialog.IMessageSetable;
import net.sf.anathema.lib.gui.dialog.userdialog.page.IDialogPage;

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