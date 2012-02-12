package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

public interface CharmTreeRenderer {
  void renderTree(boolean resetView, ITreePresentationProperties presentationProperties, IRegularNode[] nodes);

  void clearView();
}
