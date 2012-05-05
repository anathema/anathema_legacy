package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;
import org.dom4j.Document;

import java.util.List;

public class CascadeDocumentFactory implements CascadeFactory<Document> {

  private final SVGDocumentFrameFactory factory = new SVGDocumentFrameFactory();

  @Override
  public Document createCascade(IRegularNode[] nodes, ITreePresentationProperties properties) {
    List<IVisualizedGraph> visualizedGraphs = new VisualizedGraphFactory(nodes, properties).visualizeGraphs();
    DocumentCascade cascade = new DocumentCascade(factory, properties);
    cascade.initialize();
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
      graph.addTo(cascade);
      if (graph.isSingleNode()) {
        graph.translateBy(firstRowWidth, 0);
        firstRowWidth += properties.getGapDimension().width + graph.getDimension().width;
      } else {
        graph.translateBy(currentWidth, firstRowHeight);
        currentWidth += properties.getGapDimension().width + graph.getDimension().width;
        maximumHeight = Math.max(maximumHeight, graph.getDimension().height);
      }
    }
    maximumHeight += firstRowHeight;
    currentWidth = Math.max(currentWidth, firstRowWidth);
    cascade.applyFinalTouch(currentWidth, maximumHeight);
    return cascade.create();
  }

}