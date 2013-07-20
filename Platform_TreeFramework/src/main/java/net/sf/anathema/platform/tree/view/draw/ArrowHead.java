package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.Polygon;
import net.sf.anathema.platform.tree.display.shape.TransformedShape;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;
import net.sf.anathema.platform.tree.display.transform.Rotation;
import net.sf.anathema.platform.tree.display.transform.Translation;

import static java.lang.Math.atan2;

public class ArrowHead {

  public static final AgnosticShape ArrowShape = createShape();

  private static AgnosticShape createShape() {
    Polygon arrowHead = new Polygon();
    arrowHead.addPoint(0, 7);
    arrowHead.addPoint(-14, -7);
    arrowHead.addPoint(0, 0);
    arrowHead.addPoint(14, -7);
    return arrowHead;
  }

  private final Coordinate penultimatePoint;
  private final Coordinate ultimatePoint;

  public ArrowHead(Coordinate penultimatePoint, Coordinate ultimatePoint) {
    this.penultimatePoint = penultimatePoint;
    this.ultimatePoint = ultimatePoint;
  }

  public void paint(Canvas graphics) {
    AgnosticTransform transform = createTransformation();
    TransformedShape transformedShape = new TransformedShape(ArrowShape, transform);
    graphics.fill(transformedShape);
  }

  private AgnosticTransform createTransformation() {
    AgnosticTransform transform = new AgnosticTransform();
    double angle = atan2(ultimatePoint.y - penultimatePoint.y, ultimatePoint.x - penultimatePoint.x);
    transform.add(new Translation(ultimatePoint.x, ultimatePoint.y));
    transform.add(new Rotation(angle - Math.PI / 2d));
    return transform;
  }
}