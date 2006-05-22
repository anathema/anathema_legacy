package net.sf.anathema.charmtree.batik.intvalue;

import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_FILL;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_FILL_OPACITY;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_HEIGHT;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_STROKE;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_TRANSFORM;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_WIDTH;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_X;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.ATTRIB_Y;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.TAG_G;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.TAG_RECT;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_COLOR_BLACK;

import net.sf.anathema.charmtree.batik.IBoundsCalculator;
import net.sf.anathema.charmtree.presenter.view.ISVGMultiLearnableCharmView;
import net.sf.anathema.charmtree.presenter.view.ISVGSpecialCharmView;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGGElement;
import org.w3c.dom.svg.SVGLocatable;
import org.w3c.dom.svg.SVGSVGElement;

public class SVGViewControlButton implements ISVGSpecialCharmView {

  private final ISVGMultiLearnableCharmView display;
  private final double charmWidth;
  private boolean enabled = false;
  private final String label;

  public SVGViewControlButton(ISVGMultiLearnableCharmView display, double charmWidth, String label) {
    this.display = display;
    this.charmWidth = charmWidth;
    this.label = label;
  }

  public String getCharmId() {
    return display.getCharmId();
  }

  public Element initGui(SVGOMDocument svgDocument, IBoundsCalculator boundsCalculator) {
    Element outerGroupElement = svgDocument.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, TAG_G);
    SVGGElement innerGroupElement = (SVGGElement) svgDocument.createElementNS(
        SVGDOMImplementation.SVG_NAMESPACE_URI,
        TAG_G);
    innerGroupElement.appendChild(createBorder(svgDocument));
    innerGroupElement.appendChild(createText(svgDocument, label));
    outerGroupElement.appendChild(innerGroupElement);
    Element displayElement = display.initGui(svgDocument, boundsCalculator);
    setAttribute(
        displayElement,
        ATTRIB_TRANSFORM,
        "translate(0," + SVGIntValueDisplay.getDiameter(charmWidth) * 1.15 + ")"); //$NON-NLS-1$ //$NON-NLS-2$
    final SVGSVGElement rootElement = svgDocument.getRootElement();
    innerGroupElement.addEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, createDisplayListener(
        displayElement,
        outerGroupElement,
        rootElement), false);
    svgDocument.addEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, createRemoveListener(
        displayElement,
        outerGroupElement,
        rootElement,
        boundsCalculator), true);
    display.setVisible(false);
    return outerGroupElement;
  }

  private EventListener createRemoveListener(
      final Element displayElement,
      final Element outerGroupElement,
      final SVGSVGElement rootElement,
      final IBoundsCalculator boundsCalculator) {
    return new EventListener() {
      public void handleEvent(Event evt) {
        if (evt.getEventPhase() != Event.CAPTURING_PHASE) {
          return;
        }
        if (!enabled) {
          return;
        }
        if (!(evt instanceof MouseEvent && ((MouseEvent) evt).getButton() == 0)) {
          return;
        }
        MouseEvent event = (MouseEvent) evt;
        if (!boundsCalculator.getBounds((SVGLocatable) displayElement).contains(event.getClientX(), event.getClientY())) {
          removeFromView(displayElement, outerGroupElement, rootElement);
          evt.stopPropagation();
        }
      }
    };
  }

  private EventListener createDisplayListener(
      final Element displayElement,
      final Element outerGroupElement,
      final SVGSVGElement rootElement) {
    return new EventListener() {
      public void handleEvent(Event evt) {
        if (!(evt instanceof MouseEvent && ((MouseEvent) evt).getButton() == 0)) {
          return;
        }
        if (enabled) {
          removeFromView(displayElement, outerGroupElement, rootElement);
        }
        else {
          outerGroupElement.appendChild(displayElement);
          NodeList childNodes = rootElement.getChildNodes();
          for (int index = 0; index < childNodes.getLength(); index++) {
            if (childNodes.item(index) instanceof Element) {
              setAttribute((Element) childNodes.item(index), SVGConstants.SVG_OPACITY_ATTRIBUTE, "0.1"); //$NON-NLS-1$
            }
          }
          setAttribute(outerGroupElement, SVGConstants.SVG_OPACITY_ATTRIBUTE, SVGConstants.SVG_OPAQUE_VALUE);
          display.setVisible(true);
          enabled = true;
        }
      }
    };
  }

  private void removeFromView(
      final Element displayElement,
      final Element outerGroupElement,
      final SVGSVGElement rootElement) {
    display.setVisible(false);
    outerGroupElement.removeChild(displayElement);
    NodeList childNodes = rootElement.getChildNodes();
    for (int index = 0; index < childNodes.getLength(); index++) {
      if (childNodes.item(index) instanceof Element) {
        setAttribute(
            (Element) childNodes.item(index),
            SVGConstants.SVG_OPACITY_ATTRIBUTE,
            SVGConstants.SVG_OPAQUE_VALUE);
      }
    }
    enabled = false;
  }

  private Node createText(SVGOMDocument svgDocument, String labelString) {
    Element textElement = svgDocument.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_TEXT_TAG);
    setAttribute(
        textElement,
        SVGConstants.SVG_Y_ATTRIBUTE,
        String.valueOf(SVGIntValueDisplay.getDiameter(charmWidth) * 0.9));
    setAttribute(textElement, SVGConstants.SVG_X_ATTRIBUTE, String.valueOf(charmWidth / 2));
    setAttribute(textElement, SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, SVGConstants.SVG_MIDDLE_VALUE);
    Text text = svgDocument.createTextNode(labelString);
    textElement.appendChild(text);
    return textElement;
  }

  private Element createBorder(SVGDocument document) {
    Element rectangle = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, TAG_RECT);
    setAttribute(rectangle, ATTRIB_X, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(rectangle, ATTRIB_Y, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(rectangle, ATTRIB_WIDTH, String.valueOf(charmWidth));
    setAttribute(rectangle, ATTRIB_HEIGHT, String.valueOf(SVGIntValueDisplay.getDiameter(charmWidth) * 1.15));
    setAttribute(rectangle, ATTRIB_STROKE, VALUE_COLOR_BLACK);
    setAttribute(rectangle, ATTRIB_FILL, VALUE_COLOR_BLACK);
    setAttribute(rectangle, ATTRIB_FILL_OPACITY, SVGConstants.SVG_ZERO_VALUE);
    return rectangle;
  }

  private void setAttribute(org.w3c.dom.Element element, String attributeName, String attributeValue) {
    element.setAttributeNS(null, attributeName, attributeValue);
  }

  public void setVisible(boolean visible) {
    display.setVisible(visible);
    if (!visible) {
      enabled = false;
    }
  }
}