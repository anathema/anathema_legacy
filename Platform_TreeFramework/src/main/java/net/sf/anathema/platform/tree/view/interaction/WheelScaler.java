package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.framework.ui.Coordinate;

public class WheelScaler implements MouseWheelClosure {
  private static final float PERCENTAGE_INCREMENT = 0.05f;  //    5%
  public static final double MIN_SCALE = 0.00001;
  private final PolygonPanel polygonPanel;

  public WheelScaler(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
  }

  @Override
  public void mouseWheelMoved(int wheelClicks, Coordinate coordinate) {
    polygonPanel.scaleToPoint(calculateScale(wheelClicks), coordinate);
    polygonPanel.refresh();
  }

  private double calculateScale(int wheelClicks) {
    int percentageTicks = (int) (1 / PERCENTAGE_INCREMENT);
    int unitsToScroll = Math.max(wheelClicks, -percentageTicks);
    return Math.max(MIN_SCALE, 1 - PERCENTAGE_INCREMENT * unitsToScroll);
  }
}