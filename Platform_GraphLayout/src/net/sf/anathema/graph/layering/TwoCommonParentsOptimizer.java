package net.sf.anathema.graph.layering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.graph.nodes.ISimpleNode;

public class TwoCommonParentsOptimizer {

  public static boolean moveDownOvercrossingTwoTupels(List<IRegularNode> nodes, int deepestLayer) {
    List<IRegularNode> dualParentNodes = getDualParentNodes(nodes);
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
      for (IRegularNode otherNode : new HashSet<IRegularNode>(doubleParentNodeSet)) {
        if (shareAllParents(node, otherNode) && noCommonSiblings(node, otherNode)) {
          // TODO (26.10.2005): Kindreicher Knoten hinab
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

  private static boolean noCommonSiblings(IRegularNode node, IRegularNode otherNode) {
    Set<ISimpleNode> children = new HashSet<ISimpleNode>();
    for (ISimpleNode parent : node.getParents()) {
      Collections.addAll(children, parent.getChildren());
    }
    for (ISimpleNode parent : otherNode.getParents()) {
      Collections.addAll(children, parent.getChildren());
    }
    children.remove(node);
    children.remove(otherNode);
    if (children.isEmpty()) {
      return true;
    }
    for (ISimpleNode child : children) {
      if (shareAllParents(node, child) && shareAllParents(child, otherNode)) {
        return false;
      }
    }
    return true;
  }

  private static boolean shareAllParents(ISimpleNode node, ISimpleNode otherNode) {
    for (ISimpleNode parent : node.getParents()) {
      if (!ArrayUtilities.contains(otherNode.getParents(), parent)) {
        return false;
      }
    }
    return true;
  }

  private static List<IRegularNode> getDualParentNodes(List<IRegularNode> nodes) {
    List<IRegularNode> dualParentNodes = new ArrayList<IRegularNode>();
    for (IRegularNode node : nodes) {
      if (node.getParents().length == 2) {
        dualParentNodes.add(node);
      }
    }
    return dualParentNodes;
  }
}
