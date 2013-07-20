package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.view.PolygonPanel;

public class RightClickCenterer implements MouseClickClosure {
  private final PolygonPanel polygonPanel;

  public RightClickCenterer(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
  }

  @Override
  public void mouseClicked(MouseButton button, MetaKey key, Coordinate coordinate, int clickCount) {
    if (button != MouseButton.Right) {
      return;
    }
    if (clickCount == 1) {
      polygonPanel.centerOn(coordinate);
    }
  }
}