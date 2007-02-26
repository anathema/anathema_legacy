package net.sf.anathema;

public class ProxySplashscreen implements ISplashscreen {

  private static ISplashscreen instance = new ProxySplashscreen();
  private final ISplashscreen screen;

  {
    if (System.getProperty("java.version").startsWith("1.5")) { //$NON-NLS-1$ //$NON-NLS-2$
      screen = new NullSplashscreen();
    }
    else {
      screen = new AnathemaSplashscreen();
    }
  }

  public static ISplashscreen getInstance() {
    return instance;
  }

  @Override
  public void displayStatusMessage(String message) {
    screen.displayStatusMessage(message);
  }

  @Override
  public void displayVersion(String string) {
    screen.displayVersion(string);
  }
}