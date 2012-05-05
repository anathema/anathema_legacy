package net.sf.anathema.platform.svgtree.document;

import net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants;
import net.sf.anathema.platform.svgtree.document.util.SVGCreationUtils;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;
import org.apache.batik.util.SVGConstants;
import org.dom4j.Document;
import org.dom4j.Element;

import java.awt.Dimension;

public class SvgDocumentBuilder implements CascadeBuilder<Element, Document> {
  private final static Dimension MAXIMUM_DIMENSION = new Dimension(1400, 625);
  private final SVGDocumentFrameFactory factory;
  private final ITreePresentationProperties properties;
  private Document cascadeDocument;
  private Element root;
  private Element cascadeElement;

  public SvgDocumentBuilder(SVGDocumentFrameFactory factory, ITreePresentationProperties properties) {
    this.factory = factory;
    this.properties = properties;
  }

  @Override
  public void initialize() {
    this.cascadeDocument = factory.createFrame(properties);
    this.root = cascadeDocument.getRootElement();
    this.cascadeElement = createCascadeElement(root);
  }

  @Override
  public void applyFinalTouch(double currentWidth, double maximumHeight) {
    setViewBox(currentWidth, maximumHeight, root);
  }

  private void setViewBox(double width, double height, Element root) {
    if (height > MAXIMUM_DIMENSION.height || width > MAXIMUM_DIMENSION.width) {
      double viewBoxHeight = Math.max(height, width / 2.24);
      double viewBoxWidth = Math.max(width, height * 2.24) + 10;
      root.addAttribute(SVGConstants.SVG_VIEW_BOX_ATTRIBUTE,
              "0 0 " + viewBoxWidth + " " + viewBoxHeight); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  private Element createCascadeElement(Element root) {
    Element cascadeElement = root.addElement(SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG));
    cascadeElement.addAttribute(SVGConstants.SVG_ID_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_CASCADE_ID);
    return cascadeElement;
  }

  @Override
  public Document create() {
    return cascadeDocument;
  }

  @Override
  public void add(Element graphElement) {
    cascadeElement.add(graphElement);
  }
}