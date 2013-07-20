package net.sf.anathema.platform.tree.view.interaction;

import net.sf.anathema.platform.tree.display.ToolTipProperties;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.NodeToggleListener;
import net.sf.anathema.platform.tree.view.draw.InteractiveGraphicsElement;

public class SetToolTipWithDetails implements Closure {
  private final Cascade cascade;
  private final ToolTipProperties properties;
  private final PolygonPanel polygonPanel;
  private final TooltipSetter setter = new TooltipSetter();

  public SetToolTipWithDetails(Cascade cascade, ToolTipProperties properties, PolygonPanel polygonPanel) {
    this.cascade = cascade;
    this.properties = properties;
    this.polygonPanel = polygonPanel;
  }

  @Override
  public void execute(InteractiveGraphicsElement polygon) {
    cascade.addToggleListener(setter);
    polygon.toggle();
    cascade.removeToggleListener(setter);
  }

  private class TooltipSetter implements NodeToggleListener {
    @Override
    public void toggled(String id) {
      String toolTip = properties.getToolTip(id);
      polygonPanel.setToolTipText(toolTip);
    }
  }
}
