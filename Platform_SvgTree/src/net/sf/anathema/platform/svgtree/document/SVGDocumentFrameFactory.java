package net.sf.anathema.platform.svgtree.document;

import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.*;
import net.sf.anathema.platform.svgtree.document.util.SVGCreationUtils;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

import org.apache.batik.util.SVGConstants;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultDocumentType;
import org.dom4j.tree.DefaultElement;

public class SVGDocumentFrameFactory {

  public Document createFrame(final ITreePresentationProperties properties) {
    QName svg = SVGCreationUtils.createSVGQName(SVGConstants.SVG_SVG_TAG);
    Element rootElement = new DefaultElement(svg);
    defineRootAttributes(rootElement);
    QName defs = SVGCreationUtils.createSVGQName(SVGConstants.SVG_DEFS_TAG);
    Element defsElement = rootElement.addElement(defs);
    addNodeFrameSymbol(properties, defsElement);
    addArrowHeadSymbol(defsElement);
    Document frameDocument = DocumentFactory.getInstance().createDocument(rootElement);
    frameDocument.setDocType(new DefaultDocumentType(
        SVGConstants.SVG_SVG_TAG,
        SVGConstants.SVG_PUBLIC_ID,
        SVGConstants.SVG_SYSTEM_ID));
    return frameDocument;
  }

  private void addArrowHeadSymbol(final Element defsElement) {
    QName arrowHeadSymbol = SVGCreationUtils.createSVGQName(SVGConstants.SVG_SYMBOL_TAG);
    Element arrowHeadSymbolElement = defsElement.addElement(arrowHeadSymbol);
    arrowHeadSymbolElement.addAttribute(SVGConstants.SVG_ID_ATTRIBUTE, VALUE_ARROWHEAD_ID);
    QName polygon = SVGCreationUtils.createSVGQName(SVGConstants.SVG_POLYGON_TAG);
    Element polygonElement = arrowHeadSymbolElement.addElement(polygon);
    polygonElement.addAttribute(SVGConstants.SVG_POINTS_ATTRIBUTE, VALUE_ARROWHEAD_POINTS);
    polygonElement.addAttribute(SVGConstants.SVG_STROKE_ATTRIBUTE, VALUE_COLOR_SVG_BLACK);
    polygonElement.addAttribute(SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
  }

  private void addNodeFrameSymbol(final ITreePresentationProperties properties, final Element defsElement) {
    QName frameSymbol = SVGCreationUtils.createSVGQName(SVGConstants.SVG_SYMBOL_TAG);
    Element frameSymbolElement = defsElement.addElement(frameSymbol);
    frameSymbolElement.addAttribute(SVGConstants.SVG_ID_ATTRIBUTE, VALUE_FRAME_ID);
    QName frameGroup = SVGCreationUtils.createSVGQName(SVGConstants.SVG_G_TAG);
    Element frameGroupElement = frameSymbolElement.addElement(frameGroup);
    QName polygon = SVGCreationUtils.createSVGQName(SVGConstants.SVG_POLYGON_TAG);
    Element innerPolygonElement = frameGroupElement.addElement(polygon);
    innerPolygonElement.addAttribute(SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    innerPolygonElement.addAttribute(SVGConstants.SVG_POINTS_ATTRIBUTE, properties.getNodeFramePolygonString());
    Element outerPolygonElement = frameGroupElement.addElement(polygon);
    outerPolygonElement.addAttribute(SVGConstants.SVG_FILL_ATTRIBUTE, SVGConstants.SVG_NONE_VALUE);
    outerPolygonElement.addAttribute(SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, VALUE_3);
    outerPolygonElement.addAttribute(SVGConstants.SVG_STROKE_ATTRIBUTE, VALUE_COLOR_SVG_GRAY);
    outerPolygonElement.addAttribute(SVGConstants.SVG_POINTS_ATTRIBUTE, properties.getNodeFramePolygonString());
  }

  private void defineRootAttributes(final Element rootElement) {
    rootElement.addAttribute(SVGConstants.SVG_WIDTH_ATTRIBUTE, SVGConstants.SVG_HUNDRED_PERCENT_VALUE);
    rootElement.addAttribute(SVGConstants.SVG_HEIGHT_ATTRIBUTE, SVGConstants.SVG_HUNDRED_PERCENT_VALUE);
    rootElement.addAttribute(SVGConstants.SVG_VIEW_BOX_ATTRIBUTE, VALUE_VIEWBOX_SIZE);
    rootElement.addAttribute(SVGConstants.SVG_PRESERVE_ASPECT_RATIO_ATTRIBUTE, VALUE_XMID_YMIN_MEET);
    rootElement.addAttribute(SVGConstants.SVG_VERSION_ATTRIBUTE, VALUE_SVG_VERSION);
  }
}