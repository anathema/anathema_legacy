package net.sf.anathema.graph;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.anathema.graph.graph.IGraphType;
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

public class SugiyamaLayout {
  private final HierarchyBuilder hierarchyBuilder = new HierarchyBuilder();
  private final SubtreeSeparator subtreeSeparator = new SubtreeSeparator();
  private final List<IVertexOrdererFactory> factoryList = populateVertexOrdererFactoryList();
  private final ILayerer layerer = new LongestPathLayerer();

  public IProperHierarchicalGraph[] createProperHierarchicalGraphs(final IRegularNode[] acyclicGraph) {
    int deepestLayer = layerer.layerGraph(acyclicGraph);
    ISimpleNode[] hierarchicalGraph = hierarchyBuilder.removeLongEdges(acyclicGraph);
    IProperHierarchicalGraph[] separatedGraphs = subtreeSeparator.separateSubtrees(hierarchicalGraph, deepestLayer);
    final List<IProperHierarchicalGraph> orderedGraphs = new ArrayList<IProperHierarchicalGraph>(separatedGraphs.length);
    for (final IProperHierarchicalGraph graph : separatedGraphs) {
      graph.getType().accept(new IGraphTypeVisitor() {
        public void visitDirectedGraph(final IGraphType visitedType) {
          orderedGraphs.add(createOrderedGraph(graph));
        }

        public void visitInvertedTree(final IGraphType visitedType) {
          orderedGraphs.add(graph);
        }

        public void visitTree(final IGraphType visitedType) {
          orderedGraphs.add(graph);
        }

        public void visitSingle(final IGraphType visitedType) {
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

  private IProperHierarchicalGraph createOrderedGraph(final IProperHierarchicalGraph graph) {
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
      public IVertexOrderer createVertexOrderer(final IProperHierarchicalGraph graph) {
        return new SugiyamaVertexOrderer(graph);
      }
    });
    list.add(new IVertexOrdererFactory() {
      public IVertexOrderer createVertexOrderer(final IProperHierarchicalGraph graph) {
        return new UrsVertexOrderer(graph);
      }
    });
    list.add(new IVertexOrdererFactory() {
      public IVertexOrderer createVertexOrderer(final IProperHierarchicalGraph graph) {
        return new SandraVertexOrderer(graph);
      }
    });
    return list;
  }
}