package net.sf.anathema.platform.tree.view.draw;

import static org.mockito.Mockito.mock;

public class PolygonMother {

  public static FilledPolygon any() {
    return mock(FilledPolygon.class);
  }

  public static FilledPolygon squareAtOriginWithLength2() {
    FilledPolygon polygon = new FilledPolygon();
    polygon.addPoint(0, 0);
    polygon.addPoint(2, 0);
    polygon.addPoint(2, 2);
    polygon.addPoint(0, 2);
    return polygon;
  }
}