package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.framework.ui.Coordinate;

public class RightClickResetter implements MouseClickClosure {
  private final PolygonPanel polygonPanel;

  public RightClickResetter(PolygonPanel polygonPanel) {
    this.polygonPanel = polygonPanel;
  }

  @Override
  public void mouseClicked(MouseButton button, MetaKey key, Coordinate coordinate, int clickCount) {
    if (button != MouseButton.Right){
      return;
    }
    if (clickCount == 2) {
      polygonPanel.resetTransformation();
      new DefaultScaler(polygonPanel).scale();
    }
  }
}