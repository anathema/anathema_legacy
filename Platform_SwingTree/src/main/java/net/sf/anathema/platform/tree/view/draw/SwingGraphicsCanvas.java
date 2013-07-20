package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Width;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;

import static net.sf.anathema.lib.gui.swing.ColorUtilities.toAwtColor;

public class SwingGraphicsCanvas implements Canvas {

  private Graphics2D graphics;

  public SwingGraphicsCanvas(Graphics2D graphics) {
    this.graphics = graphics;
  }

  @Override
  public void setStrokeWidth(Width width) {
    graphics.setStroke(new BasicStroke(width.width));
  }

  @Override
  public void setColor(RGBColor color) {
    graphics.setColor(toAwtColor(color));
  }

  @Override
  public void drawPolyline(int[] xCoordinates, int[] yCoordinates, int length) {
    graphics.drawPolyline(xCoordinates, yCoordinates, length);
  }

  @Override
  public void fill(Shape shape) {
    graphics.fill(shape);
  }

  @Override
  public void draw(Shape shape) {
    graphics.draw(shape);
  }

  @Override
  public void setFont(Font textFont) {
    graphics.setFont(textFont);
  }

  @Override
  public FontMetrics getFontMetrics(Font textFont) {
    return graphics.getFontMetrics(textFont);
  }

  @Override
  public void drawString(String part, int x, int y) {
    graphics.drawString(part, x, y);
  }
}