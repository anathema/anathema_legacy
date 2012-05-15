/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.userdialog.buttons;

import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.dialog.DialogMessages;

public class DialogButtonConfigurationFactory {

  public static IDialogButtonConfiguration createOkOnly() {
    return createOkOnly(DialogButtonConfiguration.DEFAULT_OK_CONFIG);
  }

  public static IDialogButtonConfiguration createOkCancelWithOkText(final String okText) {
    return createOkCancelWithTexts(okText, DialogMessages.CANCEL);
  }

  public static IDialogButtonConfiguration createOkCancelWithTexts(
      final String okText,
      final String cancelText) {
    return new DialogButtonConfiguration(okText, cancelText);
  }

  public static IDialogButtonConfiguration createOkCancel() {
    return new DialogButtonConfiguration();
  }

  private static DialogButtonConfiguration createOkOnly(final ActionConfiguration config) {
    return new DialogButtonConfiguration(config, null);
  }
}