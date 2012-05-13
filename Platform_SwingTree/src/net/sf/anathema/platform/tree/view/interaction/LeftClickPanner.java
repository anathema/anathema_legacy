package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;

import javax.swing.SwingUtilities;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LeftClickPanner extends MouseAdapter {

  private final PolygonPanel panel;
  private Point start = new Point(0, 0);

  public LeftClickPanner(PolygonPanel panel) {
    this.panel = panel;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    this.start = e.getPoint();
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    boolean leftMouseButton = SwingUtilities.isLeftMouseButton(e);
    if (!leftMouseButton) {
      return;
    }
    Point dragPoint = e.getPoint();
    int deltaX = dragPoint.x - this.start.x;
    int deltaY = dragPoint.y - this.start.y;
    panel.translate(deltaX, deltaY);
    panel.revalidate();
    panel.repaint();
    this.start = dragPoint;
  }
}
