/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.userdialog;

import net.sf.anathema.lib.gui.dialog.core.IDialogContainer;

/**
 * @author gebhard
 */
public interface IUserDialogContainer extends IDialogContainer {

  /**
   * Executes this Dialog by showing a non-modal instance.
   * @see #show()
   * @see #showNonModal(IDialogCloseHandler)
   */
  public void showNonModal();

  /**
   * Executes this Dialog by showing a non-modal instance. The given close handler will be informed on
   * cancel or ok right after the dialog has been disposed.
   * @see #showNonModal()
   * @see #show()
   */
  public void showNonModal(IDialogCloseHandler dialogCloseHandler);

  public void setVisible(boolean visible);

}