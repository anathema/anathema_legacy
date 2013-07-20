package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Width;
import net.sf.anathema.platform.tree.document.components.ExtensibleArrow;

import java.awt.geom.Ellipse2D;

public class FlexibleArrow implements GraphicsElement, ExtensibleArrow {
  private static final int RADIUS = 6;
  private static final int DIAMETER = RADIUS * 2;
  private final Coordinates coordinates = new Coordinates();

  @Override
  public void addPoint(int x, int y) {
    coordinates.add(new Coordinate(x, y));
  }

  @Override
  public void paint(Canvas canvas) {
    canvas.setStrokeWidth(new Width(6));
    canvas.setColor(RGBColor.Black);
    canvas.drawPolyline(coordinates);
    Ellipse2D bottom = createCircleAtBottom();
    canvas.fill(bottom);
    new ArrowHead(coordinates.getPenultimatePoint(), coordinates.getUltimatePoint()).paint(canvas);
  }

  private Ellipse2D createCircleAtBottom() {
    Coordinate origin = coordinates.getPointOfOrigin();
    return new Ellipse2D.Float(origin.x - RADIUS, origin.y - RADIUS, DIAMETER, DIAMETER);
  }

  public void moveBy(int x, int y) {
    coordinates.translate(x, y);
  }
}