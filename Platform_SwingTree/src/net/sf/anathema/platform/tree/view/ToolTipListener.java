package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.svgtree.presenter.view.ToolTipProperties;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;
import net.sf.anathema.platform.tree.view.interaction.Closure;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ToolTipListener extends MouseMotionAdapter {
  private final ToolTipProperties properties;
  private final PolygonPanel polygonPanel;
  private final Cascade cascade;

  public ToolTipListener(ToolTipProperties properties, PolygonPanel polygonPanel, Cascade cascade) {
    this.properties = properties;
    this.polygonPanel = polygonPanel;
    this.cascade = cascade;
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    polygonPanel.onElementAtPoint(e.getPoint()).perform(new SetToolTipWithDetails()).orFallBackTo(new NoTooltip());
  }

  private class SetToolTipWithDetails implements Closure {
    @Override
    public void execute(InteractiveGraphicsElement polygon) {
      String id = cascade.getIdForElement(polygon);
      String toolTip = properties.getToolTip(id);
      polygonPanel.setToolTipText(toolTip);
    }
  }

  private class NoTooltip implements Runnable {
    @Override
    public void run() {
      polygonPanel.setToolTipText(null);
    }
  }
}
