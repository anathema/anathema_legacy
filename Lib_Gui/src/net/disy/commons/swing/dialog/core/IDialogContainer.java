/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.core;

/**
 * @author gebhard
 */
public interface IDialogContainer extends IDialogControl {

  /**
   * Executes this Dialog by showing a modal instance.
   */
  public IDialogResult show();

  /** Requests this dialog to finish ('ok' in dialogs, 'finish' in wizards.). If the dialog cannot
   * finish (because this action is disabled) this method will do nothing and return silently. */
  public void requestFinish();
}