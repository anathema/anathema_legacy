/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.disy.commons.swing.dialog.userdialog.buttons;

import net.disy.commons.swing.dialog.DisyCommonsSwingDialogMessages;
import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;

public class DialogButtonConfiguration implements IDialogButtonConfiguration {

  static final ActionConfiguration DEFAULT_CANCEL_CONFIG = new ActionConfiguration(
      DisyCommonsSwingDialogMessages.CANCEL);
  static final ActionConfiguration DEFAULT_OK_CONFIG = new ActionConfiguration(
      DisyCommonsSwingDialogMessages.OK);
  private final IActionConfiguration okConfiguration;
  private final IActionConfiguration cancelConfiguration;

  public DialogButtonConfiguration() {
    this(DEFAULT_OK_CONFIG);
  }

  public DialogButtonConfiguration(final IActionConfiguration okConfiguration) {
    this(okConfiguration, DEFAULT_CANCEL_CONFIG);
  }

  public DialogButtonConfiguration(final String okText, final String cancelText) {
    this(new ActionConfiguration(okText), new ActionConfiguration(cancelText));
  }

  public DialogButtonConfiguration(
      final IActionConfiguration okConfiguration,
      final IActionConfiguration cancelConfiguration) {
    this.okConfiguration = okConfiguration;
    this.cancelConfiguration = cancelConfiguration;
  }

  @Override
  public IActionConfiguration getOkActionConfiguration() {
    return okConfiguration;
  }

  @Override
  public IActionConfiguration getCancelActionConfiguration() {
    return cancelConfiguration;
  }
}