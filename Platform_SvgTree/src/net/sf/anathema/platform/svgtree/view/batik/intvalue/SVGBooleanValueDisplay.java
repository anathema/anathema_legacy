package net.sf.anathema.platform.svgtree.view.batik.intvalue;

import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.workflow.booleanvalue.IBooleanValueView;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.util.SVGConstants;
import org.jmock.example.announcer.Announcer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGGElement;

import java.awt.Color;

import static net.sf.anathema.platform.svgtree.document.components.ISVGCascadeXMLConstants.VALUE_COLOR_SVG_BLACK;

public class SVGBooleanValueDisplay implements IBooleanValueView {

  private boolean visible;
  private boolean selected = false;
  private final String label;
  private final double width;
  private final Announcer<IBooleanValueChangedListener> control = Announcer.to(IBooleanValueChangedListener.class);
  private String fillColorString;
  private String fillOpacityString;
  private Element bodyNode;

  public SVGBooleanValueDisplay(String label, double width, Color color) {
    this.label = label;
    this.width = width;
    this.fillColorString = "rgb(" + color.getRed() + "," //$NON-NLS-1$ //$NON-NLS-2$
        + color.getGreen()
        + "," //$NON-NLS-1$
        + color.getBlue()
        + ")"; //$NON-NLS-1$
    this.fillOpacityString = String.valueOf((float) color.getAlpha() / 255);
  }

  public Element initGui(SVGOMDocument document) {
    SVGGElement groupElement = (SVGGElement) document.createElementNS(
        SVGDOMImplementation.SVG_NAMESPACE_URI,
        SVGConstants.SVG_G_TAG);
    this.bodyNode = createBody(document);
    groupElement.appendChild(bodyNode);
    groupElement.appendChild(createTextLabel(document));
    groupElement.addEventListener(SVGConstants.SVG_MOUSEUP_EVENT_TYPE, createEventListener(), false);
    setVisible(true);
    return groupElement;
  }

  private EventListener createEventListener() {
    return new EventListener() {
      @Override
      public void handleEvent(Event evt) {
        if (!(evt instanceof MouseEvent && ((MouseEvent) evt).getButton() == 0)) {
          return;
        }
        control.announce().valueChanged(!selected);
      }
    };
  }

  private Element createBody(SVGDocument document) {
    Element rectangle = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_RECT_TAG);
    DomUtilities.setAttribute(rectangle, SVGConstants.SVG_X_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    DomUtilities.setAttribute(rectangle, SVGConstants.SVG_Y_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    DomUtilities.setAttribute(rectangle, SVGConstants.SVG_WIDTH_ATTRIBUTE, width);
    DomUtilities.setAttribute(
        rectangle,
        SVGConstants.SVG_HEIGHT_ATTRIBUTE,
        SVGIntValueDisplay.getDiameter(width) * 1.15);
    DomUtilities.setAttribute(rectangle, SVGConstants.SVG_STROKE_ATTRIBUTE, VALUE_COLOR_SVG_BLACK);
    DomUtilities.setAttribute(rectangle, SVGConstants.SVG_FILL_ATTRIBUTE, fillColorString);
    DomUtilities.setAttribute(rectangle, SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    return rectangle;
  }

  private Element createTextLabel(Document document) {
    Element buttonTextElement = document.createElementNS(
        SVGDOMImplementation.SVG_NAMESPACE_URI,
        SVGConstants.SVG_TEXT_TAG);
    DomUtilities.setAttribute(buttonTextElement, SVGConstants.SVG_X_ATTRIBUTE, this.width / 2);
    DomUtilities.setAttribute(
        buttonTextElement,
        SVGConstants.SVG_Y_ATTRIBUTE,
        SVGIntValueDisplay.getDiameter(width) * 0.9);
    DomUtilities.setAttribute(buttonTextElement, SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, SVGConstants.SVG_MIDDLE_VALUE);
    Text text = document.createTextNode(this.label);
    buttonTextElement.appendChild(text);
    return buttonTextElement;
  }

  @Override
  public void addChangeListener(IBooleanValueChangedListener listener) {
    control.addListener(listener);
  }

  @Override
  public void setSelected(boolean selected) {
    this.selected = selected;
    if (visible) {
      if (selected) {
        DomUtilities.setAttribute(bodyNode, SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE, fillOpacityString);
      }
      else {
        DomUtilities.setAttribute(bodyNode, SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
      }
    }
    control.announce().valueChanged(selected);
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
    if (visible) {
      setSelected(selected);
    }
  }
}