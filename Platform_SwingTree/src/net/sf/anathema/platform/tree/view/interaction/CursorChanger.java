package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import static java.awt.Cursor.MOVE_CURSOR;

public class CursorChanger extends MouseAdapter implements MouseMotionListener {
  private final PolygonPanel polygonPanel;

  public CursorChanger(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    polygonPanel.changeCursor(e.getPoint());
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    polygonPanel.setCursor(Cursor.getPredefinedCursor(MOVE_CURSOR));
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    polygonPanel.changeCursor(e.getPoint());
  }
}