package net.sf.anathema.lib.gui.dialog;

import java.util.Locale;
import java.util.ResourceBundle;

public class DialogMessages {

  private static final String BUNDLE_NAME = "net.sf.anathema.lib.gui.dialog.messages";

  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(
      BUNDLE_NAME,
      Locale.getDefault());

  public static final String CANCEL = getString("SmartAction.cancel");
  public static final String OK = getString("SmartAction.okay");

  public static final String SELECT_ALL = getString("SmartAction.selectAll");
  public static final String COPY = getString("SmartAction.copy");

  public static final String WIZARD_NEXT = getString("Wizard.SmartNext");
  public static final String WIZARD_BACK = getString("Wizard.SmartBack");
  public static final String WIZARD_FINISH = getString("Wizard.SmartFinish");

  public static String getString(String key) {
    return RESOURCE_BUNDLE.getString(key);
  }
}