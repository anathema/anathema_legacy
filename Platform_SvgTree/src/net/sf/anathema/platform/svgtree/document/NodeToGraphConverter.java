package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.graph.SugiyamaLayout;
import net.sf.anathema.graph.graph.IGraphTypeVisitor;
import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.document.visualizer.ICascadeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class NodeToGraphConverter {
  private final SugiyamaLayout layout = new SugiyamaLayout();
  private final List<IVisualizedGraph> visualizedGraphs = newArrayList();
  private final IRegularNode[] nodes;
  private final VisualizerFactory factory;

  public NodeToGraphConverter(IRegularNode[] nodes, VisualizerFactory visualizerFactory) {
    this.nodes = nodes;
    this.factory = visualizerFactory;
  }

  public List<IVisualizedGraph> visualizeGraphs() {
    IProperHierarchicalGraph[] graphs = layout.createProperHierarchicalGraphs(nodes);
    for (final IProperHierarchicalGraph graph : graphs) {
      graph.getType().accept(new IGraphTypeVisitor() {
        @Override
        public void visitDirectedGraph() {
          add(factory.createForBottomUp(graph));
        }

        @Override
        public void visitInvertedTree() {
          add(factory.createForInvertedTree(graph));
        }

        @Override
        public void visitTree() {
          add(factory.createForTree(graph));
        }

        @Override
        public void visitSingle() {
          add(factory.createForSingle(graph));
        }
      });
    }
    return visualizedGraphs;
  }

  private void add(ICascadeVisualizer visualizer) {
    visualizedGraphs.add(visualizer.buildTree());
  }
}