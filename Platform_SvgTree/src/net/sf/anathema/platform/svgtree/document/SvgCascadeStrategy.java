package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.graph.nodes.IRegularNode;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.IVisualizedGraph;
import org.dom4j.Document;

import java.util.List;

public class SvgCascadeStrategy implements CascadeCreationStrategy<Document> {
  private final SVGDocumentFrameFactory factory = new SVGDocumentFrameFactory();

  @Override
  public List<IVisualizedGraph> visualizeGraphs(IRegularNode[] nodes, ITreePresentationProperties properties) {
    return new VisualizedGraphFactory(nodes, properties).visualizeGraphs();
  }

  @Override
  public SvgDocumentBuilder createCascadeBuilder(ITreePresentationProperties properties) {
    return new SvgDocumentBuilder(factory, properties);
  }
}