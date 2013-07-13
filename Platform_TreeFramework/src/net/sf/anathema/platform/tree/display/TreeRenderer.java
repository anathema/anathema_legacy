package net.sf.anathema.platform.tree.display;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

public interface TreeRenderer {
  void renderTree(boolean resetView, ITreePresentationProperties presentationProperties, IRegularNode[] nodes);

  void clearView();
}