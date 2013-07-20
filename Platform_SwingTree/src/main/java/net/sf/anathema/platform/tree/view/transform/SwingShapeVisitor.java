package net.sf.anathema.platform.tree.view.transform;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.shape.Circle;
import net.sf.anathema.platform.tree.display.shape.Polygon;
import net.sf.anathema.platform.tree.display.shape.ShapeVisitor;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class SwingShapeVisitor implements ShapeVisitor {
  private Shape result;

  public Shape getShape() {
    return result;
  }

  @Override
  public void visitPolygon(Polygon polygon) {
    java.awt.Polygon swingPolygon = new java.awt.Polygon();
    for (Coordinate coordinate : polygon.coordinates) {
      swingPolygon.addPoint(coordinate.x, coordinate.y);
    }
    this.result = swingPolygon;
  }

  @Override
  public void visitCircle(Circle circle) {
    this.result = new Ellipse2D.Float(circle.centerX, circle.centerY, circle.diameter, circle.diameter);
  }
}
