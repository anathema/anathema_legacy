package net.sf.anathema;

import org.java.plugin.boot.Boot;

public class AnathemaBootLoader {

  public static void main(String[] arguments) throws Exception {
    new AnathemaPrebootSplashscreen().displayStatusMessage("Collecting Plugins..."); //$NON-NLS-1$
    Boot.main(arguments);
  }
}