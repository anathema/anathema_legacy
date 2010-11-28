package net.sf.anathema;

public class ProxySplashscreen implements ISplashscreen {

  private static ISplashscreen instance = new ProxySplashscreen();
  private final ISplashscreen screen;

  {
    if (AnathemaSplashscreen.isSplashScreenSupported()) { //$NON-NLS-1$ //$NON-NLS-2$
      screen = new AnathemaSplashscreen();
    }
    else {
      screen = new NullSplashscreen();
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