package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class WheelScaler implements MouseWheelListener {
  private static final float PERCENTAGE_INCREMENT = 0.05f;  //    5%
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

  private double calculateScale(MouseWheelEvent event) {
    int wheelClicks = event.getWheelRotation();
    int percentageTicks = (int) (1 / PERCENTAGE_INCREMENT);
    int unitsToScroll = Math.max(wheelClicks, -percentageTicks);
    return Math.max(0.00001, 1 - PERCENTAGE_INCREMENT * unitsToScroll);
  }
}