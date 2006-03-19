package net.sf.anathema.framework.environment;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import net.sf.anathema.framework.configuration.IAnathemaPreferences;

import com.l2fprod.common.swing.plaf.LookAndFeelAddons;
import com.l2fprod.common.swing.plaf.metal.MetalLookAndFeelAddons;
import com.l2fprod.common.swing.plaf.windows.WindowsLookAndFeelAddons;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

public class LookAndFeelInitializer {

  private static final String WINDOWS_LOOK_AND_FEEL_CLASSNAME = WindowsLookAndFeel.class.getName();
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
      LookAndFeelAddons.setAddon(WindowsLookAndFeelAddons.class);
    }
    UIManager.setLookAndFeel(lookAndFeelClassName);
  }

  private boolean useMetalLookAndFeel(String lookAndFeelClassName) {
    boolean forceMetalLookAndFeel = anathemaPreferences.isMetalLookAndFeelForced();
    return !lookAndFeelClassName.equals(WINDOWS_LOOK_AND_FEEL_CLASSNAME) || forceMetalLookAndFeel;
  }
}