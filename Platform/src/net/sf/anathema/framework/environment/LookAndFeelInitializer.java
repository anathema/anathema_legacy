package net.sf.anathema.framework.environment;

import net.sf.anathema.framework.configuration.IInitializationPreferences;
import net.sf.anathema.lib.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LookAndFeelInitializer {
  private static final String AQUA_LOOK_AND_FEEL_CLASSNAME = "apple.laf.AquaLookAndFeel"; //$NON-NLS-1$
  private static final String AQUA_USE_SCREEN_MENU_BAR = "apple.laf.useScreenMenuBar"; //$NON-NLS-1$
  private static final String AQUA_APPLICATION_NAME = "com.apple.mrj.application.apple.menu.about.name"; //$NON-NLS-1$
  private final IInitializationPreferences initializationPreferences;

  public LookAndFeelInitializer(IInitializationPreferences initializationPreferences) {
    this.initializationPreferences = initializationPreferences;
  }

  public void initialize()
          throws InstantiationException,
          IllegalAccessException,
          ClassNotFoundException,
          UnsupportedLookAndFeelException {
    makeCombosLookBetterOnAnyLookAndFeel();
    String lookAndFeelClassName = getLookAndFeelToUse();
    setLookAndFeel(lookAndFeelClassName);
  }

  private void makeCombosLookBetterOnAnyLookAndFeel() {
    System.getProperties().put("swing.addon", "org.jdesktop.swingx.plaf.macosx.MacOSXLookAndFeelAddons"); //$NON-NLS-1$//$NON-NLS-2$
  }

  private String getLookAndFeelToUse() {
    String lookAndFeelClassName = initializationPreferences.getUserLookAndFeel();
    if (lookAndFeelClassName == null) {
      lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
    }
    return lookAndFeelClassName;
  }

  private void setLookAndFeel(String lookAndFeelClassName) {
    boolean successfullySet = trySetLookAndFeel(lookAndFeelClassName);
    if (!successfullySet) {
      fallBackToSystemLookAndFeel();
    }
  }

  private void fallBackToSystemLookAndFeel() {
    trySetLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  }

  private boolean trySetLookAndFeel(String lafClassName) {
    try {
      if (isAqua(lafClassName)) {
        integrateMenuWithMacOS();
      }
      UIManager.setLookAndFeel(lafClassName);
      return true;
    } catch (Exception e) {
      Logger logger = Logger.getLogger(LookAndFeelInitializer.class);
      logger.warn("Failed to use the Look and Feel: " + lafClassName, e); //$NON-NLS-1$
      return false;
    }
  }

  private static boolean isAqua(String lookAndFeelClassName) {
    return lookAndFeelClassName.contains(AQUA_LOOK_AND_FEEL_CLASSNAME);
  }

  private void integrateMenuWithMacOS() {
    System.setProperty(AQUA_USE_SCREEN_MENU_BAR, "true"); //$NON-NLS-1$
    System.setProperty(AQUA_APPLICATION_NAME, "Anathema"); //$NON-NLS-1$ //$NON-NLS-2$
  }
}
