package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.Coordinates;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.framework.ui.Width;
import net.sf.anathema.platform.tree.display.shape.AgnosticShape;
import net.sf.anathema.platform.tree.display.shape.Circle;
import net.sf.anathema.platform.tree.display.shape.TransformedShape;
import net.sf.anathema.platform.tree.document.components.ExtensibleArrow;

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
    AgnosticShape bottom = createCircleAtBottom();
    canvas.fill(new TransformedShape(bottom));
    new ArrowHead(coordinates.getPenultimatePoint(), coordinates.getUltimatePoint()).paint(canvas);
  }

  private AgnosticShape createCircleAtBottom() {
    Coordinate origin = coordinates.getPointOfOrigin();
    return new Circle(origin.x, origin.y, DIAMETER);
  }

  public void moveBy(int x, int y) {
    coordinates.translate(x, y);
  }
}