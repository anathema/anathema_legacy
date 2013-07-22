package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.NodeInteractionListener;
import net.sf.anathema.platform.tree.view.container.Cascade;

public class LeftClickSelector implements MouseClickClosure {
  private final Cascade cascade;
  private final PolygonPanel polygonPanel;
  private final NodeInteractionListener interactionListener;

  public LeftClickSelector(Cascade cascade, PolygonPanel panel, NodeInteractionListener interactionListener) {
    this.cascade = cascade;
    this.polygonPanel = panel;
    this.interactionListener = interactionListener;
  }

  @Override
  public void mouseClicked(MouseButton button, MetaKey key, Coordinate coordinate, int clickCount) {
    if (button != MouseButton.Left || key == MetaKey.CTRL) {
      return;
    }
    polygonPanel.onElementAtPoint(coordinate).perform(new NodeSelectionTrigger(cascade, interactionListener));
    polygonPanel.refresh();
  }
}