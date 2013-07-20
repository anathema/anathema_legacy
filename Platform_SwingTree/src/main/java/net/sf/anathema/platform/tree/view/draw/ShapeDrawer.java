package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Width;

import java.awt.Shape;

public class ShapeDrawer {
  private final Shape shape;
  private final RGBColor stroke;

  public ShapeDrawer(Shape shape, RGBColor stroke) {
    this.shape = shape;
    this.stroke = stroke;
  }

  public void draw(Canvas graphics) {
    graphics.setColor(stroke);
    graphics.setStrokeWidth(new Width(4));
    graphics.draw(shape);
  }
}
