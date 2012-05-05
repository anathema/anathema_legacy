package net.sf.anathema.platform.tree.view;

import net.sf.anathema.platform.svgtree.presenter.view.NodeInteractionListener;

import java.awt.Color;

public interface Cascade {
  void colorNode(String nodeId, Color fillColor);

  void setNodeAlpha(String nodeId, int alpha);

  void addTo(PolygonPanel panel);

  void addInteractionListener(NodeInteractionListener listener);

  void removeInteractionListener(NodeInteractionListener listener);
}
