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
}