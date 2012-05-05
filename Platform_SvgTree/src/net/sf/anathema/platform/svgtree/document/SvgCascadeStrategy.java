package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import net.sf.anathema.platform.svgtree.document.visualizer.SvgGraphFactory;
import net.sf.anathema.platform.svgtree.document.visualizer.VisualizedGraphFactory;
import org.dom4j.Document;

public class SvgCascadeStrategy implements CascadeCreationStrategy<Document> {
  private final SVGDocumentFrameFactory factory = new SVGDocumentFrameFactory();

  @Override
  public SvgDocumentBuilder createCascadeBuilder(ITreePresentationProperties properties) {
    return new SvgDocumentBuilder(factory, properties);
  }

  @Override
  public VisualizedGraphFactory getFactoryForVisualizedGraphs(ITreePresentationProperties properties) {
    return new SvgGraphFactory(properties);
  }
}