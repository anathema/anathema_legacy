package net.sf.anathema.platform.tree.display.shape;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.Coordinates;

public class Polygon implements AgnosticShape {
  public final Coordinates coordinates = new Coordinates();

  public void addPoint(int x, int y) {
    coordinates.add(new Coordinate(x, y));
  }

  @Override
  public void accept(ShapeVisitor visitor) {
    visitor.visitPolygon(this);
  }

  public void translate(int dx, int dy) {
    coordinates.translate(dx, dy);
  }

  public boolean contains(Coordinate candidate) {
    //Should test for inside bounds first if lacking performance
    return testContainmentWithRaycasting(candidate);
  }

  private boolean testContainmentWithRaycasting(Coordinate candidate) {
    int currentIndex = 0;
    int indexBefore = coordinates.count() - 1;
    boolean isInside = false;
    for (; currentIndex < coordinates.count(); currentIndex++) {
      Coordinate currentPoint = coordinates.get(currentIndex);
      Coordinate pointBefore = coordinates.get(indexBefore);
      if ((currentPoint.y > candidate.y) != (pointBefore.y > candidate.y) &&
              (candidate.x < (pointBefore.x - currentPoint.x) * (candidate.y - currentPoint.y) / (pointBefore.y - currentPoint.y) + currentPoint.x)) {
        isInside = !isInside;
      }
      indexBefore = currentIndex;
    }
    return isInside;
  }
}