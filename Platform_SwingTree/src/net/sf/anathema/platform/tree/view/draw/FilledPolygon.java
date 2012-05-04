package net.sf.anathema.platform.tree.view.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Point2D;
import java.util.Random;

public class FilledPolygon {
  private final Polygon polygon = new Polygon();
  private Color fill = Color.YELLOW;

  public void paint(Graphics2D graphics) {
    new ShapeFiller(polygon, fill).fill(graphics);
    new ShapeDrawer(polygon).draw(graphics);
  }

  public boolean contains(Point2D p) {
    return polygon.contains(p);
  }

  public void toggle() {
    this.fill = getColor();
  }

  private Color getColor() {
    return new Color(new Random().nextInt(0xffffff));
  }

  public void addPoint(int x, int y) {
    polygon.addPoint(x, y);
  }

  public void moveBy(int x, int y) {
    polygon.translate(x, y);
  }
}