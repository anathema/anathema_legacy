package net.sf.anathema.platform.tree.view.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public class ShapeFiller {
  private final Shape shape;
  private final Color color;

  public ShapeFiller(Shape shape, Color color) {
    this.shape = shape;
    this.color = color;
  }

  public void fill(Graphics2D graphics) {
    graphics.setColor(color);
    graphics.fill(shape);
  }
}