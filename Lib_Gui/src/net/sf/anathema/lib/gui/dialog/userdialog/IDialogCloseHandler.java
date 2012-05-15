/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.IDialogResult;

public interface IDialogCloseHandler {

  public final static IDialogCloseHandler NULL_HANDLER = new IDialogCloseHandler() {
    @Override
    public void handleDialogClose(final IDialogResult result) {
      //nothing to do
    }
  };

  public void handleDialogClose(IDialogResult result);
}