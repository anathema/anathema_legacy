package net.sf.anathema.platform.svgtree.document;

import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.*;
import static org.apache.batik.util.SVGConstants.*;
import net.sf.anathema.platform.svgtree.document.util.SVGCreationUtils;
import net.sf.anathema.platform.svgtree.document.visualizer.ITreePresentationProperties;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultDocumentType;
import org.dom4j.tree.DefaultElement;

public class SVGDocumentFrameFactory {

  public Document createFrame(final ITreePresentationProperties properties) {
    QName svg = SVGCreationUtils.createSVGQName(SVG_SVG_TAG);
    Element rootElement = new DefaultElement(svg);
    defineRootAttributes(rootElement);
    QName defs = SVGCreationUtils.createSVGQName(SVG_DEFS_TAG);
    Element defsElement = rootElement.addElement(defs);
    addNodeFrameSymbol(properties, defsElement);
    addArrowHeadSymbol(defsElement);
    addArrowBottomSymbol(defsElement);
    Document frameDocument = DocumentFactory.getInstance().createDocument(rootElement);
    frameDocument.setDocType(new DefaultDocumentType(SVG_SVG_TAG, SVG_PUBLIC_ID, SVG_SYSTEM_ID));
    return frameDocument;
  }

  private void addArrowHeadSymbol(final Element defsElement) {
    QName arrowHeadSymbol = SVGCreationUtils.createSVGQName(SVG_MARKER_TAG);
    Element arrowHeadSymbolElement = defsElement.addElement(arrowHeadSymbol);
    arrowHeadSymbolElement.addAttribute(SVG_ID_ATTRIBUTE, VALUE_ARROWHEAD_ID);
    arrowHeadSymbolElement.addAttribute(SVG_ORIENT_ATTRIBUTE, SVG_AUTO_VALUE);
    arrowHeadSymbolElement.addAttribute(SVG_REF_X_ATTRIBUTE, VALUE_8);
    arrowHeadSymbolElement.addAttribute(SVG_REF_Y_ATTRIBUTE, VALUE_15);
    arrowHeadSymbolElement.addAttribute(SVG_VIEW_BOX_ATTRIBUTE, VALUE_ARROWHEAD_VIEWBOX);
    arrowHeadSymbolElement.addAttribute(ATTRIB_OVERFLOW, VALUE_VISIBLE);
    QName polygon = SVGCreationUtils.createSVGQName(SVG_POLYGON_TAG);
    Element polygonElement = arrowHeadSymbolElement.addElement(polygon);
    polygonElement.addAttribute(SVG_POINTS_ATTRIBUTE, VALUE_ARROWHEAD_POINTS);
    polygonElement.addAttribute(SVG_STROKE_ATTRIBUTE, VALUE_COLOR_SVG_BLACK);
    polygonElement.addAttribute(SVG_STROKE_WIDTH_ATTRIBUTE, SVG_ZERO_VALUE);
  }

  private void addArrowBottomSymbol(final Element defsElement) {
    QName arrowBottomSymbol = SVGCreationUtils.createSVGQName(SVG_MARKER_TAG);
    Element arrowHeadSymbolElement = defsElement.addElement(arrowBottomSymbol);
    arrowHeadSymbolElement.addAttribute(SVG_ID_ATTRIBUTE, VALUE_ARROWBOTTOM_ID);
    arrowHeadSymbolElement.addAttribute(SVG_REF_X_ATTRIBUTE, VALUE_8);
    arrowHeadSymbolElement.addAttribute(SVG_REF_Y_ATTRIBUTE, VALUE_8);
    arrowHeadSymbolElement.addAttribute(SVG_VIEW_BOX_ATTRIBUTE, VALUE_ARROWBOTTOM_VIEWBOX);
    QName path = SVGCreationUtils.createSVGQName(SVG_CIRCLE_TAG);
    Element pathElement = arrowHeadSymbolElement.addElement(path);
    pathElement.addAttribute(SVG_R_ATTRIBUTE, VALUE_6);
    pathElement.addAttribute(SVG_CY_ATTRIBUTE, VALUE_8);
    pathElement.addAttribute(SVG_CX_ATTRIBUTE, VALUE_8);
    pathElement.addAttribute(SVG_FILL_ATTRIBUTE, VALUE_COLOR_SVG_BLACK);
  }

  private void addNodeFrameSymbol(final ITreePresentationProperties properties, final Element defsElement) {
    QName frameSymbol = SVGCreationUtils.createSVGQName(SVG_SYMBOL_TAG);
    Element frameSymbolElement = defsElement.addElement(frameSymbol);
    frameSymbolElement.addAttribute(SVG_ID_ATTRIBUTE, VALUE_FRAME_ID);
    QName frameGroup = SVGCreationUtils.createSVGQName(SVG_G_TAG);
    Element frameGroupElement = frameSymbolElement.addElement(frameGroup);
    QName polygon = SVGCreationUtils.createSVGQName(SVG_POLYGON_TAG);
    Element innerPolygonElement = frameGroupElement.addElement(polygon);
    innerPolygonElement.addAttribute(SVG_STROKE_WIDTH_ATTRIBUTE, SVG_ZERO_VALUE);
    innerPolygonElement.addAttribute(SVG_POINTS_ATTRIBUTE, properties.getNodeFramePolygonString());
    Element outerPolygonElement = frameGroupElement.addElement(polygon);
    outerPolygonElement.addAttribute(SVG_FILL_ATTRIBUTE, SVG_NONE_VALUE);
    outerPolygonElement.addAttribute(SVG_STROKE_WIDTH_ATTRIBUTE, VALUE_3);
    outerPolygonElement.addAttribute(SVG_STROKE_ATTRIBUTE, VALUE_COLOR_SVG_GRAY);
    outerPolygonElement.addAttribute(SVG_POINTS_ATTRIBUTE, properties.getNodeFramePolygonString());
  }

  private void defineRootAttributes(final Element rootElement) {
    rootElement.addAttribute(SVG_WIDTH_ATTRIBUTE, SVG_HUNDRED_PERCENT_VALUE);
    rootElement.addAttribute(SVG_HEIGHT_ATTRIBUTE, SVG_HUNDRED_PERCENT_VALUE);
    rootElement.addAttribute(SVG_VIEW_BOX_ATTRIBUTE, VALUE_VIEWBOX_SIZE);
    rootElement.addAttribute(SVG_PRESERVE_ASPECT_RATIO_ATTRIBUTE, VALUE_XMID_YMIN_MEET);
    rootElement.addAttribute(SVG_VERSION_ATTRIBUTE, VALUE_SVG_VERSION);
  }
}