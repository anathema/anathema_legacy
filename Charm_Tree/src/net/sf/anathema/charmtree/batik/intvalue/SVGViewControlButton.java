package net.sf.anathema.charmtree.batik.intvalue;

import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_COLOR_SVG_BLACK;

import net.sf.anathema.charmtree.batik.IBoundsCalculator;
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

  private final ISVGSpecialCharmView display;
  private final double charmWidth;
  private boolean enabled = false;
  private Element displayElement;
  private Element outerGroupElement;
  private SVGSVGElement rootElement;
  private SVGButton button;

  public SVGViewControlButton(ISVGSpecialCharmView display, double charmWidth, String label) {
    this.display = display;
    this.charmWidth = charmWidth;
    this.button = new SVGButton(charmWidth, label);
  }

  public String getCharmId() {
    return display.getCharmId();
  }

  public Element initGui(SVGOMDocument svgDocument, IBoundsCalculator boundsCalculator) {
    this.outerGroupElement = svgDocument.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_G_TAG);
    final SVGGElement buttonGroup = button.initGui(svgDocument);
    outerGroupElement.appendChild(buttonGroup);
    this.displayElement = display.initGui(svgDocument, boundsCalculator);
    setAttribute(
        displayElement,
        SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
        "translate(0," + SVGIntValueDisplay.getDiameter(charmWidth) * 1.15 + SVGButton.SHADOW_OFFSET + ")"); //$NON-NLS-1$ //$NON-NLS-2$
    this.rootElement = svgDocument.getRootElement();
    buttonGroup.addEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, createDisplayListener(), false);
    svgDocument.addEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, createRemoveListener(boundsCalculator), true);
    display.setVisible(false);
    return outerGroupElement;
  }

  private EventListener createRemoveListener(final IBoundsCalculator boundsCalculator) {
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
          removeFromView();
          evt.stopPropagation();
        }
      }
    };
  }

  private EventListener createDisplayListener() {
    return new EventListener() {
      public void handleEvent(Event evt) {
        if (!(evt instanceof MouseEvent && ((MouseEvent) evt).getButton() == 0)) {
          return;
        }
        if (enabled) {
          removeFromView();
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
          button.setSelected(true);
        }
      }
    };
  }

  private void removeFromView() {
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
    button.setSelected(false);
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
    Element rectangle = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_RECT_TAG);
    setAttribute(rectangle, SVGConstants.SVG_X_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(rectangle, SVGConstants.SVG_Y_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(rectangle, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(charmWidth));
    setAttribute(
        rectangle,
        SVGConstants.SVG_HEIGHT_ATTRIBUTE,
        String.valueOf(SVGIntValueDisplay.getDiameter(charmWidth) * 1.15));
    setAttribute(rectangle, SVGConstants.SVG_STROKE_ATTRIBUTE, VALUE_COLOR_SVG_BLACK);
    setAttribute(rectangle, SVGConstants.SVG_FILL_ATTRIBUTE, VALUE_COLOR_SVG_BLACK);
    setAttribute(rectangle, SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
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

  public void reset() {
    if (enabled) {
      removeFromView();
    }
  }
}