package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;

public class DefaultScaler {
  private final PolygonPanel polygonPanel;

  public DefaultScaler(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
  }

  public void scale() {
    polygonPanel.scale(PolygonPanel.RECOMMENDED_DEFAULT_SCALE);
    polygonPanel.revalidate();
  }
}