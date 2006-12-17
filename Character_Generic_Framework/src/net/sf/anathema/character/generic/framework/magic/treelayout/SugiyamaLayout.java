package net.sf.anathema.character.generic.framework.magic.treelayout;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.character.generic.framework.magic.treelayout.graph.IProperHierarchicalGraph;
import net.sf.anathema.character.generic.framework.magic.treelayout.graph.type.IGraphType;
import net.sf.anathema.character.generic.framework.magic.treelayout.graph.type.IGraphTypeVisitor;
import net.sf.anathema.character.generic.framework.magic.treelayout.hierarchy.HierarchyBuilder;
import net.sf.anathema.character.generic.framework.magic.treelayout.hierarchy.SubtreeSeparator;
import net.sf.anathema.character.generic.framework.magic.treelayout.layering.ILayerer;
import net.sf.anathema.character.generic.framework.magic.treelayout.layering.LongestPathLayerer;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.IRegularNode;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.ISimpleNode;
import net.sf.anathema.character.generic.framework.magic.treelayout.ordering.IVertexOrderer;
import net.sf.anathema.character.generic.framework.magic.treelayout.ordering.IVertexOrdererFactory;
import net.sf.anathema.character.generic.framework.magic.treelayout.ordering.SandraVertexOrderer;
import net.sf.anathema.character.generic.framework.magic.treelayout.ordering.SugiyamaVertexOrderer;
import net.sf.anathema.character.generic.framework.magic.treelayout.ordering.UrsVertexOrderer;
import net.sf.anathema.character.generic.framework.magic.treelayout.ordering.leaf.LeafStructureOptimizer;

public class SugiyamaLayout {
  private final HierarchyBuilder hierarchyBuilder = new HierarchyBuilder();
  private final SubtreeSeparator subtreeSeparator = new SubtreeSeparator();
  private final List<IVertexOrdererFactory> factoryList = populateVertexOrdererFactoryList();
  private final ILayerer layerer = new LongestPathLayerer();

  public IProperHierarchicalGraph[] createProperHierarchicalGraphs(IRegularNode[] acyclicGraph) {
    int deepestLayer = layerer.layerGraph(acyclicGraph);
    ISimpleNode[] hierarchicalGraph = hierarchyBuilder.removeLongEdges(acyclicGraph);
    IProperHierarchicalGraph[] separatedGraphs = subtreeSeparator.separateSubtrees(hierarchicalGraph, deepestLayer);
    final List<IProperHierarchicalGraph> orderedGraphs = new ArrayList<IProperHierarchicalGraph>(separatedGraphs.length);
    for (final IProperHierarchicalGraph graph : separatedGraphs) {
      graph.getType().accept(new IGraphTypeVisitor() {
        public void visitDirectedGraph(IGraphType visitedType) {
          orderedGraphs.add(createOrderedGraph(graph));
        }

        public void visitInvertedTree(IGraphType visitedType) {
          orderedGraphs.add(graph);
        }

        public void visitTree(IGraphType visitedType) {
          orderedGraphs.add(graph);
        }

        public void visitSingle(IGraphType visitedType) {
          orderedGraphs.add(graph);
        }
      });
    }
    optimizeLeavePositioning(orderedGraphs);
    return orderedGraphs.toArray(new IProperHierarchicalGraph[orderedGraphs.size()]);
  }

  private void optimizeLeavePositioning(final List<IProperHierarchicalGraph> orderedGraphs) {
    LeafStructureOptimizer<ISimpleNode> leafStructureOptimizer = new LeafStructureOptimizer<ISimpleNode>();
    for (IProperHierarchicalGraph graph : orderedGraphs) {
      for (int layerIndex = 1; layerIndex < graph.getDeepestLayer(); layerIndex++) {
        if (graph.containsRoot(layerIndex + 1)) {
          return;
        }
        ISimpleNode[] childrenLayer = graph.getNodesByLayer(layerIndex + 1);
        Set<ISimpleNode> newChildrenOrder = new LinkedHashSet<ISimpleNode>();
        for (ISimpleNode parentNode : graph.getNodesByLayer(layerIndex)) {
          ISimpleNode[] nodeChildren = parentNode.getChildren(childrenLayer);
          List<ISimpleNode> optimizedNodes = leafStructureOptimizer.optimize(nodeChildren);
          newChildrenOrder.addAll(optimizedNodes);
        }
        ISimpleNode[] newOrder = newChildrenOrder.toArray(new ISimpleNode[newChildrenOrder.size()]);
        graph.setNewLayerOrder(layerIndex + 1, newOrder);
      }
    }
  }

  private IProperHierarchicalGraph createOrderedGraph(IProperHierarchicalGraph graph) {
    IProperHierarchicalGraph bestGraph = null;
    for (IVertexOrdererFactory ordererFactory : factoryList) {
      IProperHierarchicalGraph clonedGraph = graph.clone();
      ordererFactory.createVertexOrderer(clonedGraph).processMultiLayerGraph();
      int crossingCount = clonedGraph.calculateTotalNumberOfCrossings();
      if (crossingCount == 0) {
        return clonedGraph;
      }
      if (bestGraph == null || crossingCount < bestGraph.calculateTotalNumberOfCrossings()) {
        bestGraph = clonedGraph;
      }
    }
    return bestGraph;
  }

  private List<IVertexOrdererFactory> populateVertexOrdererFactoryList() {
    List<IVertexOrdererFactory> list = new ArrayList<IVertexOrdererFactory>();
    list.add(new IVertexOrdererFactory() {
      public IVertexOrderer createVertexOrderer(IProperHierarchicalGraph graph) {
        return new SugiyamaVertexOrderer(graph);
      }
    });
    list.add(new IVertexOrdererFactory() {
      public IVertexOrderer createVertexOrderer(IProperHierarchicalGraph graph) {
        return new UrsVertexOrderer(graph);
      }
    });
    list.add(new IVertexOrdererFactory() {
      public IVertexOrderer createVertexOrderer(IProperHierarchicalGraph graph) {
        return new SandraVertexOrderer(graph);
      }
    });
    return list;
  }
}