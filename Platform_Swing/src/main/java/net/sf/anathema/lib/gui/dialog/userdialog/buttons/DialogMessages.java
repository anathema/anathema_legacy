package net.sf.anathema.lib.gui.dialog.userdialog.buttons;

import java.util.Locale;
import java.util.ResourceBundle;

public class DialogMessages {

  private static final String BUNDLE_NAME = "net.sf.anathema.lib.gui.dialog.messages";

  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(
      BUNDLE_NAME,
      Locale.getDefault());

  public static final String CANCEL = getString("SmartAction.cancel");
  public static final String OK = getString("SmartAction.okay");

  public static String getString(String key) {
    return RESOURCE_BUNDLE.getString(key);
  }
}