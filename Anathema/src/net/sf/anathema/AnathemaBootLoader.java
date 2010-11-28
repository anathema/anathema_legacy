package net.sf.anathema;

import java.security.AccessController;

import org.java.plugin.boot.Boot;

import sun.security.action.GetPropertyAction;

public class AnathemaBootLoader {

  public static void main(String[] arguments) throws Exception {
    if (isSplashScreenSupported()) { //$NON-NLS-1$ //$NON-NLS-2$
      new AnathemaPrebootSplashscreen().displayStatusMessage("Collecting Plugins..."); //$NON-NLS-1$
    }
    Boot.main(arguments);
  }
  
  public static boolean isSplashScreenSupported() {
	String osName = (String)AccessController.doPrivileged(new GetPropertyAction("os.name"));
    return !osName.contains("Mac OS X"); //$NON-NLS-1$ //$NON-NLS-2$
  }
}