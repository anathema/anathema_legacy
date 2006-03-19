package net.sf.anathema.charmtree.provider.svg;

import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_FILL;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_HEIGHT;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_ID;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_POINTS;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_PRESERVE_ASPECT_RATIO;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_STROKE;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_STROKE_WIDTH;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_VIEW_BOX;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_WIDTH;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.TAG_DEFS;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.TAG_G;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.TAG_POLYGON;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.TAG_SVG;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.TAG_SYMBOL;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_0;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_100_PERCENT;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_3;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_ARROWHEAD_ID;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_ARROWHEAD_POINTS;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_FRAME_ID;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_NONE;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_COLOR_BLACK;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_STROKE_MEDIUM_GRAY;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_VIEWBOX_SIZE;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_XMID_YMIN_MEET;

import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultDocumentType;
import org.dom4j.tree.DefaultElement;

public class SVGDocumentFrameFactory {

  public Document createFrame(ICharmPresentationProperties properties) {
    QName svg = SVGCreationUtils.createSVGQName(TAG_SVG);
    Element rootElement = new DefaultElement(svg);
    defineRootAttributes(rootElement);
    QName defs = SVGCreationUtils.createSVGQName(TAG_DEFS);
    Element defsElement = rootElement.addElement(defs);
    addCharmFrameSymbol(properties, defsElement);
    addArrowHeadSymbol(defsElement);
    Document frameDocument = DocumentFactory.getInstance().createDocument(rootElement);
    frameDocument.setDocType(new DefaultDocumentType());
    return frameDocument;
  }

  private void addArrowHeadSymbol(Element defsElement) {
    QName arrowHeadSymbol = SVGCreationUtils.createSVGQName(TAG_SYMBOL);
    Element arrowHeadSymbolElement = defsElement.addElement(arrowHeadSymbol);
    arrowHeadSymbolElement.addAttribute(ATTRIB_ID, VALUE_ARROWHEAD_ID);
    QName polygon = SVGCreationUtils.createSVGQName(TAG_POLYGON);
    Element polygonElement = arrowHeadSymbolElement.addElement(polygon);
    polygonElement.addAttribute(ATTRIB_POINTS, VALUE_ARROWHEAD_POINTS);
    polygonElement.addAttribute(ATTRIB_STROKE, VALUE_COLOR_BLACK);
    polygonElement.addAttribute(ATTRIB_STROKE_WIDTH, VALUE_0);
  }

  private void addCharmFrameSymbol(ICharmPresentationProperties properties, Element defsElement) {
    QName frameSymbol = SVGCreationUtils.createSVGQName(TAG_SYMBOL);
    Element frameSymbolElement = defsElement.addElement(frameSymbol);
    frameSymbolElement.addAttribute(ATTRIB_ID, VALUE_FRAME_ID);
    QName frameGroup = SVGCreationUtils.createSVGQName(TAG_G);
    Element frameGroupElement = frameSymbolElement.addElement(frameGroup);
    QName polygon = SVGCreationUtils.createSVGQName(TAG_POLYGON);
    Element innerPolygonElement = frameGroupElement.addElement(polygon);
    innerPolygonElement.addAttribute(ATTRIB_STROKE_WIDTH, VALUE_0);
    innerPolygonElement.addAttribute(ATTRIB_POINTS, properties.getCharmFramePolygonString());
    Element outerPolygonElement = frameGroupElement.addElement(polygon);
    outerPolygonElement.addAttribute(ATTRIB_FILL, VALUE_NONE);
    outerPolygonElement.addAttribute(ATTRIB_STROKE_WIDTH, VALUE_3);
    outerPolygonElement.addAttribute(ATTRIB_STROKE, VALUE_STROKE_MEDIUM_GRAY);
    outerPolygonElement.addAttribute(ATTRIB_POINTS, properties.getCharmFramePolygonString());
  }

  private void defineRootAttributes(Element rootElement) {
    rootElement.addAttribute(ATTRIB_WIDTH, ATTRIB_ID);
    rootElement.addAttribute(ATTRIB_WIDTH, VALUE_100_PERCENT);
    rootElement.addAttribute(ATTRIB_HEIGHT, VALUE_100_PERCENT);
    rootElement.addAttribute(ATTRIB_VIEW_BOX, VALUE_VIEWBOX_SIZE);
    rootElement.addAttribute(ATTRIB_PRESERVE_ASPECT_RATIO, VALUE_XMID_YMIN_MEET);
  }
}