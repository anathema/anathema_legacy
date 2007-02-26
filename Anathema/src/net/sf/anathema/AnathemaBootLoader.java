package net.sf.anathema;

import org.java.plugin.boot.Boot;

public class AnathemaBootLoader {

  public static void main(String[] arguments) throws Exception {
    if (!System.getProperty("java.version").startsWith("1.5")) { //$NON-NLS-1$ //$NON-NLS-2$
      new AnathemaPrebootSplashscreen().displayStatusMessage("Collecting Plugins..."); //$NON-NLS-1$
    }
    Boot.main(arguments);
  }
}