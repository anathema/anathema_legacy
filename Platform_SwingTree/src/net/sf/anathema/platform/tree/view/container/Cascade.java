package net.sf.anathema.platform.tree.view.container;

import net.sf.anathema.platform.svgtree.presenter.view.NodeProperties;
import net.sf.anathema.platform.tree.view.PolygonPanel;
import net.sf.anathema.platform.tree.view.interaction.SpecialControl;

import java.awt.Color;

public interface Cascade {
  void colorNode(String nodeId, Color fillColor);

  void setNodeAlpha(String nodeId, int alpha);

  void addTo(PolygonPanel panel);

  void addToggleListener(NodeToggleListener listener);

  void removeToggleListener(NodeToggleListener listener);

  void initNodeNames(NodeProperties properties);

  void determinePositionFor(String nodeId, SpecialControl control);
}