package net.sf.anathema.platform.tree.swing;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;
import net.sf.anathema.platform.tree.display.transform.TransformOperation;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class SwingTransformer {
  public static AffineTransform convert(AgnosticTransform transform) {
    final AffineTransform affineTransform = new AffineTransform();
    SwingTransformVisitor visitor = new SwingTransformVisitor(affineTransform);
    transform.visitOperations(visitor);
    return affineTransform;
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