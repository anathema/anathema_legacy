package net.sf.anathema.character.generic.framework.magic.treelayout.layering;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.IRegularNode;
import net.sf.anathema.lib.collection.MultiEntryMap;


public class TwoCommonParentsOptimizer {

  public static boolean moveDownOvercrossingTwoTupels(
      MultiEntryMap<Integer, IRegularNode> nodesByLayer,
      int layerIndex,
      int deepestLayer) {
    Map<IRegularNode, List<IRegularNode>> parentsByNode = new HashMap<IRegularNode, List<IRegularNode>>();
    for (IRegularNode layerNode : nodesByLayer.get(layerIndex)) {
      List<IRegularNode> parentNodes = getParents(layerNode, nodesByLayer);
      if (parentNodes.size() == 2) {
        parentsByNode.put(layerNode, parentNodes);
      }
    }
    if (parentsByNode.size() < 2) {
      return false;
    }
    boolean increaseDeepestLayer = false;
    Set<IRegularNode> doubleParentNodeSet = new HashSet<IRegularNode>(parentsByNode.keySet());
    for (IRegularNode doubleParentNode : parentsByNode.keySet()) {
      if (!doubleParentNodeSet.contains(doubleParentNode)) {
        continue;
      }
      IRegularNode nodeToBeMovedDown = null;
      doubleParentNodeSet.remove(doubleParentNode);
      Set<IRegularNode> doubleParentNodeSetCopy = new HashSet<IRegularNode>(doubleParentNodeSet);
      List<IRegularNode> doubleParentNodeParents = parentsByNode.get(doubleParentNode);
      for (IRegularNode remainingNode : doubleParentNodeSetCopy) {
        List<IRegularNode> remainingNodeParents = parentsByNode.get(remainingNode);
        if (doubleParentNodeParents.containsAll(remainingNodeParents)) {
          // todo vom (26.10.2005) (sieroux): Kindreicher Knoten hinab
          nodeToBeMovedDown = doubleParentNode;
          doubleParentNodeSet.remove(remainingNode);
        }
      }
      if (nodeToBeMovedDown != null) {
        int targetLayer = nodeToBeMovedDown.getLayer() + 1;
        nodeToBeMovedDown.setLayer(targetLayer);
        increaseDeepestLayer = increaseDeepestLayer || targetLayer > deepestLayer;
      }
    }
    return increaseDeepestLayer;
  }

  private static List<IRegularNode> getParents(IRegularNode node, MultiEntryMap<Integer, IRegularNode> nodesByLayer) {
    List<IRegularNode> parentNodes = new ArrayList<IRegularNode>();
    for (int index = 1; index < node.getLayer(); index++) {
      for (IRegularNode potentialParent : nodesByLayer.get(index)) {
        if (ArrayUtilities.contains(potentialParent.getChildren(), node)) {
          parentNodes.add(potentialParent);
        }
      }
    }
    return parentNodes;
  }
}