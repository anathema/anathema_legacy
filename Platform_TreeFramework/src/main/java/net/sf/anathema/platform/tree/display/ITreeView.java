package net.sf.anathema.platform.tree.display;

import net.sf.anathema.framework.ui.RGBColor;
import net.sf.anathema.platform.tree.view.container.Cascade;

public interface ITreeView {
  void addNodeInteractionListener(NodeInteractionListener listener);

  void setNodeBackgroundColor(String nodeId, RGBColor color);

  void setNodeAlpha(String nodeId, int alpha);

  void addCascadeLoadedListener(CascadeLoadedListener listener);

  void setCanvasBackground(RGBColor color);

  void loadCascade(Cascade cascade, boolean resetView) throws CascadeLoadException;

  void addSpecialControl(String nodeId, SpecialControl control);

  void initNodeNames(NodeProperties properties);

  void clear();

  void registerSpecialType(Class contentClass, ContentFactory factory);
}