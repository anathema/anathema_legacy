package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Width;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;

public class ShapeDrawer {
  private final AgnosticShape shape;
  private final RGBColor stroke;

  public ShapeDrawer(AgnosticShape shape, RGBColor stroke) {
    this.shape = shape;
    this.stroke = stroke;
  }

  public void draw(Canvas graphics) {
    graphics.setColor(stroke);
    graphics.setStrokeWidth(new Width(4));
    graphics.draw(shape);
  }
}
