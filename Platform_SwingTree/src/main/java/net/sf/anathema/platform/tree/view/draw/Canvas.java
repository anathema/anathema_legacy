package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.FontStyle;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Width;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.TransformedShape;

import java.awt.FontMetrics;

public interface Canvas {
  void setStrokeWidth(Width width);

  void setColor(RGBColor color);

  void drawPolyline(Iterable<Coordinate> coordinates);

  void fill(TransformedShape shape);

  void draw(AgnosticShape shape);

  FontMetrics getFontMetrics();

  void drawString(String text, Coordinate coordinate);

  void setFontStyle(FontStyle style, int textSize);
}