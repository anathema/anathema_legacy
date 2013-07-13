package net.sf.anathema.platform.tree.display;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.tree.document.visualizer.TreePresentationProperties;

public interface TreeRenderer {
  void renderTree(boolean resetView, TreePresentationProperties presentationProperties, IRegularNode[] nodes);

  void clearView();
}