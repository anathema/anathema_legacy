package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.view.transform.AgnosticTransform;
import net.sf.anathema.platform.tree.view.transform.Rotation;
import net.sf.anathema.platform.tree.view.transform.SwingTransformer;
import net.sf.anathema.platform.tree.view.transform.Translation;

import java.awt.Polygon;
import java.awt.Shape;

import static java.lang.Math.atan2;

public class ArrowHead {

  public static final Shape ArrowShape = createShape();

  private static Shape createShape() {
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
    Shape transformedShape = SwingTransformer.convert(transform).createTransformedShape(ArrowShape);
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