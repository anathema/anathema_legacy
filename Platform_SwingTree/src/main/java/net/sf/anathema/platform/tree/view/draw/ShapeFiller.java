package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.RGBColor;

import java.awt.Shape;

public class ShapeFiller {
  private final Shape shape;
  private final RGBColor color;

  public ShapeFiller(Shape shape, RGBColor color) {
    this.shape = shape;
    this.color = color;
  }

  public void fill(Canvas graphics) {
    graphics.setColor(color);
    graphics.fill(shape);
  }
}