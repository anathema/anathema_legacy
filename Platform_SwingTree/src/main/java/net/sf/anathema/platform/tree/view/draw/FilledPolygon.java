package net.sf.anathema.platform.tree.view.draw;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.lib.gui.swing.ColorUtilities;
import net.sf.anathema.platform.tree.view.interaction.SpecialControl;
import org.jmock.example.announcer.Announcer;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

import static net.sf.anathema.lib.gui.swing.ColorUtilities.toAwtColor;

public class FilledPolygon implements InteractiveGraphicsElement, AgnosticPolygon {
  private final Announcer<Runnable> toggleListeners = Announcer.to(Runnable.class);
  private final Polygon polygon = new Polygon();
  private Color fill = ColorUtilities.getTransparency();
  private Color stroke = Color.BLACK;
  private final TextWriter textWriter = new TextWriter(polygon);

  @Override
  public void paint(Graphics2D graphics) {
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
    fillWithAwt(toAwtColor(fill));
  }

  public void setAlpha(int alpha) {
    Color original = fill;
    fillWithAwt(ColorUtilities.getTransparentColor(original, alpha));
    setStroke(ColorUtilities.getTransparentColor(stroke, alpha));
  }


  public void whenToggledDo(Runnable runnable) {
    toggleListeners.addListener(runnable);
  }

  public void setText(String text) {
    textWriter.setText(text);
  }

  public void position(SpecialControl control) {
    Rectangle bounds = polygon.getBounds();
    control.setPosition((int) bounds.getMinX(), (int) bounds.getMaxY() + 10);
    control.setWidth((int) bounds.getWidth());
  }

  public void setStroke(Color stroke) {
    this.stroke = stroke;
    textWriter.setStroke(stroke);
  }

  private void fillWithAwt(Color fill) {
    this.fill = fill;
  }
}