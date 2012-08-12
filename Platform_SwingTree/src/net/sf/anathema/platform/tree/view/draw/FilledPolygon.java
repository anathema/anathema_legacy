package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.lib.gui.swing.ColorUtilities;
import net.sf.anathema.platform.tree.view.interaction.SpecialControl;
import org.jmock.example.announcer.Announcer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class FilledPolygon implements InteractiveGraphicsElement {
  private final Announcer<Runnable> toggleListeners = Announcer.to(Runnable.class);
  private final Polygon polygon = new Polygon();
  private Color fill = Color.YELLOW;
  private Color stroke = Color.BLACK;
  private String text = "";

  @Override
  public void paint(Graphics2D graphics) {
    new ShapeFiller(polygon, fill).fill(graphics);
    new ShapeDrawer(polygon, stroke).draw(graphics);
    new TextWriter(polygon.getBounds(), stroke, text).write(graphics);
  }

  @Override
  public boolean contains(Point2D p) {
    return polygon.contains(p);
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

  public void fill(Color fill) {
    this.fill = fill;
  }

  public void setAlpha(int alpha) {
    Color original = fill;
    fill(ColorUtilities.getTransparentColor(original, alpha));
    this.stroke = ColorUtilities.getTransparentColor(stroke, alpha);
  }

  public void whenToggledDo(Runnable runnable) {
    toggleListeners.addListener(runnable);
  }

  public void setText(String text) {
    this.text = text;
  }

  public void position(SpecialControl control) {
    Rectangle bounds = polygon.getBounds();
    control.setPosition((int) bounds.getMinX(), (int) bounds.getMaxY() + 10);
    control.setWidth((int) bounds.getWidth());
  }
}