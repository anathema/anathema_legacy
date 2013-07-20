package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.FontStyle;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Width;

import java.awt.FontMetrics;
import java.awt.Shape;
import java.util.List;

public interface Canvas {
  void setStrokeWidth(Width width);

  void setColor(RGBColor color);

  void drawPolyline(List<Coordinate> coordinates);

  void fill(Shape shape);

  void draw(Shape shape);

  FontMetrics getFontMetrics();

  void drawString(String text, Coordinate coordinate);

  void setFontStyle(FontStyle style, int textSize);
}