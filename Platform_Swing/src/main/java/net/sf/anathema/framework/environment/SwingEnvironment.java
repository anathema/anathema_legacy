package net.sf.anathema.framework.environment;

import net.sf.anathema.framework.configuration.IInitializationPreferences;

import javax.swing.ToolTipManager;

import static java.awt.Desktop.isDesktopSupported;

public class SwingEnvironment {

  public static void initTooltipManager(IInitializationPreferences initializationPreferences) {
    ToolTipManager.sharedInstance().setInitialDelay(0);
    ToolTipManager.sharedInstance().setReshowDelay(0);
    int toolTipTime = initializationPreferences.getTooltipTimePreference();
    ToolTipManager.sharedInstance().setDismissDelay(toolTipTime * 1000);
  }

  public static void initLookAndFeel(
          IInitializationPreferences initializationPreferences) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    new LookAndFeelInitializer(initializationPreferences).initialize();
  }

  public static boolean isAutoOpenSupported() {
    return isDesktopSupported();
  }
}