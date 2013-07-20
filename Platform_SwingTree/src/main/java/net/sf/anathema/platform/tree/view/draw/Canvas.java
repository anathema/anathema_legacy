package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Width;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Shape;

public interface Canvas {
  void setStrokeWidth(Width width);

  void setColor(RGBColor color);

  void drawPolyline(int[] xCoordinates, int[] yCoordinates, int length);

  void fill(Shape shape);

  void draw(Shape shape);

  void setFont(Font textFont);

  FontMetrics getFontMetrics(Font textFont);

  void drawString(String part, int centeredX, int actualY);
}