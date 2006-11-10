package net.sf.anathema.charmtree.provider.visualizer;

import java.awt.Dimension;

import net.sf.anathema.character.generic.framework.magic.treelayout.graph.IProperHierarchicalGraph;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.ISimpleNode;
import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;
import net.sf.anathema.charmtree.provider.IVisualizedGraph;
import net.sf.anathema.charmtree.provider.VisualizedGraph;
import net.sf.anathema.charmtree.provider.components.ILayer;
import net.sf.anathema.charmtree.provider.components.nodes.IVisualizableNode;

public abstract class AbstractTreeVisualizer extends AbstractCharmCascadeVisualizer {

  public AbstractTreeVisualizer(ICharmPresentationProperties properties, IProperHierarchicalGraph graph) {
    super(properties, graph);
  }

  private void createVisualizableNodes(int layerIndex) {
    ISimpleNode[] layerNodes = getGraph().getNodesByLayer(layerIndex + 1);
    for (ISimpleNode currentNode : layerNodes) {
      getNodeFactory().registerVisualizableNode(currentNode);
    }
  }

  public IVisualizedGraph buildCharmTree() {
    int layerCount = getGraph().getDeepestLayer();
    for (int layerIndex = layerCount - 1; layerIndex >= 0; layerIndex--) {
      createVisualizableNodes(layerIndex);
    }
    ILayer[] layers = createLayers(layerCount);
    int treeWidth = setPositionRecursively(getInitialLayer(layers).getNodes()[0], 0)
        - getProperties().getGapDimension().width;
    return new VisualizedGraph(createXml(layers), new Dimension(treeWidth, getTreeHeight(layers)));
  }

  private int setPositionRecursively(IVisualizableNode node, int rightSide) {
    IVisualizableNode[] relatives = getRelatives(node);
    if (relatives.length == 0) {
      node.setPosition(rightSide + node.getWidth() / 2);
      return node.getRightSide() + getProperties().getGapDimension().width;
    }
    for (IVisualizableNode relative : relatives) {
      rightSide = setPositionRecursively(relative, rightSide);
    }
    node.setPosition((relatives[0].getPosition() + relatives[relatives.length - 1].getPosition()) / 2);
    return rightSide;
  }

  protected abstract ILayer getInitialLayer(ILayer[] layers);

  protected abstract IVisualizableNode[] getRelatives(IVisualizableNode node);
}