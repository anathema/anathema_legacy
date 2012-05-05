package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants;
import net.sf.anathema.platform.svgtree.document.util.SVGCreationUtils;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;
import org.apache.batik.util.SVGConstants;
import org.dom4j.Document;
import org.dom4j.Element;

import java.awt.Dimension;
import java.util.List;

public class CascadeDocumentFactory implements CascadeFactory<Document> {

  private final static Dimension MAXIMUM_DIMENSION = new Dimension(1400, 625);
  private final SVGDocumentFrameFactory factory = new SVGDocumentFrameFactory();

  @Override
  public Document createCascade(IRegularNode[] nodes, ITreePresentationProperties properties) {
    List<IVisualizedGraph> visualizedGraphs = new VisualizedGraphFactory(nodes, properties).visualizeGraphs();
    return placeOnCanvas(properties, visualizedGraphs);
  }

  private Document placeOnCanvas(ITreePresentationProperties properties, List<IVisualizedGraph> visualizedGraphs) {
    Document cascadeDocument = factory.createFrame(properties);
    Element root = cascadeDocument.getRootElement();
    Element cascadeElement = createCascadeElement(root);
    double firstRowWidth = 0;
    double firstRowHeight = 0;
    for (IVisualizedGraph graph : visualizedGraphs) {
      if (graph.isSingleNode()) {
        firstRowWidth = properties.getGapDimension().width;
        firstRowHeight = properties.getNodeDimension().height + properties.getGapDimension().height;
        break;
      }
    }
    double currentWidth = properties.getGapDimension().width;
    double maximumHeight = 0;
    for (IVisualizedGraph graph : visualizedGraphs) {
      Element graphElement = graph.getCascadeElement();
      cascadeElement.add(graphElement);
      if (graph.isSingleNode()) {
        graphElement.addAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE, "translate(" + firstRowWidth + " 0)"); //$NON-NLS-1$ //$NON-NLS-2$
        firstRowWidth += properties.getGapDimension().width + graph.getDimension().width;
      } else {
        graphElement.addAttribute(SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
                "translate(" + currentWidth + " " + firstRowHeight + ")"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        currentWidth += properties.getGapDimension().width + graph.getDimension().width;
        maximumHeight = Math.max(maximumHeight, graph.getDimension().height);
      }
    }
    maximumHeight += firstRowHeight;
    currentWidth = Math.max(currentWidth, firstRowWidth);
    setViewBox(currentWidth, maximumHeight, root);
    return cascadeDocument;
  }

  private Element createCascadeElement(Element root) {
    Element cascadeElement = root.addElement(SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG));
    cascadeElement.addAttribute(SVGConstants.SVG_ID_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_CASCADE_ID);
    return cascadeElement;
  }

  private void setViewBox(double width, double height, Element root) {
    if (height > MAXIMUM_DIMENSION.height || width > MAXIMUM_DIMENSION.width) {
      double viewBoxHeight = Math.max(height, width / 2.24);
      double viewBoxWidth = Math.max(width, height * 2.24) + 10;
      root.addAttribute(SVGConstants.SVG_VIEW_BOX_ATTRIBUTE,
              "0 0 " + viewBoxWidth + " " + viewBoxHeight); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }
}