package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.view.draw.GraphicsElement;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;
import net.sf.anathema.platform.tree.view.interaction.Closure;
import net.sf.anathema.platform.tree.view.interaction.ElementContainer;
import net.sf.anathema.platform.tree.view.interaction.Executor;
import net.sf.anathema.platform.tree.view.interaction.SpecialControl;

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
import static java.awt.Cursor.getPredefinedCursor;
import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class PolygonPanel extends JPanel {
  public static final double RECOMMENDED_DEFAULT_SCALE = 0.75d;
  private static final double MAX_ZOOM_OUT_DETERMINANT = 0.09d; //30%
  private static final double MAX_ZOOM_IN_DETERMINANT = 2.25d; //150%
  private AffineTransform transform = new AffineTransform();
  private ElementContainer container = new ElementContainer();
  private final List<SpecialControl> specialControls = new ArrayList<SpecialControl>();

  public PolygonPanel() {
    setLayout(null);
    setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics = (Graphics2D) g.create();
    graphics.transform(transform);
    graphics.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
    for (GraphicsElement element : container) {
      element.paint(graphics);
    }
  }

  @Override
  public void revalidate() {
    if (specialControls != null) {
      for (SpecialControl specialControl : specialControls) {
        specialControl.transformThrough(transform);
      }
    }
    super.revalidate();
  }

  public void add(SpecialControl control) {
    specialControls.add(control);
    control.transformOriginalCoordinates(transform);
    control.addTo(this);
  }

  public void add(InteractiveGraphicsElement element) {
    container.add(element);
  }

  public void add(GraphicsElement element) {
    container.add(element);
  }

  public void scale(double scale) {
    AffineTransform clone = (AffineTransform) transform.clone();
    clone.scale(scale, scale);
    double determinant = clone.getDeterminant();
    if (MAX_ZOOM_OUT_DETERMINANT <= determinant && determinant <= MAX_ZOOM_IN_DETERMINANT) {
      transform.scale(scale, scale);
    }
  }

  public void translate(int x, int y) {
    transform.translate(x, y);
  }

  public void resetTransformation() {
    transform.setToIdentity();
    repaint();
  }

  public void changeCursor(Point point) {
    Point2D elementPoint = transformClickPointToObjectCoordinates(point);
    container.onElementAtPoint(elementPoint).perform(new SetHandCursor()).orFallBackTo(new SetDefaultCursor());
  }

  public void clear() {
    container.clear();
    for (SpecialControl specialControl : specialControls) {
      specialControl.remove(this);
    }
    specialControls.clear();
    repaint();
  }

  public Executor onElementAtPoint(Point point) {
    Point2D elementPoint = transformClickPointToObjectCoordinates(point);
    return container.onElementAtPoint(elementPoint);
  }

  private Point2D transformClickPointToObjectCoordinates(Point p) {
    try {
      return transform.inverseTransform(p, p);
    } catch (NoninvertibleTransformException e1) {
      throw new RuntimeException(e1);
    }
  }

  public void centerOn(int x, int y) {
    int xCenter = getWidth() / 2;
    int yCenter = getHeight() / 2;
    int newCenterX = xCenter - x;
    int newCenterY = yCenter - y;
    AffineTransform newCenter = AffineTransform.getTranslateInstance(newCenterX, newCenterY);
    transform.preConcatenate(newCenter);
    repaint();
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
}