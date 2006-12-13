package net.sf.anathema;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;

public class AnathemaPrebootSplashscreen {
  private final static AnathemaPrebootSplashscreen instance = new AnathemaPrebootSplashscreen();
  private final Graphics2D graphics = SplashScreen.getSplashScreen().createGraphics();

  private AnathemaPrebootSplashscreen() {
    graphics.setFont(graphics.getFont().deriveFont(Font.BOLD));
  }

  public static AnathemaPrebootSplashscreen getInstance() {
    return instance;
  }

  public void displayStatusMessage(String message) {
    SplashScreen splashScreen = SplashScreen.getSplashScreen();
    if (splashScreen == null || !splashScreen.isVisible()) {
      return;
    }
    graphics.drawString(message, 105, 333);
    splashScreen.update();
  }
}