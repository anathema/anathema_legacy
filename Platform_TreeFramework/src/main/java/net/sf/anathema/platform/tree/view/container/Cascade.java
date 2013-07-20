package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.display.NodeProperties;
import net.sf.anathema.platform.tree.display.draw.ShapeWithPosition;
import net.sf.anathema.platform.tree.view.interaction.PolygonPanel;

public interface Cascade {
  void colorNode(String nodeId, RGBColor fillColor);

  void setNodeAlpha(String nodeId, int alpha);

  void addTo(PolygonPanel panel);

  void addToggleListener(NodeToggleListener listener);

  void removeToggleListener(NodeToggleListener listener);

  void initNodeNames(NodeProperties properties);

  void determinePositionFor(String nodeId, ShapeWithPosition control);
}