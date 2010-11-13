package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.graph.graph.IProperHierarchicalGraph;
import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.platform.svgtree.document.components.ILayer;
import net.sf.anathema.platform.svgtree.document.components.IVisualizableNode;
import net.sf.anathema.platform.svgtree.document.components.Layer;
import net.sf.anathema.platform.svgtree.document.util.SVGCreationUtils;

import org.apache.batik.util.SVGConstants;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultElement;

public class SingleNodeVisualizer extends AbstractCascadeVisualizer {

  public SingleNodeVisualizer(ITreePresentationProperties properties, IProperHierarchicalGraph graph) {
    super(properties, graph);
  }

  public IVisualizedGraph buildTree() {
    ISimpleNode node = getGraph().getNodesByLayer(1)[0];
    IVisualizableNode visualizableNode = getNodeFactory().registerVisualizableNode(node);
    ILayer layer = new Layer(getProperties().getGapDimension(), 0);
    visualizableNode.setLayer(layer);
    visualizableNode.setPosition(visualizableNode.getWidth() / 2);
    QName group = SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG);
    Element cascadeElement = new DefaultElement(group);
    visualizableNode.toXML(cascadeElement);
    return new SingleNodeVisualizedGraph(cascadeElement, getProperties().getNodeDimension());
  }
}