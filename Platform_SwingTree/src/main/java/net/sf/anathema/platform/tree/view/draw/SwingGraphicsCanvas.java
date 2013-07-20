package net.sf.anathema.platform.tree.view.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;

public class SwingGraphicsCanvas implements Canvas {

  private Graphics2D graphics;

  public SwingGraphicsCanvas(Graphics2D graphics) {
    this.graphics = graphics;
  }

  @Override
  public void setStroke(BasicStroke basicStroke) {
    graphics.setStroke(basicStroke);
  }

  @Override
  public void setColor(Color color) {
    graphics.setColor(color);
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