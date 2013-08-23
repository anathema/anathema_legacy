package net.sf.anathema.framework.environment;

import static java.awt.Desktop.isDesktopSupported;

public class SwingEnvironment {

  public static boolean isAutoOpenSupported() {
    return isDesktopSupported();
  }
}