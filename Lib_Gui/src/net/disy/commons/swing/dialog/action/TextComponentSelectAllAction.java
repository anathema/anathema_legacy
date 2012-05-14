/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.action;

import net.disy.commons.core.util.Ensure;
import net.disy.commons.swing.dialog.DisyCommonsSwingDialogMessages;
import net.sf.anathema.lib.gui.action.SmartAction;

import javax.swing.text.JTextComponent;
import java.awt.Component;

public class TextComponentSelectAllAction extends SmartAction {

  private final JTextComponent textComponent;

  public TextComponentSelectAllAction(final JTextComponent textComponent) {
    super(DisyCommonsSwingDialogMessages.SELECT_ALL);
    Ensure.ensureArgumentNotNull(textComponent);
    this.textComponent = textComponent;
  }

  @Override
  protected void execute(final Component parentComponent) {
    textComponent.selectAll();
  }
}