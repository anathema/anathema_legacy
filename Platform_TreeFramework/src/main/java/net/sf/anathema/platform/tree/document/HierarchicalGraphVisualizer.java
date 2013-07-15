package net.sf.anathema.platform.tree.document;

import net.sf.anathema.graph.graph.IGraphTypeVisitor;
import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.platform.tree.document.components.ILayer;
import net.sf.anathema.platform.tree.document.visualizer.ICascadeVisualizer;
import net.sf.anathema.platform.tree.document.visualizer.IVisualizedGraph;
import net.sf.anathema.platform.tree.document.visualizer.VisualizedGraphFactory;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class HierarchicalGraphVisualizer {
  private final List<IVisualizedGraph> visualizedGraphs = newArrayList();
  private final PositionerFactory positionerFactory;
  private final VisualizedGraphFactory factoryForVisualizedGraphs;

  public HierarchicalGraphVisualizer(PositionerFactory positionerFactory,
                                     VisualizedGraphFactory factoryForVisualizedGraphs) {
    this.positionerFactory = positionerFactory;
    this.factoryForVisualizedGraphs = factoryForVisualizedGraphs;
  }

  public List<IVisualizedGraph> visualizeGraphs(IProperHierarchicalGraph[] graphs) {
    for (final IProperHierarchicalGraph graph : graphs) {
      graph.getType().accept(new IGraphTypeVisitor() {
        @Override
        public void visitDirectedGraph() {
          add(positionerFactory.createForBottomUp(graph));
        }

        @Override
        public void visitInvertedTree() {
          add(positionerFactory.createForInvertedTree(graph));
        }

        @Override
        public void visitTree() {
          add(positionerFactory.createForTree(graph));
        }

        @Override
        public void visitSingle() {
          add(positionerFactory.createForSingle(graph));
        }
      });
    }
    return visualizedGraphs;
  }

  private void add(ICascadeVisualizer visualizer) {
    ILayer[] layers = visualizer.buildTree();
    IVisualizedGraph graph = factoryForVisualizedGraphs.create(layers);
    visualizedGraphs.add(graph);
  }
}