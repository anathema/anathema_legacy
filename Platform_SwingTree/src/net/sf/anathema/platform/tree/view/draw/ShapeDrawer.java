package net.sf.anathema.platform.tree.view.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public class ShapeDrawer {
  private final Shape shape;

  public ShapeDrawer(Shape shape) {
    this.shape = shape;
  }

  public void draw(Graphics2D graphics) {
    graphics.setColor(Color.BLACK);
    graphics.setStroke(new BasicStroke(4));
    graphics.draw(shape);
  }
}
