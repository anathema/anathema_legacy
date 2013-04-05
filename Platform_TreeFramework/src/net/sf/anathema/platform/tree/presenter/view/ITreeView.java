package net.sf.anathema.platform.tree.presenter.view;

import java.awt.Color;

public interface ITreeView<G> {
  void addNodeInteractionListener(NodeInteractionListener listener);

  void setNodeBackgroundColor(String nodeId, Color color);

  void setNodeAlpha(String nodeId, int alpha);

  void addCascadeLoadedListener(CascadeLoadedListener listener);

  void setCanvasBackground(Color color);

  void loadCascade(G cascade, boolean resetView) throws CascadeLoadException;

  void addSpecialControl(String nodeId, SpecialControl control);

  void initNodeNames(NodeProperties properties);

  void clear();

  void registerSpecialType(Class contentClass, ContentFactory factory);
}