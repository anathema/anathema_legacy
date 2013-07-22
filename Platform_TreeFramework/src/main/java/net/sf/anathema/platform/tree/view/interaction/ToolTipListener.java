package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.framework.ui.Coordinate;
import net.sf.anathema.platform.tree.display.ToolTipProperties;
import net.sf.anathema.platform.tree.view.container.Cascade;

public class ToolTipListener implements MouseMotionClosure {
  private final PolygonPanel polygonPanel;
  private final SetToolTipWithDetails setToolTip;

  public ToolTipListener(ToolTipProperties properties, PolygonPanel polygonPanel, Cascade cascade) {
    this.polygonPanel = polygonPanel;
    this.setToolTip = new SetToolTipWithDetails(cascade, properties, polygonPanel);
  }

  @Override
  public void mouseDragged(MouseButton button, Coordinate coordinate) {
    //nothing to do
  }

  @Override
  public void mouseMoved(Coordinate coordinate) {
    polygonPanel.onElementAtPoint(coordinate).perform(setToolTip).orFallBackTo(new NoTooltip());
  }

  private class NoTooltip implements Runnable {
    @Override
    public void run() {
      polygonPanel.setToolTipText(null);
    }
  }
}
