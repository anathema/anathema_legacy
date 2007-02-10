package net.sf.anathema.charmtree.provider.visualizer;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.framework.magic.treelayout.graph.IProperHierarchicalGraph;
import net.sf.anathema.character.generic.framework.magic.treelayout.nodes.ISimpleNode;
import net.sf.anathema.character.generic.template.presentation.ITreePresentationProperties;
import net.sf.anathema.charmtree.provider.components.ILayer;
import net.sf.anathema.charmtree.provider.components.Layer;
import net.sf.anathema.charmtree.provider.components.nodes.IVisualizableNode;
import net.sf.anathema.charmtree.provider.components.nodes.VisualizableNodeFactory;
import net.sf.anathema.charmtree.provider.svg.SVGCreationUtils;
import net.sf.anathema.lib.collection.MultiEntryMap;

import org.apache.batik.util.SVGConstants;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultElement;

public abstract class AbstractCharmCascadeVisualizer implements ICharmCascadeVisualizer {

  private final ITreePresentationProperties properties;
  private final Map<ISimpleNode, IVisualizableNode> visualizableNodesByContent = new HashMap<ISimpleNode, IVisualizableNode>();
  private final MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors = new MultiEntryMap<ISimpleNode, ISimpleNode>();
  private final VisualizableNodeFactory nodeFactory;
  private final IProperHierarchicalGraph graph;

  public AbstractCharmCascadeVisualizer(ITreePresentationProperties properties, IProperHierarchicalGraph graph) {
    this.properties = properties;
    this.graph = graph;
    this.nodeFactory = new VisualizableNodeFactory(
        properties.getNodeDimension(),
        properties.getGapDimension(),
        visualizableNodesByContent,
        leafNodesByAncestors);
  }

  protected ITreePresentationProperties getProperties() {
    return properties;
  }

  protected VisualizableNodeFactory getNodeFactory() {
    return nodeFactory;
  }

  protected IProperHierarchicalGraph getGraph() {
    return graph;
  }

  protected void registerLeafNodeAncestor(ISimpleNode ancestor, ISimpleNode leaf) {
    leafNodesByAncestors.add(ancestor, leaf);
  }

  protected ILayer[] createLayers(int layerCount) {
    ILayer[] layers = new ILayer[layerCount];
    initLayers(layers);
    for (int layerIndex = layerCount - 1; layerIndex >= 0; layerIndex--) {
      addNodesToLayers(layerIndex, layers);
    }
    return layers;
  }

  private void addNodesToLayers(int layerIndex, ILayer[] layers) {
    ILayer currentLayer = layers[layerIndex];
    ISimpleNode[] nodesOnLayer = graph.getNodesByLayer(layerIndex + 1);
    for (ISimpleNode node : nodesOnLayer) {
      currentLayer.addNode(getVisualizableNode(node));
    }
  }

  protected boolean isVisualizableNodeRegistered(ISimpleNode node) {
    return visualizableNodesByContent.containsKey(node);
  }

  protected IVisualizableNode getVisualizableNode(ISimpleNode node) {
    return visualizableNodesByContent.get(node);
  }

  private void initLayers(ILayer[] layers) {
    for (int layerIndex = 0; layerIndex < layers.length; layerIndex++) {
      int yPosition = layerIndex * (properties.getNodeDimension().height + properties.getGapDimension().height);
      layers[layerIndex] = new Layer(properties.getGapDimension(), yPosition);
    }
    for (int layerIndex = 1; layerIndex < layers.length; layerIndex++) {
      layers[layerIndex].setPreviousLayer(layers[layerIndex - 1]);
    }
    for (int layerIndex = 0; layerIndex < layers.length - 1; layerIndex++) {
      layers[layerIndex].setFollowUp(layers[layerIndex + 1]);
    }
  }

  protected Element createXml(ILayer[] layers) {
    QName group = SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG);
    Element cascadeElement = new DefaultElement(group);
    for (ILayer layer : layers) {
      layer.addNodesToXml(cascadeElement);
    }
    for (ILayer layer : layers) {
      layer.addArrowsToXml(cascadeElement);
    }
    return cascadeElement;
  }

  protected int getTreeHeight(ILayer[] layers) {
    int treeHeight = layers.length
        * getProperties().getNodeDimension().height
        + (layers.length - 1)
        * getProperties().getGapDimension().height;
    return treeHeight;
  }
}