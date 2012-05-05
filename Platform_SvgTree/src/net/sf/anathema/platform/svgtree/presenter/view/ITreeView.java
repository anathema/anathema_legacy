package net.sf.anathema.platform.svgtree.presenter.view;

import net.sf.anathema.lib.gui.IDisposable;
import net.sf.anathema.lib.gui.IView;

import java.awt.Color;

public interface ITreeView<G> extends IView, IDisposable {
  void addNodeSelectionListener(CharmInteractionListener listener);

  void setNodeBackgroundColor(String nodeId, Color color);

  void setNodeAlpha(String nodeId, int alpha);

  void addCascadeLoadedListener(CascadeLoadedListener listener);

  void setCanvasBackground(Color color);

  void loadCascade(G cascade, boolean resetView) throws CascadeLoadException;
}
