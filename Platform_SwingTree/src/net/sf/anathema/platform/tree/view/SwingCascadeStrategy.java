package net.sf.anathema.platform.tree.view;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.document.CascadeBuilder;
import net.sf.anathema.platform.svgtree.document.CascadeCreationStrategy;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;
import net.sf.anathema.platform.tree.view.container.Cascade;
import net.sf.anathema.platform.tree.view.container.ContainerCascade;

import java.util.List;

public class SwingCascadeStrategy implements CascadeCreationStrategy<Cascade> {
  @Override
  public List<IVisualizedGraph> visualizeGraphs(IRegularNode[] nodes, ITreePresentationProperties properties) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public CascadeBuilder<ContainerCascade, Cascade> createCascadeBuilder(ITreePresentationProperties properties) {
    return new SwingCascadeBuilder();
  }
}
