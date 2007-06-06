package net.sf.anathema.platform.svgtree.graph.layering;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.platform.svgtree.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.graph.nodes.ISimpleNode;

public class TwoCommonParentsOptimizer {

  public static boolean moveDownOvercrossingTwoTupels(
      MultiEntryMap<Integer, IRegularNode> nodesByLayer,
      int layerToOptimize,
      int deepestLayer) {
    List<IRegularNode> dualParentNodes = getDualParentNodes(nodesByLayer, layerToOptimize);
    if (dualParentNodes.size() < 2) {
      return false;
    }
    boolean increaseDeepestLayer = false;
    Set<IRegularNode> doubleParentNodeSet = new HashSet<IRegularNode>(dualParentNodes);
    for (IRegularNode node : dualParentNodes) {
      if (!doubleParentNodeSet.contains(node)) {
        continue;
      }
      doubleParentNodeSet.remove(node);
      ISimpleNode[] nodeParents = node.getParents();
      for (IRegularNode otherNode : new HashSet<IRegularNode>(doubleParentNodeSet)) {
        if (containsAll(nodeParents, otherNode.getParents())) {
          // TODO vom 26.10.2005: Kindreicher Knoten hinab
          doubleParentNodeSet.remove(otherNode);
          int targetLayer = node.getLayer() + 1;
          node.setLayer(targetLayer);
          increaseDeepestLayer |= targetLayer > deepestLayer;
          break;
        }
      }
    }
    return increaseDeepestLayer;
  }

  private static boolean containsAll(ISimpleNode[] nodeParents, ISimpleNode[] otherNodeParents) {
    for (ISimpleNode node : otherNodeParents) {
      if (!ArrayUtilities.contains(nodeParents, node)) {
        return false;
      }
    }
    return true;
  }

  private static List<IRegularNode> getDualParentNodes(
      MultiEntryMap<Integer, IRegularNode> nodesByLayer,
      int layerToOptimize) {
    List<IRegularNode> dualParentNodes = new ArrayList<IRegularNode>();
    for (IRegularNode node : nodesByLayer.get(layerToOptimize)) {
      if (node.getParents().length == 2) {
        dualParentNodes.add(node);
      }
    }
    return dualParentNodes;
  }
}
