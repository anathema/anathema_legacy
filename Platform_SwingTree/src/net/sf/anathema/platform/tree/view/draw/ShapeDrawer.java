package net.sf.anathema.platform.tree.view.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public class ShapeDrawer {
  private final Shape shape;
  private final Color stroke;

  public ShapeDrawer(Shape shape, Color stroke) {
    this.shape = shape;
    this.stroke = stroke;
  }

  public void draw(Graphics2D graphics) {
    graphics.setColor(stroke);
    graphics.setStroke(new BasicStroke(4));
    graphics.draw(shape);
  }
}
