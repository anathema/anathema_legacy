package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class WheelScaler implements MouseWheelListener {
  private static final float PERCENTAGE_INCREMENT = 0.05f;  //    5%
  public static final double MIN_SCALE = 0.00001;
  private final PolygonPanel polygonPanel;

  public WheelScaler(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    polygonPanel.scaleToPoint(calculateScale(e), e.getX(), e.getY());
    polygonPanel.refresh();
  }

  private double calculateScale(MouseWheelEvent event) {
    int wheelClicks = event.getWheelRotation();
    int percentageTicks = (int) (1 / PERCENTAGE_INCREMENT);
    int unitsToScroll = Math.max(wheelClicks, -percentageTicks);
    return Math.max(MIN_SCALE, 1 - PERCENTAGE_INCREMENT * unitsToScroll);
  }
}