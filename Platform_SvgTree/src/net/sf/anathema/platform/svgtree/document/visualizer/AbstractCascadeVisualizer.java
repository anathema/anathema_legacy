package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.graph.graph.LayeredGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNode;
import net.sf.anathema.platform.svgtree.document.components.Layer;
import net.sf.anathema.platform.svgtree.document.components.VisualizableNodeFactory;
import net.sf.anathema.platform.svgtree.document.util.SVGCreationUtils;
import org.apache.batik.util.SVGConstants;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultElement;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCascadeVisualizer implements ICascadeVisualizer {

  private final ITreePresentationProperties properties;
  private final Map<ISimpleNode, IVisualizableNode> visualizableNodesByContent =
          new HashMap<ISimpleNode, IVisualizableNode>();
  private final MultiEntryMap<ISimpleNode, ISimpleNode> leafNodesByAncestors =
          new MultiEntryMap<ISimpleNode, ISimpleNode>();
  private final VisualizableNodeFactory nodeFactory;
  private final LayeredGraph graph;

  public AbstractCascadeVisualizer(ITreePresentationProperties properties, LayeredGraph graph) {
    this.properties = properties;
    this.graph = graph;
    this.nodeFactory = new VisualizableNodeFactory(properties.getNodeDimension(), properties.getGapDimension(),
            properties.getVerticalLineDimension(), visualizableNodesByContent, leafNodesByAncestors);
  }

  protected ITreePresentationProperties getProperties() {
    return properties;
  }

  protected VisualizableNodeFactory getNodeFactory() {
    return nodeFactory;
  }

  protected LayeredGraph getGraph() {
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

  protected Dimension getTreeDimension(ILayer[] layers) {
    return new Dimension(getTreeWidth(layers), getTreeHeight(layers));
  }

  protected int getTreeWidth(ILayer[] layers) {
    int width = 0;
    for (ILayer layer : layers) {
      width = Math.max(width, layer.getWidth());
    }
    return width;
  }

  protected int getTreeHeight(ILayer[] layers) {
    return layers.length * getProperties().getNodeDimension().height +
            (layers.length - 1) * getProperties().getGapDimension().height;
  }
}
