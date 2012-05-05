package net.sf.anathema.platform.tree.view.draw;

public class PolygonMother {

  public static InteractiveGraphicsElement squareAtOriginWithLength2() {
    FilledPolygon polygon = new FilledPolygon();
    polygon.addPoint(0, 0);
    polygon.addPoint(2, 0);
    polygon.addPoint(2, 2);
    polygon.addPoint(0, 2);
    return polygon;
  }
}