package net.sf.anathema.platform.svgtree.view.batik.intvalue;

import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.svg.SVGGElement;
import org.w3c.dom.svg.SVGLocatable;
import org.w3c.dom.svg.SVGSVGElement;

public class SVGViewControlButton implements ISVGSpecialNodeView {

  private final ISVGSpecialNodeView display;
  private final double nodeWidth;
  private final SVGButton button;
  private boolean enabled = false;
  private Element displayElement;
  private Element outerGroupElement;
  private SVGSVGElement rootElement;

  public SVGViewControlButton(final ISVGSpecialNodeView display, final double nodeWidth, final String label) {
    this.display = display;
    this.nodeWidth = nodeWidth;
    this.button = new SVGButton(nodeWidth, label);
  }

  public String getNodeId() {
    return display.getNodeId();
  }

  public Element initGui(final SVGOMDocument svgDocument, final IBoundsCalculator boundsCalculator) {
    this.outerGroupElement = svgDocument.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_G_TAG);
    final SVGGElement buttonGroup = button.initGui(svgDocument);
    outerGroupElement.appendChild(buttonGroup);
    this.displayElement = display.initGui(svgDocument, boundsCalculator);
    setAttribute(
        displayElement,
        SVGConstants.SVG_TRANSFORM_ATTRIBUTE,
        "translate(0," + SVGIntValueDisplay.getDiameter(nodeWidth) * 1.15 + SVGButton.SHADOW_OFFSET + ")"); //$NON-NLS-1$ //$NON-NLS-2$
    this.rootElement = svgDocument.getRootElement();
    buttonGroup.addEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, createDisplayListener(), false);
    svgDocument.addEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, createRemoveListener(boundsCalculator), true);
    display.setVisible(false);
    return outerGroupElement;
  }

  private EventListener createRemoveListener(final IBoundsCalculator boundsCalculator) {
    return new EventListener() {
      public void handleEvent(final Event evt) {
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
      public void handleEvent(final Event evt) {
        if (!(evt instanceof MouseEvent && ((MouseEvent) evt).getButton() == 0)) {
          return;
        }
        if (enabled) {
          removeFromView();
        }
        else {
          addToView();
        }
      }
    };
  }

  private void addToView() {
    outerGroupElement.appendChild(displayElement);
    NodeList childNodes = rootElement.getChildNodes();
    for (int index = 0; index < childNodes.getLength(); index++) {
      if (childNodes.item(index) instanceof Element) {
        setAttribute((Element) childNodes.item(index), SVGConstants.SVG_OPACITY_ATTRIBUTE, "0.1"); //$NON-NLS-1$
      }
    }
    setAttribute(outerGroupElement, SVGConstants.SVG_OPACITY_ATTRIBUTE, SVGConstants.SVG_OPAQUE_VALUE);
    display.setVisible(true);
    setEnabled(true);
  }

  private void setEnabled(final boolean enabled) {
    this.enabled = enabled;
    button.setSelected(enabled);
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
    setEnabled(false);
  }

  private void setAttribute(final org.w3c.dom.Element element, final String attributeName, final String attributeValue) {
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