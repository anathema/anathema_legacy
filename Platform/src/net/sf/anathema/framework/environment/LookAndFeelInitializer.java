package net.sf.anathema.framework.environment;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import net.sf.anathema.framework.configuration.IAnathemaPreferences;
import net.sf.anathema.lib.logging.Logger;

public class LookAndFeelInitializer {
  private static final String AQUA_LOOK_AND_FEEL_CLASSNAME = "apple.laf.AquaLookAndFeel"; //$NON-NLS-1$
  private static final String AQUA_USE_SCREEN_MENU_BAR = "apple.laf.useScreenMenuBar"; //$NON-NLS-1$
  private static final String AQUA_APPLICATION_NAME = "com.apple.mrj.application.apple.menu.about.name"; //$NON-NLS-1$
  private final IAnathemaPreferences anathemaPreferences;

  public LookAndFeelInitializer(IAnathemaPreferences anathemaPreferences) {
    this.anathemaPreferences = anathemaPreferences;
  }
  
  private static boolean trySetLookAndFeel(String lafClassName) {
    boolean success = false;
    try {
      if (isAqua(lafClassName)) {
        System.setProperty(AQUA_USE_SCREEN_MENU_BAR, "true"); //$NON-NLS-1$
        System.setProperty(AQUA_APPLICATION_NAME, "Anathema"); //$NON-NLS-1$ //$NON-NLS-2$
      }

      UIManager.setLookAndFeel(lafClassName);
      success = true;
    } catch (Exception e) {
      // Simply ignore all the exceptions and return that it was not possible.
      // We don't want to crash for not being able to set the L&F.
      Logger logger = Logger.getLogger(LookAndFeelInitializer.class);
      logger.warn("Failed to use the Look and Feel: " + lafClassName, e); //$NON-NLS-1$
    }
    
    return success;
  }

  public void initialize() {

    // This property was always set by this method, so I (kelemen@github.com) moved it forward.
    // Still I'm not sure that setting this property always is a good thing to do.
    System.getProperties().put("swing.addon", "com.l2fprod.common.swing.plaf.aqua.AquaLookAndFeelAddons"); //$NON-NLS-1$//$NON-NLS-2$

    // Try to use the value in the settings if it was set.
    String lookAndFeelClassName = anathemaPreferences.getUserLookAndFeel();
    if (lookAndFeelClassName == null) {
      lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
    }

    if (!trySetLookAndFeel(lookAndFeelClassName)) {
      trySetLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      // In case we have failed again, use the current look and feel
      // which is still better than a crash.
    }
  }

  private static boolean isAqua(String lookAndFeelClassName) {
    return lookAndFeelClassName.contains(AQUA_LOOK_AND_FEEL_CLASSNAME);
  }
}