package net.sf.anathema.platform.svgtree.document.components;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;

import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.ListOrderedSet;

public class HorizontalMetaNode extends AbstractVisualizableNode {

  private final List<ISimpleNode> contentNodes = new ArrayList<ISimpleNode>();
  private final Dimension gapDimension;
  private final Map<ISimpleNode, IVisualizableNode> innerVisualizableNodesByContent = new HashMap<ISimpleNode, IVisualizableNode>();

  public HorizontalMetaNode(
      final Map<ISimpleNode, IVisualizableNode> map,
      final Dimension nodeDimension,
      final Dimension gapDimension) {
    super(map, nodeDimension);
    this.gapDimension = gapDimension;
  }

  public void addInnerNode(final ISimpleNode node, final IVisualizableNode visualizableNode) {
    contentNodes.add(node);
    innerVisualizableNodesByContent.put(node, visualizableNode);
  }

  public IVisualizableNode[] getChildren() {
    Set<IVisualizableNode> children = new ListOrderedSet<IVisualizableNode>();
    for (ISimpleNode node : contentNodes) {
      for (ISimpleNode child : node.getChildren()) {
        children.add(getContentNodeMap().get(child));
      }
    }
    return children.toArray(new IVisualizableNode[children.size()]);
  }

  public IVisualizableNode[] getParents() {
    Set<IVisualizableNode> parents = new ListOrderedSet<IVisualizableNode>();
    for (ISimpleNode node : contentNodes) {
      for (ISimpleNode parent : node.getParents()) {
        parents.add(getContentNodeMap().get(parent));
      }
    }
    return parents.toArray(new IVisualizableNode[parents.size()]);
  }

  public int getWidth() {
    int width = 0;
    for (ISimpleNode node : contentNodes) {
      width += innerVisualizableNodesByContent.get(node).getWidth();
    }
    return width + gapDimension.width * (contentNodes.size() - 1);
  }

  private void positionInnerNodes() {
    IVisualizableNode[] innerNodes = getInnerNodes();
    int nodePosition = getLeftSide() + innerNodes[0].getWidth() / 2;
    for (int nodeIndex = 0; nodeIndex < innerNodes.length; nodeIndex++) {
      IVisualizableNode node = innerNodes[nodeIndex];
      node.setPosition(nodePosition);
      if (nodeIndex < innerNodes.length - 1) {
        nodePosition += node.getWidth() / 2 + gapDimension.width + innerNodes[nodeIndex + 1].getWidth() / 2;
      }
    }
  }

  public boolean isOfSameLeafGroup(final IVisualizableNode node) {
    for (IVisualizableNode visualizableNode : getInnerNodes()) {
      if (visualizableNode.isOfSameLeafGroup(node)) {
        return true;
      }
    }
    return false;
  }

  public void accept(final IVisualizableNodeVisitor visitor) {
    visitor.visitHorizontalMetaNode(this);
  }

  public IVisualizableNode[] getInnerNodes() {
    List<IVisualizableNode> innerVisualizableNodes = new ArrayList<IVisualizableNode>();
    for (ISimpleNode node : contentNodes) {
      innerVisualizableNodes.add(innerVisualizableNodesByContent.get(node));
    }
    return innerVisualizableNodes.toArray(new IVisualizableNode[innerVisualizableNodes.size()]);
  }

  @Override
  public void setLayer(final ILayer layer) {
    for (IVisualizableNode node : getInnerNodes()) {
      node.setLayer(layer);
    }
    super.setLayer(layer);
  }

  private void refreshContentMap() {
    Map<ISimpleNode, IVisualizableNode> contentNodeMap = getContentNodeMap();
    for (ISimpleNode node : contentNodes) {
      contentNodeMap.put(node, innerVisualizableNodesByContent.get(node));
    }
  }

  public boolean isRootNode() {
    return false;
  }

  public final void toXML(final Element element) {
    throw new UnsupportedOperationException("Metanodes should be unrolled before creating XML."); //$NON-NLS-1$
  }

  public void resolveMetanode() {
    positionInnerNodes();
    refreshContentMap();
  }
}