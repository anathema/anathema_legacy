package net.sf.anathema.character.main.magic.display.view.charmtree;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

public interface CharmTreeRenderer {
  void renderTree(boolean resetView, ITreePresentationProperties presentationProperties, IRegularNode[] nodes);

  void clearView();
}