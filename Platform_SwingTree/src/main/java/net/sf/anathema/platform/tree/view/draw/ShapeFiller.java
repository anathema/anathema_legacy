package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.TransformedShape;

public class ShapeFiller {
  private final AgnosticShape shape;
  private final RGBColor color;

  public ShapeFiller(AgnosticShape shape, RGBColor color) {
    this.shape = shape;
    this.color = color;
  }

  public void fill(Canvas graphics) {
    graphics.setColor(color);
    graphics.fill(new TransformedShape(shape));
  }
}