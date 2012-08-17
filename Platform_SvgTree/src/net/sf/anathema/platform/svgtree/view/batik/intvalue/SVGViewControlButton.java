package net.sf.anathema.platform.svgtree.view.batik.intvalue;

import net.sf.anathema.platform.svgtree.presenter.view.ISVGSpecialNodeView;
import net.sf.anathema.platform.svgtree.presenter.view.SpecialCharmContainer;
import net.sf.anathema.platform.svgtree.view.batik.IBoundsCalculator;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.svg.SVGGElement;
import org.w3c.dom.svg.SVGSVGElement;

import java.awt.Rectangle;

import static java.text.MessageFormat.format;
import static net.sf.anathema.platform.svgtree.view.batik.intvalue.SVGButton.SHADOW_OFFSET;
import static org.apache.batik.dom.svg.SVGDOMImplementation.SVG_NAMESPACE_URI;
import static org.apache.batik.util.SVGConstants.SVG_G_TAG;
import static org.apache.batik.util.SVGConstants.SVG_MOUSEOUT_EVENT_TYPE;
import static org.apache.batik.util.SVGConstants.SVG_MOUSEUP_EVENT_TYPE;
import static org.apache.batik.util.SVGConstants.SVG_TRANSFORM_ATTRIBUTE;

public class SVGViewControlButton implements ISVGSpecialNodeView {

  private static final String VALUE_INHERIT = "inherit"; //$NON-NLS-1$
  private static final String VALUE_NONE = "none"; //$NON-NLS-1$
  private static final String ATTRIB_DISPLAY = "display"; //$NON-NLS-1$
  private final ISVGSpecialNodeView display;
  private final double nodeWidth;
  private final SVGButton button;
  private boolean enabled = false;
  private IBoundsCalculator calculator;
  private SVGGElement displayElement;
  private SVGGElement outerGroupElement;
  private SVGSVGElement rootElement;

  public SVGViewControlButton(ISVGSpecialNodeView display, double nodeWidth, String label) {
    this.display = display;
    this.nodeWidth = nodeWidth;
    this.button = new SVGButton(nodeWidth, label);
  }

  @Override
  public String getNodeId() {
    return display.getNodeId();
  }

  @Override
  public void showIn(SpecialCharmContainer container) {
    //nothing to do
  }

  @Override
  public SVGGElement initGui(SVGOMDocument svgDocument, IBoundsCalculator boundsCalculator) {
    this.calculator = boundsCalculator;
    this.outerGroupElement = (SVGGElement) svgDocument.createElementNS(SVG_NAMESPACE_URI, SVG_G_TAG);
    SVGGElement buttonGroup = button.initGui(svgDocument);
    outerGroupElement.appendChild(buttonGroup);
    this.displayElement = display.initGui(svgDocument, calculator);
    setAttribute(displayElement, SVG_TRANSFORM_ATTRIBUTE,
            format("translate(0,{0}{1})", SVGIntValueDisplay.getDiameter(nodeWidth) * 1.15, SHADOW_OFFSET));
    outerGroupElement.appendChild(displayElement);
    this.rootElement = svgDocument.getRootElement();
    EventListener removeListener = createRemoveListener(buttonGroup);
    buttonGroup.addEventListener(SVG_MOUSEUP_EVENT_TYPE, createDisplayListener(), false);
    buttonGroup.addEventListener(SVG_MOUSEOUT_EVENT_TYPE, removeListener, false);
    displayElement.addEventListener(SVG_MOUSEOUT_EVENT_TYPE, removeListener, false);
    setDisplayVisible(false);
    return outerGroupElement;
  }

  private EventListener createRemoveListener(final SVGGElement buttonGroup) {
    return new EventListener() {
      @Override
      public void handleEvent(Event evt) {
        if (!enabled) {
          return;
        }
        MouseEvent event = (MouseEvent) evt;
        Rectangle bounds = calculator.getBounds(displayElement);
        boolean outOfDisplay = !bounds.contains(event.getClientX(), event.getClientY());
        boolean outOfButton = !calculator.getBounds(buttonGroup).contains(event.getClientX(), event.getClientY());
        if (outOfDisplay && outOfButton) {
          removeFromView();
          evt.stopPropagation();
        }
      }
    };
  }

  private EventListener createDisplayListener() {
    return new EventListener() {
      @Override
      public void handleEvent(Event evt) {
        if (!(evt instanceof MouseEvent && ((MouseEvent) evt).getButton() == 0)) {
          return;
        }
        if (enabled) {
          removeFromView();
        } else {
          addToView();
        }
      }
    };
  }

  private void addToView() {
    NodeList childNodes = rootElement.getChildNodes();
    for (int index = 0; index < childNodes.getLength(); index++) {
      if (childNodes.item(index) instanceof Element) {
        setAttribute((Element) childNodes.item(index), SVGConstants.SVG_OPACITY_ATTRIBUTE, "0.1"); //$NON-NLS-1$
      }
    }
    setAttribute(outerGroupElement, SVGConstants.SVG_OPACITY_ATTRIBUTE, SVGConstants.SVG_OPAQUE_VALUE);
    setDisplayVisible(true);
    setEnabled(true);
  }

  private void setEnabled(boolean enabled) {
    this.enabled = enabled;
    button.setSelected(enabled);
  }

  private void removeFromView() {
    setDisplayVisible(false);
    NodeList childNodes = rootElement.getChildNodes();
    for (int index = 0; index < childNodes.getLength(); index++) {
      if (childNodes.item(index) instanceof Element) {
        setAttribute((Element) childNodes.item(index), SVGConstants.SVG_OPACITY_ATTRIBUTE,
                SVGConstants.SVG_OPAQUE_VALUE);
      }
    }
    setEnabled(false);
  }

  private void setAttribute(org.w3c.dom.Element element, String attributeName, String attributeValue) {
    element.setAttributeNS(null, attributeName, attributeValue);
  }

  @Override
  public void hide() {
    setDisplayVisible(false);
    enabled = false;
  }

  private void setDisplayVisible(boolean visible) {
    if (displayElement != null) {
      if (visible) {
        setAttribute(displayElement, ATTRIB_DISPLAY, VALUE_INHERIT);
      } else {
        setAttribute(displayElement, ATTRIB_DISPLAY, VALUE_NONE);
      }
    }
  }

  @Override
  public void reset() {
    if (enabled) {
      removeFromView();
    }
  }
}