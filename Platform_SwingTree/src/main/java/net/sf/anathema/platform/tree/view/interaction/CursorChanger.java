package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.view.PolygonPanel;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class CursorChanger extends MouseAdapter implements MouseMotionListener {
  private final PolygonPanel polygonPanel;

  public CursorChanger(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    polygonPanel.changeCursor(new Coordinate(e.getPoint().x, e.getPoint().y));
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    polygonPanel.showMoveCursor();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    Point point = e.getPoint();
    polygonPanel.changeCursor(new Coordinate(point.x, point.y));
  }
}