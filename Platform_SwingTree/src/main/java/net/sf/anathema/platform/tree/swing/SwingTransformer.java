package net.sf.anathema.platform.tree.swing;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class SwingTransformer {
  public static AffineTransform convert(AgnosticTransform transform) {
    return new AffineTransform(transform.a1, transform.a2, transform.b1, transform.b2, transform.c1, transform.c2);
  }

  public static Shape convert(AgnosticShape shape) {
    SwingShapeVisitor visitor = new SwingShapeVisitor();
    shape.accept(visitor);
    return visitor.getShape();
  }

  public static Point convert(Coordinate coordinate) {
    return new Point(coordinate.x, coordinate.y);
  }
}