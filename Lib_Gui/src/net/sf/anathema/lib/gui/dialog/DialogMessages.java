package net.sf.anathema.lib.gui.dialog;

import java.util.Locale;
import java.util.ResourceBundle;

public class DialogMessages {

  private static final String BUNDLE_NAME = "net.sf.anathema.lib.gui.dialog.messages";//$NON-NLS-1$

  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(
      BUNDLE_NAME,
      Locale.getDefault());

  public static final String CANCEL = getString("SmartAction.cancel"); //$NON-NLS-1$
  public static final String HELP = getString("SmartAction.help"); //$NON-NLS-1$
  public static final String OK = getString("SmartAction.okay"); //$NON-NLS-1$

  public static final String SELECT_ALL = getString("SmartAction.selectAll"); //$NON-NLS-1$
  public static final String COPY = getString("SmartAction.copy"); //$NON-NLS-1$

  public static final String WIZARD_NEXT = getString("Wizard.SmartNext"); //$NON-NLS-1$
  public static final String WIZARD_BACK = getString("Wizard.SmartBack"); //$NON-NLS-1$
  public static final String WIZARD_FINISH = getString("Wizard.SmartFinish"); //$NON-NLS-1$

  public static String getString(String key) {
    return RESOURCE_BUNDLE.getString(key);
  }
}