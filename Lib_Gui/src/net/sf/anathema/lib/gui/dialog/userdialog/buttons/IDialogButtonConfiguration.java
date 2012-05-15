/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.userdialog.buttons;

import net.sf.anathema.lib.gui.action.IActionConfiguration;

public interface IDialogButtonConfiguration {

  /** @return Action configuration object for the ok button or <code>null</code> if there shall not be an 
   * ok button available. */
  public IActionConfiguration getOkActionConfiguration();

  /** @return Action configuration object for the cancel button or <code>null</code> if there shall not be an 
   * cancel button available. */
  public IActionConfiguration getCancelActionConfiguration();
}