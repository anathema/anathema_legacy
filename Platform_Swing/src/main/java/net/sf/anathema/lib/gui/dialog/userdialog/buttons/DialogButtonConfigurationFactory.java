package net.sf.anathema.lib.gui.dialog.userdialog.buttons;

public class DialogButtonConfigurationFactory {

  public static IDialogButtonConfiguration createOkOnly() {
    return new DialogButtonConfiguration(DialogButtonConfiguration.DEFAULT_OK_CONFIG, null);
  }

  public static IDialogButtonConfiguration createOkCancel() {
    return new DialogButtonConfiguration();
  }

}