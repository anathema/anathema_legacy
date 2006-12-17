package net.sf.anathema;


public class AnathemaSplashscreen {
//  private final static Rectangle2D.Double textAreaRectangle = new Rectangle2D.Double(93, 318, 454, 19);
  private final static AnathemaSplashscreen instance = new AnathemaSplashscreen();
//  private final FontRenderContext renderContext = new FontRenderContext(null, true, false);
//  private Graphics2D graphics;
//  private Paint textAreaGradient;
//  private Font font;

  private AnathemaSplashscreen() {
//    if (!isSplashScreenSupported()) {
//      return;
//    }
//    Color startColor = new Color(12, 28, 59);
//    Color endColor = new Color(10, 21, 46);
//    Rectangle2D bounds = textAreaRectangle.getBounds2D();
//    this.textAreaGradient = new GradientPaint(
//        (int) bounds.getMinX(),
//        (int) bounds.getMinY(),
//        startColor,
//        (int) bounds.getMaxX(),
//        (int) bounds.getMaxY(),
//        endColor);
//    this.graphics = SplashScreen.getSplashScreen().createGraphics();
//    this.font = graphics.getFont().deriveFont(Font.BOLD);
  }

  public static AnathemaSplashscreen getInstance() {
    return instance;
  }

  public void displayStatusMessage(String message) {
//    if (!isSplashScreenSupported()) {
//      return;
//    }
//    SplashScreen splashScreen = SplashScreen.getSplashScreen();
//    if (splashScreen == null || !splashScreen.isVisible()) {
//      return;
//    }
//    resetTextArea();
//    TextLayout layout = new TextLayout(message, font, renderContext);
//    layout.draw(graphics, 105, 333);
//    splashScreen.update();
  }

  private void resetTextArea() {
//    Paint oldPaint = graphics.getPaint();
//    graphics.setPaint(textAreaGradient);
//    graphics.fill(textAreaRectangle);
//    graphics.setPaint(oldPaint);
  }

  public void displayVersion(String string) {
//    if (!isSplashScreenSupported()) {
//      return;
//    }
//    SplashScreen splashScreen = SplashScreen.getSplashScreen();
//    if (splashScreen == null || !splashScreen.isVisible()) {
//      return;
//    }
//    TextLayout layout = new TextLayout(string, font.deriveFont(font.getSize2D() + 2), renderContext);
//    layout.draw(graphics, 445, 91);
//    splashScreen.update();
  }

//  private boolean isSplashScreenSupported() {
//   return !System.getProperty("java.version").startsWith("1.5"); //$NON-NLS-1$ //$NON-NLS-2$
//  }
}