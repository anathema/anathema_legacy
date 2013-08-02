package net.sf.anathema.platform.tree.fx;

import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Transform;
import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.Circle;
import net.sf.anathema.platform.tree.display.shape.Polygon;
import net.sf.anathema.platform.tree.display.shape.ShapeVisitor;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;

public class FxTransformer {
  public static Transform convert(AgnosticTransform transform) {
    return Transform.affine(transform.a1, transform.a2, transform.b1, transform.b2, transform.c1, transform.c2);
  }

  public static Shape convert(AgnosticShape shape) {
    final Shape[] result = new Shape[1];
    shape.accept(new ShapeVisitor() {
      @Override
      public void visitPolygon(Polygon polygon) {
        javafx.scene.shape.Polygon fxPolygon = new javafx.scene.shape.Polygon();
        for (Coordinate coordinate : polygon.coordinates) {
          fxPolygon.getPoints().addAll((double) coordinate.x, (double) coordinate.y);
        }
        result[0] = fxPolygon;
      }

      @Override
      public void visitCircle(Circle circle) {
        int radius = circle.diameter / 2;
        Ellipse fxCircle = new Ellipse(circle.centerX, circle.centerY, radius, radius);
        result[0] = fxCircle;
      }
    });
    return result[0];
  }
}