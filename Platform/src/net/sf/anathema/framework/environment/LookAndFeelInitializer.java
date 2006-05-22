package net.sf.anathema.framework.environment;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import net.sf.anathema.framework.configuration.IAnathemaPreferences;

import com.l2fprod.common.swing.plaf.LookAndFeelAddons;
import com.l2fprod.common.swing.plaf.metal.MetalLookAndFeelAddons;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class LookAndFeelInitializer {

  private static final String WINDOWS_LOOK_AND_FEEL_CLASSNAME = WindowsLookAndFeel.class.getName();
  private static final String AQUA_LOOK_AND_FEEL_CLASSNAME = "apple.laf.AquaLookAndFeel"; //$NON-NLS-1$
  private static final String AQUA_USE_SCREEN_MENU_BAR = "apple.laf.useScreenMenuBar"; //$NON-NLS-1$
  private static final String AQUA_APPLICATION_NAME = "com.apple.mrj.application.apple.menu.about.name"; //$NON-NLS-1$
  private final IAnathemaPreferences anathemaPreferences;

  public LookAndFeelInitializer(IAnathemaPreferences anathemaPreferences) {
    this.anathemaPreferences = anathemaPreferences;
  }

  public void initialize()
      throws InstantiationException,
      IllegalAccessException,
      ClassNotFoundException,
      UnsupportedLookAndFeelException {
    String lookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
    if (useMetalLookAndFeel(lookAndFeelClassName)) {
      LookAndFeelAddons.setAddon(MetalLookAndFeelAddons.class);
      lookAndFeelClassName = MetalLookAndFeel.class.getName();
    }
    else {
      LookAndFeelAddons.setAddon(LookAndFeelAddons.getSystemAddonClassName());
    }
    if (isAqua(lookAndFeelClassName)) {
      System.setProperty(AQUA_USE_SCREEN_MENU_BAR, "true"); //$NON-NLS-1$
      System.setProperty(AQUA_APPLICATION_NAME, "Anathema"); //$NON-NLS-1$
    }
    UIManager.setLookAndFeel(lookAndFeelClassName);
  }

  private boolean useMetalLookAndFeel(String lookAndFeelClassName) {
    boolean forceMetalLookAndFeel = anathemaPreferences.isMetalLookAndFeelForced();
    final boolean isWindowsLookAndFeel = lookAndFeelClassName.equals(WINDOWS_LOOK_AND_FEEL_CLASSNAME);
    return (!isWindowsLookAndFeel && !isAqua(lookAndFeelClassName)) || forceMetalLookAndFeel;
  }

  private boolean isAqua(String lookAndFeelClassName) {
    return lookAndFeelClassName.equals(AQUA_LOOK_AND_FEEL_CLASSNAME);
  }
}