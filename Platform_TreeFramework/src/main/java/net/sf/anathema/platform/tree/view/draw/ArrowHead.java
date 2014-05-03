package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.Vector;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.Polygon;
import net.sf.anathema.platform.tree.display.shape.TransformedShape;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;

public class ArrowHead {

  private static final double ARROW_SIZE = 10;

  private final Coordinate penultimatePoint;
  private final Coordinate ultimatePoint;

  public ArrowHead(Coordinate penultimatePoint, Coordinate ultimatePoint) {
    this.penultimatePoint = penultimatePoint;
    this.ultimatePoint = ultimatePoint;
  }

  public void paint(Canvas graphics) {
    AgnosticShape shape = createArrowShape();
    TransformedShape transformedShape = new TransformedShape(shape, new AgnosticTransform());
    graphics.fill(transformedShape);
  }

  private AgnosticShape createArrowShape() {
    Vector direction = calculateDirectionVector();
    Vector normalizedDirection = normalizeVector(direction);

    double xTranslation = normalizedDirection.dx * ARROW_SIZE;
    double yTranslation = normalizedDirection.dy * ARROW_SIZE;
    Coordinate arrowTarget = new Coordinate(ultimatePoint.x + xTranslation, ultimatePoint.y + yTranslation);
    Coordinate arrowPoint1 = new Coordinate(ultimatePoint.x - xTranslation - yTranslation, ultimatePoint.y - yTranslation + xTranslation);
    Coordinate arrowPoint2 = new Coordinate(ultimatePoint.x - xTranslation + yTranslation, ultimatePoint.y - yTranslation - xTranslation);

    Polygon arrowHead = new Polygon();
    arrowHead.addPoint(arrowTarget.x, arrowTarget.y);
    arrowHead.addPoint(arrowPoint1.x, arrowPoint1.y);
    arrowHead.addPoint(arrowPoint2.x, arrowPoint2.y);
    return arrowHead;
  }

  private Vector normalizeVector(Vector direction) {
    double length = Math.sqrt(direction.dx * direction.dx + direction.dy * direction.dy);
    double unitDx = direction.dx / length;
    double unitDy = direction.dy / length;
    return new Vector(unitDx, unitDy);
  }

  private Vector calculateDirectionVector() {
    double dx = ultimatePoint.x - penultimatePoint.x;
    double dy = ultimatePoint.y - penultimatePoint.y;
    return new Vector(dx, dy);
  }
}