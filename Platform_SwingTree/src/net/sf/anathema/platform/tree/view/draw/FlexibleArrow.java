package net.sf.anathema.platform.tree.view.draw;

import com.google.common.collect.Lists;
import net.sf.anathema.platform.tree.document.components.ExtensibleArrow;
import org.apache.commons.lang3.ArrayUtils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class FlexibleArrow implements GraphicsElement, ExtensibleArrow {
  private static final int RADIUS = 6;
  private static final int DIAMETER = RADIUS * 2;
  private final List<Point> points = new ArrayList<Point>();

  @Override
  public void addPoint(int x, int y) {
    points.add(new Point(x, y));
  }

  @Override
  public void paint(Graphics2D graphics) {
    int[] xCoordinates = collectXCoordinates();
    int[] yCoordinates = collectYCoordinates();
    graphics.setStroke(new BasicStroke(6));
    graphics.setColor(Color.BLACK);
    graphics.drawPolyline(xCoordinates, yCoordinates, xCoordinates.length);
    Ellipse2D bottom = createCircleAtBottom();
    graphics.fill(bottom);
    new ArrowHead(points.get(points.size() - 2), points.get(points.size() - 1)).paint(graphics);
  }

  private Ellipse2D createCircleAtBottom() {
    Point origin = points.get(0);
    return new Ellipse2D.Float(origin.x - RADIUS, origin.y - RADIUS, DIAMETER, DIAMETER);
  }

  private int[] collectXCoordinates() {
    List<Integer> coordinates = Lists.newArrayList();
    for (Point point : points) {
      coordinates.add(point.x);
    }
    return ArrayUtils.toPrimitive(coordinates.toArray(new Integer[coordinates.size()]));
  }

  private int[] collectYCoordinates() {
    List<Integer> coordinates = Lists.newArrayList();
    for (Point point : points) {
      coordinates.add(point.y);
    }
    return ArrayUtils.toPrimitive(coordinates.toArray(new Integer[coordinates.size()]));
  }

  public void moveBy(int x, int y) {
    for (Point point : points) {
      point.translate(x, y);
    }
  }
}