package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.view.interaction.ShapeWithPosition;
import org.jmock.example.announcer.Announcer;

import java.awt.Polygon;
import java.awt.Rectangle;

public class FilledPolygon implements InteractiveGraphicsElement, AgnosticPolygon {
  private final Announcer<Runnable> toggleListeners = Announcer.to(Runnable.class);
  private final Polygon polygon = new Polygon();
  private RGBColor fill = RGBColor.Transparent;
  private RGBColor stroke = RGBColor.Black;
  private final TextWriter textWriter = new TextWriter(polygon);

  @Override
  public void paint(Canvas graphics) {
    new ShapeFiller(polygon, fill).fill(graphics);
    new ShapeDrawer(polygon, stroke).draw(graphics);
    textWriter.write(graphics);
  }

  @Override
  public boolean contains(Coordinate point) {
    return polygon.contains(point.x, point.y);
  }

  @Override
  public void toggle() {
    toggleListeners.announce().run();
  }

  public void addPoint(int x, int y) {
    polygon.addPoint(x, y);
  }

  public void moveBy(int x, int y) {
    polygon.translate(x, y);
  }

  public void fill(RGBColor fill) {
    this.fill = fill;
  }

  public void setAlpha(int alpha) {
    RGBColor original = fill;
    this.fill = new RGBColor(original, alpha);
    setStroke(new RGBColor(stroke, alpha));
  }


  public void whenToggledDo(Runnable runnable) {
    toggleListeners.addListener(runnable);
  }

  public void setText(String text) {
    textWriter.setText(text);
  }

  public void position(ShapeWithPosition control) {
    Rectangle bounds = polygon.getBounds();
    control.setPosition((int) bounds.getMinX(), (int) bounds.getMaxY() + 10);
    control.setWidth((int) bounds.getWidth());
  }

  public void setStroke(RGBColor stroke) {
    this.stroke = stroke;
    textWriter.setStroke(stroke);
  }
}