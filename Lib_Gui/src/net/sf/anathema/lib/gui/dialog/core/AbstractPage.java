/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.core;

import net.disy.commons.swing.component.DisposableContainer;

import java.awt.Component;

public abstract class AbstractPage extends DisposableContainer implements IPage {

  /** @deprecated As of 10.11.2009 (gebhard), replaced by {@link #getHelpHandler()} */
  @Deprecated
  protected void performHelp() {
    throw new UnsupportedOperationException();
  }

  /** @deprecated As of 10.11.2009 (gebhard), replaced by {@link #getHelpHandler()} */
  @Deprecated
  protected boolean isHelpAvailable() {
    return false;
  }

  @Override
  public IDialogHelpHandler getHelpHandler() {
    return isHelpAvailable() ? new IDialogHelpHandler() {
      @Override
      public void execute(final Component parentComponent) {
        performHelp();
      }
    } : null;
  }

}