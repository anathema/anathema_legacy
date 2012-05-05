package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.graph.graph.LayeredGraph;
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

  public SingleNodeVisualizer(ITreePresentationProperties properties, LayeredGraph graph) {
    super(properties, graph);
  }

  @Override
  public IVisualizedGraph buildTree() {
    ISimpleNode node = getGraph().getNodesByLayer(1)[0];
    IVisualizableNode visualizableNode = getNodeFactory().registerVisualizableNode(node);
    ILayer layer = new Layer(getProperties().getGapDimension(), 0, getProperties());
    visualizableNode.setLayer(layer);
    visualizableNode.setPosition(visualizableNode.getWidth() / 2);
    return makeSvgGraph(visualizableNode, layer);
  }

  private IVisualizedGraph makeSvgGraph(IVisualizableNode visualizableNode, ILayer layer) {
    QName group = SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG);
    Element cascadeElement = new DefaultElement(group);
    visualizableNode.accept(new CreateSvgElementFromNode(layer, cascadeElement, getProperties()));
    return new SingleNodeVisualizedGraph(cascadeElement, getProperties().getNodeDimension());
  }

}