package net.sf.anathema.platform.tree.document;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.tree.document.visualizer.ITreePresentationProperties;

public interface CascadeFactory<G> {
  G createCascade(IRegularNode[] nodes, ITreePresentationProperties properties);
}