package net.sf.anathema.graph;

import net.sf.anathema.graph.graph.IGraphTypeVisitor;
import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.hierarchy.HierarchyBuilder;
import net.sf.anathema.graph.hierarchy.SubtreeSeparator;
import net.sf.anathema.graph.layering.ILayerer;
import net.sf.anathema.graph.layering.LongestPathLayerer;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.ordering.IVertexOrderer;
import net.sf.anathema.graph.ordering.IVertexOrdererFactory;
import net.sf.anathema.graph.ordering.LeafStructureOptimizer;
import net.sf.anathema.graph.ordering.SandraVertexOrderer;
import net.sf.anathema.graph.ordering.SugiyamaVertexOrderer;
import net.sf.anathema.graph.ordering.UrsVertexOrderer;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SugiyamaLayout {
  private final HierarchyBuilder hierarchyBuilder = new HierarchyBuilder();
  private final SubtreeSeparator subtreeSeparator = new SubtreeSeparator();
  private final List<IVertexOrdererFactory> factoryList = populateVertexOrdererFactoryList();
  private final ILayerer layerer = new LongestPathLayerer();

  public IProperHierarchicalGraph[] createProperHierarchicalGraphs(IRegularNode[] acyclicGraph) {
    int deepestLayer = layerer.layerGraph(acyclicGraph);
    ISimpleNode[] hierarchicalGraph = hierarchyBuilder.removeLongEdges(acyclicGraph);
    IProperHierarchicalGraph[] separatedGraphs = subtreeSeparator.separateSubtrees(hierarchicalGraph, deepestLayer);
    final List<IProperHierarchicalGraph> orderedGraphs = new ArrayList<>(separatedGraphs.length);
    for (final IProperHierarchicalGraph graph : separatedGraphs) {
      graph.getType().accept(new IGraphTypeVisitor() {
        @Override
        public void visitDirectedGraph() {
          orderedGraphs.add(createOrderedGraph(graph));
        }

        @Override
        public void visitInvertedTree() {
          orderedGraphs.add(graph);
        }

        @Override
        public void visitTree() {
          orderedGraphs.add(graph);
        }

        @Override
        public void visitSingle() {
          orderedGraphs.add(graph);
        }
      });
    }
    optimizeLeavePositioning(orderedGraphs);
    return orderedGraphs.toArray(new IProperHierarchicalGraph[orderedGraphs.size()]);
  }

  private void optimizeLeavePositioning(List<IProperHierarchicalGraph> orderedGraphs) {
    LeafStructureOptimizer<ISimpleNode> leafStructureOptimizer = new LeafStructureOptimizer<>();
    for (IProperHierarchicalGraph graph : orderedGraphs) {
      for (int layerIndex = 1; layerIndex < graph.getDeepestLayer(); layerIndex++) {
        if (graph.containsRoot(layerIndex + 1)) {
          return;
        }
        ISimpleNode[] childrenLayer = graph.getNodesByLayer(layerIndex + 1);
        Set<ISimpleNode> newChildrenOrder = new LinkedHashSet<>();
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
    int bestCrossingCount = Integer.MAX_VALUE;
    for (IVertexOrdererFactory ordererFactory : factoryList) {
      IProperHierarchicalGraph clonedGraph = graph.clone();
      ordererFactory.createVertexOrderer(clonedGraph).processMultiLayerGraph();
      int crossingCount = clonedGraph.calculateTotalNumberOfCrossings();
      if (crossingCount == 0) {
        return clonedGraph;
      }
      if (bestGraph == null || crossingCount < bestCrossingCount) {
        bestGraph = clonedGraph;
        bestCrossingCount = crossingCount;
      }
    }
    return bestGraph;
  }

  private List<IVertexOrdererFactory> populateVertexOrdererFactoryList() {
    List<IVertexOrdererFactory> list = new ArrayList<>();
    list.add(new IVertexOrdererFactory() {
      @Override
      public IVertexOrderer createVertexOrderer(IProperHierarchicalGraph graph) {
        return new SugiyamaVertexOrderer(graph);
      }
    });
    list.add(new IVertexOrdererFactory() {
      @Override
      public IVertexOrderer createVertexOrderer(IProperHierarchicalGraph graph) {
        return new UrsVertexOrderer(graph);
      }
    });
    list.add(new IVertexOrdererFactory() {
      @Override
      public IVertexOrderer createVertexOrderer(IProperHierarchicalGraph graph) {
        return new SandraVertexOrderer(graph);
      }
    });
    return list;
  }
}