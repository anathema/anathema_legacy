package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.tree.view.draw.FilledPolygon;
import net.sf.anathema.platform.tree.view.draw.FlexibleArrow;
import net.sf.anathema.platform.tree.view.draw.GraphicsElement;
import net.sf.anathema.platform.tree.view.interaction.Closure;
import net.sf.anathema.platform.tree.view.interaction.NodeContainer;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.awt.Cursor.HAND_CURSOR;
import static java.awt.Cursor.getPredefinedCursor;
import static java.awt.RenderingHints.KEY_ANTIALIASING;
import static java.awt.RenderingHints.VALUE_ANTIALIAS_ON;

public class PolygonPanel extends JPanel {
  private AffineTransform transform = new AffineTransform();
  private NodeContainer container = new NodeContainer();
  private List<GraphicsElement> elements = newArrayList();

  public PolygonPanel() {
    setBackground(Color.WHITE);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D graphics = (Graphics2D) g;
    graphics.transform(transform);
    graphics.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
    for (GraphicsElement polygon : elements) {
      polygon.paint(graphics);
    }
  }

  public void addPolygon(FilledPolygon polygon) {
    container.add(polygon);
    elements.add(polygon);
  }

  public void addArrow(FlexibleArrow flexibleArrow) {
    elements.add(flexibleArrow);
  }

  public void scale(int unitsToScroll) {
    double delta = 1 - unitsToScroll * 0.1;
    transform.scale(delta, delta);
    repaint();
  }

  public void resetTransformation() {
    transform.setToIdentity();
    repaint();
  }

  public void changeCursor(Point point) {
    Point2D polygonPoint = transformClickPointToObjectCoordinates(point);
    container.onPolygonAtPoint(polygonPoint).perform(new SetHandCursor()).orFallBackTo(new SetDefaultCursor());
  }

  public void togglePolygonAtPoint(Point point) {
    Point2D polygonPoint = transformClickPointToObjectCoordinates(point);
    container.onPolygonAtPoint(polygonPoint).perform(new ToggleAndRepaint());
  }

  private Point2D transformClickPointToObjectCoordinates(Point p) {
    try {
      return transform.inverseTransform(p, p);
    } catch (NoninvertibleTransformException e1) {
      throw new RuntimeException(e1);
    }
  }

  private class ToggleAndRepaint implements Closure {
    @Override
    public void execute(FilledPolygon polygon) {
      polygon.toggle();
      repaint();
    }
  }

  private class SetDefaultCursor implements Runnable {
    @Override
    public void run() {
      setCursor(Cursor.getDefaultCursor());
    }
  }

  private class SetHandCursor implements Closure {
    @Override
    public void execute(FilledPolygon polygon) {
      setCursor(getPredefinedCursor(HAND_CURSOR));
    }
  }
}