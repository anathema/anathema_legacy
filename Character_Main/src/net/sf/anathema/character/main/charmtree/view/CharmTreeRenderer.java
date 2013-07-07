package net.sf.anathema.character.main.charmtree.view;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

public interface CharmTreeRenderer {
  void renderTree(boolean resetView, ITreePresentationProperties presentationProperties, IRegularNode[] nodes);

  void clearView();
}