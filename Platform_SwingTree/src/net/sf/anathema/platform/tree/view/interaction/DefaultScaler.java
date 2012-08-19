package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.view.PolygonPanel;

import static net.sf.anathema.platform.tree.view.PolygonPanel.RECOMMENDED_DEFAULT_SCALE;

public class DefaultScaler {
  private final PolygonPanel polygonPanel;

  public DefaultScaler(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
  }

  public void scale() {
    polygonPanel.scale(RECOMMENDED_DEFAULT_SCALE);
    polygonPanel.revalidate();
  }
}