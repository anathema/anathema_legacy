package net.sf.anathema.platform.svgtree.document.svg;

import static net.sf.anathema.platform.svgtree.document.svg.ISVGCascadeXMLConstants.VALUE_3;
import static net.sf.anathema.platform.svgtree.document.svg.ISVGCascadeXMLConstants.VALUE_ARROWHEAD_ID;
import static net.sf.anathema.platform.svgtree.document.svg.ISVGCascadeXMLConstants.VALUE_ARROWHEAD_POINTS;
import static net.sf.anathema.platform.svgtree.document.svg.ISVGCascadeXMLConstants.VALUE_COLOR_SVG_BLACK;
import static net.sf.anathema.platform.svgtree.document.svg.ISVGCascadeXMLConstants.VALUE_COLOR_SVG_GRAY;
import static net.sf.anathema.platform.svgtree.document.svg.ISVGCascadeXMLConstants.VALUE_FRAME_ID;
import static net.sf.anathema.platform.svgtree.document.svg.ISVGCascadeXMLConstants.VALUE_SVG_VERSION;
import static net.sf.anathema.platform.svgtree.document.svg.ISVGCascadeXMLConstants.VALUE_VIEWBOX_SIZE;
import static net.sf.anathema.platform.svgtree.document.svg.ISVGCascadeXMLConstants.VALUE_XMID_YMIN_MEET;

import net.sf.anathema.character.generic.template.presentation.ITreePresentationProperties;

import org.apache.batik.util.SVGConstants;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultDocumentType;
import org.dom4j.tree.DefaultElement;

public class SVGDocumentFrameFactory {

  public Document createFrame(ITreePresentationProperties properties) {
    QName svg = SVGCreationUtils.createSVGQName(SVGConstants.SVG_SVG_TAG);
    Element rootElement = new DefaultElement(svg);
    defineRootAttributes(rootElement);
    QName defs = SVGCreationUtils.createSVGQName(SVGConstants.SVG_DEFS_TAG);
    Element defsElement = rootElement.addElement(defs);
    addCharmFrameSymbol(properties, defsElement);
    addArrowHeadSymbol(defsElement);
    Document frameDocument = DocumentFactory.getInstance().createDocument(rootElement);
    frameDocument.setDocType(new DefaultDocumentType(
        SVGConstants.SVG_SVG_TAG,
        SVGConstants.SVG_PUBLIC_ID,
        SVGConstants.SVG_SYSTEM_ID));
    return frameDocument;
  }

  private void addArrowHeadSymbol(Element defsElement) {
    QName arrowHeadSymbol = SVGCreationUtils.createSVGQName(SVGConstants.SVG_SYMBOL_TAG);
    Element arrowHeadSymbolElement = defsElement.addElement(arrowHeadSymbol);
    arrowHeadSymbolElement.addAttribute(SVGConstants.SVG_ID_ATTRIBUTE, VALUE_ARROWHEAD_ID);
    QName polygon = SVGCreationUtils.createSVGQName(SVGConstants.SVG_POLYGON_TAG);
    Element polygonElement = arrowHeadSymbolElement.addElement(polygon);
    polygonElement.addAttribute(SVGConstants.SVG_POINTS_ATTRIBUTE, VALUE_ARROWHEAD_POINTS);
    polygonElement.addAttribute(SVGConstants.SVG_STROKE_ATTRIBUTE, VALUE_COLOR_SVG_BLACK);
    polygonElement.addAttribute(SVGConstants.SVG_STROKE_WIDTH_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
  }

  private void addCharmFrameSymbol(ITreePresentationProperties properties, Element defsElement) {
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

  private void defineRootAttributes(Element rootElement) {
    rootElement.addAttribute(SVGConstants.SVG_WIDTH_ATTRIBUTE, SVGConstants.SVG_HUNDRED_PERCENT_VALUE);
    rootElement.addAttribute(SVGConstants.SVG_HEIGHT_ATTRIBUTE, SVGConstants.SVG_HUNDRED_PERCENT_VALUE);
    rootElement.addAttribute(SVGConstants.SVG_VIEW_BOX_ATTRIBUTE, VALUE_VIEWBOX_SIZE);
    rootElement.addAttribute(SVGConstants.SVG_PRESERVE_ASPECT_RATIO_ATTRIBUTE, VALUE_XMID_YMIN_MEET);
    rootElement.addAttribute(SVGConstants.SVG_VERSION_ATTRIBUTE, VALUE_SVG_VERSION);
  }
}