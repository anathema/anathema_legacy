package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class WheelScaler implements MouseWheelListener {
  private final PolygonPanel polygonPanel;

  public WheelScaler(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    polygonPanel.translate(e.getX(), e.getY());
    polygonPanel.scale(calculateScale(e));
    polygonPanel.translate(-e.getX(), -e.getY());
    polygonPanel.revalidate();
    polygonPanel.repaint();
  }

  private double calculateScale(MouseWheelEvent e) {
    int unitsToScroll = e.getUnitsToScroll();
    return 1 - unitsToScroll * 0.1;
  }
}