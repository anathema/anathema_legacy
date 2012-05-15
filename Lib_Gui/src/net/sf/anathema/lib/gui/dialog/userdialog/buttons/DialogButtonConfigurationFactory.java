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