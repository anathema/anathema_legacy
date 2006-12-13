package net.sf.anathema;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.SplashScreen;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class AnathemaSplashscreen {
  private final static Rectangle2D.Double textAreaRectangle = new Rectangle2D.Double(93, 318, 454, 19);
  private final static AnathemaSplashscreen instance = new AnathemaSplashscreen();
  private final Graphics2D graphics = SplashScreen.getSplashScreen().createGraphics();
  private final FontRenderContext renderContext = new FontRenderContext(null, true, false);
  private final Paint textAreaGradient;
  private final Font font;

  private AnathemaSplashscreen() {
    Color startColor = new Color(12, 28, 59);
    Color endColor = new Color(10, 21, 46);
    Rectangle2D bounds = textAreaRectangle.getBounds2D();
    this.textAreaGradient = new GradientPaint(
        (int) bounds.getMinX(),
        (int) bounds.getMinY(),
        startColor,
        (int) bounds.getMaxX(),
        (int) bounds.getMaxY(),
        endColor);
    this.font = graphics.getFont().deriveFont(Font.BOLD);
  }

  public static AnathemaSplashscreen getInstance() {
    return instance;
  }

  public void displayStatusMessage(String message) {
    SplashScreen splashScreen = SplashScreen.getSplashScreen();
    if (splashScreen == null || !splashScreen.isVisible()) {
      return;
    }
    resetTextArea();
    TextLayout layout = new TextLayout(message, font, renderContext);
    layout.draw(graphics, 105, 333);
    splashScreen.update();
  }

  private void resetTextArea() {
    Paint oldPaint = graphics.getPaint();
    graphics.setPaint(textAreaGradient);
    graphics.fill(textAreaRectangle);
    graphics.setPaint(oldPaint);
  }

  public void displayVersion(String string) {
    SplashScreen splashScreen = SplashScreen.getSplashScreen();
    if (splashScreen == null || !splashScreen.isVisible()) {
      return;
    }
    TextLayout layout = new TextLayout(string, font.deriveFont(font.getSize2D() + 2), renderContext);
    layout.draw(graphics, 445, 91);
    splashScreen.update();
  }
}