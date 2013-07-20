package net.sf.anathema.platform.tree.display.shape;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.Coordinates;
import org.apache.commons.lang3.builder.EqualsBuilder;

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

  //*Adapted from http://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html*/
  private boolean testContainmentWithRaycasting(Coordinate candidate) {
    int currentIndex = 0;
    int indexBefore = coordinates.count() - 1;
    boolean isInside = false;
    for (; currentIndex < coordinates.count(); currentIndex++) {
      Coordinate currentPoint = coordinates.get(currentIndex);
      Coordinate pointBefore = coordinates.get(indexBefore);
      boolean isNotAboveOrBelowBothReferencePoints = (currentPoint.y > candidate.y) != (pointBefore.y > candidate.y);
      boolean hasCrossedPolygonBoundary = isNotAboveOrBelowBothReferencePoints &&
              (candidate.x < (pointBefore.x - currentPoint.x) * (candidate.y - currentPoint.y) / (pointBefore.y - currentPoint.y) + currentPoint.x);
      if (hasCrossedPolygonBoundary) {
        isInside = !isInside;
      }
      indexBefore = currentIndex;
    }
    return isInside;
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return coordinates.hashCode();
  }
}