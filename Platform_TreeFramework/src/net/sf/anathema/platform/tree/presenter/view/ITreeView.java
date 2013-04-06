package net.sf.anathema.platform.tree.presenter.view;

import net.sf.anathema.platform.tree.util.RGBColor;

public interface ITreeView<G> {
  void addNodeInteractionListener(NodeInteractionListener listener);

  void setNodeBackgroundColor(String nodeId, RGBColor color);

  void setNodeAlpha(String nodeId, int alpha);

  void addCascadeLoadedListener(CascadeLoadedListener listener);

  void setCanvasBackground(RGBColor color);

  void loadCascade(G cascade, boolean resetView) throws CascadeLoadException;

  void addSpecialControl(String nodeId, SpecialControl control);

  void initNodeNames(NodeProperties properties);

  void clear();

  void registerSpecialType(Class contentClass, ContentFactory factory);
}