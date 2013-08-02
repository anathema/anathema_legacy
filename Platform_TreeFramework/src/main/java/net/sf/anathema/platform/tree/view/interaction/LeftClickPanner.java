package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.framework.ui.Coordinate;

public class LeftClickPanner implements MouseMotionClosure, MousePressClosure {

  private final PolygonPanel panel;
  private Coordinate start = new Coordinate(0, 0);

  public LeftClickPanner(PolygonPanel panel) {
    this.panel = panel;
  }

  @Override
  public void mousePressed(Coordinate coordinate) {
    this.start = coordinate;
  }

  @Override
  public void mouseDragged(MouseButton button, Coordinate coordinate) {
    if (button != MouseButton.Primary) {
      return;
    }
    int deltaX = coordinate.x - this.start.x;
    int deltaY = coordinate.y - this.start.y;
    panel.translateRelativeToScale(deltaX, deltaY);
    panel.refresh();
    this.start = coordinate;
  }

  @Override
  public void mouseMoved(Coordinate coordinate) {
    //nothing to do
  }
}
