package net.sf.anathema;

public class ProxySplashscreen implements ISplashscreen {

  private static final ISplashscreen instance = new ProxySplashscreen();
  private final ISplashscreen screen = new AnathemaSplashscreen();

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