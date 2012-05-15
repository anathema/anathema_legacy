package net.sf.anathema.platform.svgtree.document.components;

import net.sf.anathema.graph.nodes.ISimpleNode;
import org.dom4j.Element;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HorizontalMetaNode extends AbstractVisualizableNode {

  private final List<ISimpleNode> contentNodes = new ArrayList<ISimpleNode>();
  private final Dimension gapDimension;
  private final Map<ISimpleNode, IVisualizableNode> innerVisualizableNodesByContent = new HashMap<ISimpleNode, IVisualizableNode>();

  public HorizontalMetaNode(
      Map<ISimpleNode, IVisualizableNode> map,
      Dimension nodeDimension,
      Dimension gapDimension) {
    super(map, nodeDimension);
    this.gapDimension = gapDimension;
  }

  public void addInnerNode(ISimpleNode node, IVisualizableNode visualizableNode) {
    contentNodes.add(node);
    innerVisualizableNodesByContent.put(node, visualizableNode);
  }

  @Override
  public IVisualizableNode[] getChildren() {
    Set<IVisualizableNode> children = new LinkedHashSet<IVisualizableNode>();
    for (ISimpleNode node : contentNodes) {
      for (ISimpleNode child : node.getChildren()) {
        children.add(getContentNodeMap().get(child));
      }
    }
    return children.toArray(new IVisualizableNode[children.size()]);
  }

  @Override
  public IVisualizableNode[] getParents() {
    Set<IVisualizableNode> parents = new LinkedHashSet<IVisualizableNode>();
    for (ISimpleNode node : contentNodes) {
      for (ISimpleNode parent : node.getParents()) {
        parents.add(getContentNodeMap().get(parent));
      }
    }
    return parents.toArray(new IVisualizableNode[parents.size()]);
  }

  @Override
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

  @Override
  public boolean isOfSameLeafGroup(IVisualizableNode node) {
    for (IVisualizableNode visualizableNode : getInnerNodes()) {
      if (visualizableNode.isOfSameLeafGroup(node)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void accept(IVisualizableNodeVisitor visitor) {
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
  public void setLayer(ILayer layer) {
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

  @Override
  public boolean isRootNode() {
    return false;
  }

  @Override
  public final void toXML(Element element) {
    throw new UnsupportedOperationException("Metanodes should be unrolled before creating XML."); //$NON-NLS-1$
  }

  public void resolveMetanode() {
    positionInnerNodes();
    refreshContentMap();
  }
}