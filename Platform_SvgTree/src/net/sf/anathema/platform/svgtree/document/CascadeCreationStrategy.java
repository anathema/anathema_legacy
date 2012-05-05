package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;

import java.util.List;

public interface CascadeCreationStrategy<CASCADE> {
  List<IVisualizedGraph> visualizeGraphs(IRegularNode[] nodes, ITreePresentationProperties properties);

  CascadeBuilder<?, CASCADE> createCascadeBuilder(ITreePresentationProperties properties);
}
