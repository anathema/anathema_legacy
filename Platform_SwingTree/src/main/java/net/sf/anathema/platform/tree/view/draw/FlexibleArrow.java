package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Width;
import net.sf.anathema.platform.tree.document.components.ExtensibleArrow;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class FlexibleArrow implements GraphicsElement, ExtensibleArrow {
  private static final int RADIUS = 6;
  private static final int DIAMETER = RADIUS * 2;
  private final List<Point> points = new ArrayList<>();

  @Override
  public void addPoint(int x, int y) {
    points.add(new Point(x, y));
  }

  @Override
  public void paint(Canvas graphics) {
    graphics.setStrokeWidth(new Width(6));
    graphics.setColor(RGBColor.Black);
    List<Coordinate> coordinates = convertToCoordinates(points);
    graphics.drawPolyline(coordinates);
    Ellipse2D bottom = createCircleAtBottom();
    graphics.fill(bottom);
    new ArrowHead(points.get(points.size() - 2), points.get(points.size() - 1)).paint(graphics);
  }

  private List<Coordinate> convertToCoordinates(List<Point> points) {
    List<Coordinate> coordinates = new ArrayList<>();
    for (Point point : points) {
      coordinates.add(new Coordinate(point.x, point.y));
    }
    return coordinates;
  }

  private Ellipse2D createCircleAtBottom() {
    Point origin = points.get(0);
    return new Ellipse2D.Float(origin.x - RADIUS, origin.y - RADIUS, DIAMETER, DIAMETER);
  }

  public void moveBy(int x, int y) {
    for (Point point : points) {
      point.translate(x, y);
    }
  }
}