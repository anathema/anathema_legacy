package net.sf.anathema.graph.hierarchy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.graph.ProperHierarchicalGraph;
import net.sf.anathema.graph.graph.SingleNodeGraph;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.ListOrderedSet;
import net.sf.anathema.lib.collection.MultiEntryMap;

public class SubtreeSeparator {

  public IProperHierarchicalGraph[] separateSubtrees(ISimpleNode[] hierarchicalGraph, int deepestLayer) {
    MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors = createLeafGroups(deepestLayer, hierarchicalGraph);
    ISimpleNode[] roots = findRoots(hierarchicalGraph);
    List<ISimpleNode> handledRoots = new ArrayList<ISimpleNode>();
    List<IProperHierarchicalGraph> subtreeList = new ArrayList<IProperHierarchicalGraph>();
    List<IProperHierarchicalGraph> singleNodeList = new ArrayList<IProperHierarchicalGraph>();
    for (ISimpleNode root : roots) {
      if (root.isLeafNode()) {
        singleNodeList.add(new SingleNodeGraph(root));
        handledRoots.add(root);
      }
    }
    for (ISimpleNode root : roots) {
      if (handledRoots.contains(root)) {
        continue;
      }
      Set<ISimpleNode> peerRoots = new ListOrderedSet<ISimpleNode>();
      getPeerRoots(root, roots, handledRoots, peerRoots, leafNodesByAncestors);
      Set<ISimpleNode> subtreeNodes = new ListOrderedSet<ISimpleNode>();
      int deepestSubtreeLayer = 1;
      for (ISimpleNode peerRoot : peerRoots) {
        deepestSubtreeLayer = Math.max(deepestSubtreeLayer, addChildrenRecursively(subtreeNodes, peerRoot));
      }
      subtreeList.add(new ProperHierarchicalGraph(
          subtreeNodes.toArray(new ISimpleNode[subtreeNodes.size()]),
          deepestSubtreeLayer));
    }
    subtreeList.addAll(singleNodeList);
    return subtreeList.toArray(new IProperHierarchicalGraph[subtreeList.size()]);
  }

  private int addChildrenRecursively(Set<ISimpleNode> reorderedNodes, ISimpleNode root) {
    int deepestSubtreeLayer = root.getLayer();
    reorderedNodes.add(root);
    for (ISimpleNode child : root.getChildren()) {
      deepestSubtreeLayer = Math.max(deepestSubtreeLayer, addChildrenRecursively(reorderedNodes, child));
    }
    return deepestSubtreeLayer;
  }

  private void getPeerRoots(
      ISimpleNode root,
      ISimpleNode[] roots,
      List<ISimpleNode> handledRoots,
      Set<ISimpleNode> peerRoots,
      MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    peerRoots.add(root);
    handledRoots.add(root);
    List<ISimpleNode> rootLeaves = leafNodesByAncestors.get(root);
    for (ISimpleNode otherRoot : roots) {
      if (handledRoots.contains(otherRoot)) {
        continue;
      }
      List<ISimpleNode> otherRootLeaves = leafNodesByAncestors.get(otherRoot);
      if (!Collections.disjoint(rootLeaves, otherRootLeaves)) {
        getPeerRoots(otherRoot, roots, handledRoots, peerRoots, leafNodesByAncestors);
      }
    }
  }

  private ISimpleNode[] findRoots(ISimpleNode[] hierarchicalGraph) {
    List<ISimpleNode> roots = new ArrayList<ISimpleNode>();
    for (ISimpleNode node : hierarchicalGraph) {
      if (node.isRootNode()) {
        roots.add(node);
      }
    }
    return roots.toArray(new IRegularNode[roots.size()]);
  }

  private MultiEntryMap<ISimpleNode, ISimpleNode> createLeafGroups(int deepestLayer, ISimpleNode[] hierarchicalGraph) {
    Ensure.ensureArgumentArrayContentsNotNull(hierarchicalGraph);
    Map<Integer, List<ISimpleNode>> nodesByLayer = new HashMap<Integer, List<ISimpleNode>>();
    for (int index = 1; index <= deepestLayer; index++) {
      nodesByLayer.put(index, new ArrayList<ISimpleNode>());
    }
    for (ISimpleNode node : hierarchicalGraph) {
      List<ISimpleNode> layerNodes = nodesByLayer.get(node.getLayer());
      layerNodes.add(node);
    }
    MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors = new MultiEntryMap<ISimpleNode, ISimpleNode>();
    for (int layerIndex = deepestLayer - 1; layerIndex >= 0; layerIndex--) {
      createLeafGroups(nodesByLayer, layerIndex, leafNodesByAncestors);
    }
    return leafNodesByAncestors;
  }

  private void createLeafGroups(
      Map<Integer, List<ISimpleNode>> nodesByLayer,
      int layerIndex,
      MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors) {
    List<ISimpleNode> layerNodes = nodesByLayer.get(layerIndex + 1);
    for (ISimpleNode node : layerNodes) {
      if (!node.isLeafNode()) {
        continue;
      }
      List<ISimpleNode> ancestors = new ArrayList<ISimpleNode>();
      ancestors.add(node);
      leafNodesByAncestors.add(node, node);
      for (int upperLayerIndex = layerIndex - 1; upperLayerIndex >= 0; upperLayerIndex--) {
        List<ISimpleNode> ancestorLayerNodes = nodesByLayer.get(upperLayerIndex + 1);
        for (ISimpleNode possibleAncestor : ancestorLayerNodes) {
          if (ancestors.contains(possibleAncestor)) {
            continue;
          }
          List<ISimpleNode> childrenList = Arrays.asList(possibleAncestor.getChildren());
          if (!Collections.disjoint(childrenList, ancestors)) {
            ancestors.add(possibleAncestor);
            leafNodesByAncestors.add(possibleAncestor, node);
          }
        }
      }
    }
  }
}