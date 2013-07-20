package net.sf.anathema.platform.tree.view.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Shape;

public interface Canvas {
  void setStroke(BasicStroke basicStroke);

  void setColor(Color black);

  void drawPolyline(int[] xCoordinates, int[] yCoordinates, int length);

  void fill(Shape shape);

  void draw(Shape shape);

  void setFont(Font textFont);

  FontMetrics getFontMetrics(Font textFont);

  void drawString(String part, int centeredX, int actualY);
}