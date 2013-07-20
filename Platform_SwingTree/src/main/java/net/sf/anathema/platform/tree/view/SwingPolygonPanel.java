package net.sf.anathema.platform.tree.view;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.transform.AgnosticTransform;
import net.sf.anathema.platform.tree.display.transform.CenterOn;
import net.sf.anathema.platform.tree.display.transform.PreConcatenate;
import net.sf.anathema.platform.tree.display.transform.Scale;
import net.sf.anathema.platform.tree.display.transform.Translation;
import net.sf.anathema.platform.tree.swing.SwingGraphicsCanvas;
import net.sf.anathema.platform.tree.view.draw.Canvas;
import net.sf.anathema.platform.tree.view.draw.GraphicsElement;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;
import net.sf.anathema.platform.tree.view.interaction.ButtonSpecialControl;
import net.sf.anathema.platform.tree.view.interaction.Closure;
import net.sf.anathema.platform.tree.view.interaction.ElementContainer;
import net.sf.anathema.platform.tree.view.interaction.Executor;
import net.sf.anathema.platform.tree.view.interaction.SpecialControlTrigger;
import net.sf.anathema.platform.tree.view.transform.SwingTransformer;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Cursor.HAND_CURSOR;
import static java.awt.Cursor.MOVE_CURSOR;
import static java.awt.Cursor.getPredefinedCursor;
import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.KEY_RENDERING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;
import static java.awt.RenderingHints.VALUE_RENDER_QUALITY;
import static net.sf.anathema.lib.gui.swing.ColorUtilities.toAwtColor;

public class SwingPolygonPanel extends JPanel implements PolygonPanel {
  private static final double MAX_ZOOM_OUT_SCALE = 0.3d; //30%
  private static final double MAX_ZOOM_IN_SCALE = 1.5d; //150%
  private AgnosticTransform transform = new AgnosticTransform();
  private ElementContainer container = new ElementContainer();
  private final List<ButtonSpecialControl> specialControls = new ArrayList<>();

  public SwingPolygonPanel() {
    setLayout(null);
    setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics = (Graphics2D) g.create();
    AffineTransform affineTransform = SwingTransformer.convert(transform);
    graphics.transform(affineTransform);
    graphics.setRenderingHint(KEY_RENDERING, VALUE_RENDER_QUALITY);
    graphics.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
    Canvas canvas = new SwingGraphicsCanvas(graphics);
    for (GraphicsElement element : container) {
      element.paint(canvas);
    }
  }

  @Override
  public void refresh() {
    this.revalidate();
    super.repaint();
  }

  @SuppressWarnings("ConstantConditions") //Is null during superclass initialization
  @Override
  public void revalidate() {
    if (specialControls != null) {
      for (SpecialControlTrigger specialControlTrigger : specialControls) {
        specialControlTrigger.transformThrough(transform);
      }
    }
    super.revalidate();
  }

  @Override
  public SpecialControlTrigger addSpecialControl() {
    ButtonSpecialControl specialControl = new ButtonSpecialControl();
    specialControls.add(specialControl);
    specialControl.transformOriginalCoordinates(transform);
    specialControl.addTo(this);
    return specialControl;
  }

  @Override
  public void add(InteractiveGraphicsElement element) {
    container.add(element);
  }

  @Override
  public void add(GraphicsElement element) {
    container.add(element);
  }

  @Override
  public void scale(double scale) {
    AgnosticTransform scaleTransform = new AgnosticTransform();
    scaleTransform.add(new Scale(scale));
    executeScaleIfBoundsAreNotBroken(scaleTransform);
  }

  @Override
  public void scaleToPoint(double scale, int x, int y) {
    AgnosticTransform scaleTransform = new AgnosticTransform();
    scaleTransform.add(new Translation(x, y));
    scaleTransform.add(new Scale(scale));
    scaleTransform.add(new Translation(-x, -y));
    executeScaleIfBoundsAreNotBroken(scaleTransform);
  }

  @Override
  public void translate(int x, int y) {
    transform.add(new Translation(x, y));
  }

  @Override
  public void translateRelativeToScale(int x, int y) {
    double scale = Math.sqrt(getScaleSquared(transform));
    transform.add(new Translation(x / scale, y / scale));
  }

  @Override
  public void resetTransformation() {
    transform.setToIdentity();
    repaint();
  }

  @Override
  public void changeCursor(Coordinate screenCoordinates) {
    Coordinate coordinate = getObjectCoordinatesFrom(screenCoordinates);
    container.onElementAtPoint(coordinate).perform(new SetHandCursor()).orFallBackTo(new SetDefaultCursor());
  }

  @Override
  public void clear() {
    container.clear();
    for (ButtonSpecialControl specialControl : specialControls) {
      specialControl.remove();
    }
    specialControls.clear();
    repaint();
  }

  @Override
  public Executor onElementAtPoint(Coordinate screenCoordinates) {
    Coordinate elementPoint = getObjectCoordinatesFrom(screenCoordinates);
    return container.onElementAtPoint(elementPoint);
  }

  private Coordinate getObjectCoordinatesFrom(Coordinate point) {
    Point2D elementPoint = transformClickPointToObjectCoordinates(point);
    return new Coordinate(elementPoint.getX(), elementPoint.getY());
  }

  private Point2D transformClickPointToObjectCoordinates(Coordinate coordinate) {
    try {
      Point point = SwingTransformer.convert(coordinate);
      return SwingTransformer.convert(transform).inverseTransform(point, point);
    } catch (NoninvertibleTransformException e1) {
      throw new RuntimeException(e1);
    }
  }

  @Override
  public void centerOn(int x, int y) {
    int xCenter = getWidth() / 2;
    int yCenter = getHeight() / 2;
    int newCenterX = xCenter - x;
    int newCenterY = yCenter - y;
    transform.add(new CenterOn(newCenterX, newCenterY));
    repaint();
  }

  @Override
  public void setBackground(RGBColor color) {
    setBackground(toAwtColor(color));
  }

  @Override
  public void showMoveCursor() {
    setCursor(Cursor.getPredefinedCursor(MOVE_CURSOR));
  }

  private void executeScaleIfBoundsAreNotBroken(AgnosticTransform scaleInstance) {
    AgnosticTransform copy = transform.createCopy();
    copy.add(new PreConcatenate(scaleInstance));
    double determinant = getScaleSquared(copy);
    double maxZoomOutDeterminant = MAX_ZOOM_OUT_SCALE * MAX_ZOOM_OUT_SCALE;
    double maxZoomInDeterminant = MAX_ZOOM_IN_SCALE * MAX_ZOOM_IN_SCALE;
    if (maxZoomOutDeterminant <= determinant && determinant <= maxZoomInDeterminant) {
      transform.add(new PreConcatenate(scaleInstance));
    }
  }

  public JComponent getComponent() {
    return this;
  }

  private class SetDefaultCursor implements Runnable {

    @Override
    public void run() {
      setCursor(Cursor.getDefaultCursor());
    }
  }

  private class SetHandCursor implements Closure {

    @Override
    public void execute(InteractiveGraphicsElement polygon) {
      setCursor(getPredefinedCursor(HAND_CURSOR));
    }
  }

  private double getScaleSquared(AgnosticTransform transform) {
    return SwingTransformer.convert(transform).getDeterminant();
  }
}