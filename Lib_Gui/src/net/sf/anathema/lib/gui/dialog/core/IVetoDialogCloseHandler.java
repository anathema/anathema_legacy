/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.core;

import java.awt.Component;

public interface IVetoDialogCloseHandler {

  /** Called when a dialog is about to be closed.
   * @param result the result from the dialog, containing information about whether the dialog was canceled
   * or finished/ok.
   * @param parentComponent a gui swing that can be used to create dialogs from
   * @return <code>true</code> if the dialog can be closed, <code>false</code> if it shall stay open.*/
  public boolean handleDialogAboutToClose(IDialogResult result, Component parentComponent);
}