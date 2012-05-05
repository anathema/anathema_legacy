package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.graph.SugiyamaLayout;
import net.sf.anathema.graph.graph.IGraphTypeVisitor;
import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.document.visualizer.BottomUpGraphVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.ICascadeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;
import net.sf.anathema.platform.svgtree.document.visualizer.InvertedTreeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.SingleNodeVisualizer;
import net.sf.anathema.platform.svgtree.document.visualizer.TreeVisualizer;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class VisualizedGraphFactory {
  private final SugiyamaLayout layout = new SugiyamaLayout();
  private final List<IVisualizedGraph> visualizedGraphs = newArrayList();
  private final IRegularNode[] nodes;
  private final ITreePresentationProperties properties;

  public VisualizedGraphFactory(IRegularNode[] nodes, ITreePresentationProperties properties) {
    this.nodes = nodes;
    this.properties = properties;
  }

  public List<IVisualizedGraph> visualizeGraphs() {
    IProperHierarchicalGraph[] graphs = layout.createProperHierarchicalGraphs(nodes);
    for (final IProperHierarchicalGraph graph : graphs) {
      graph.getType().accept(new IGraphTypeVisitor() {
        @Override
        public void visitDirectedGraph() {
          add(new BottomUpGraphVisualizer(graph, properties));
        }

        @Override
        public void visitInvertedTree() {
          add(new InvertedTreeVisualizer(graph, properties));
        }

        @Override
        public void visitTree() {
          add(new TreeVisualizer(graph, properties));
        }

        @Override
        public void visitSingle() {
          add(new SingleNodeVisualizer(properties, graph));
        }
      });
    }
    return visualizedGraphs;
  }

  private void add(ICascadeVisualizer visualizer) {
    visualizedGraphs.add(visualizer.buildTree());
  }
}
