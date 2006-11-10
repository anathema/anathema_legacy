package net.sf.anathema.charmtree.batik.intvalue;

import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_COLOR_LIGHT_MEDIUM_GRAY;
import static net.sf.anathema.charmtree.provider.svg.ISVGCascadeXMLConstants.VALUE_COLOR_SVG_BLACK;

import java.awt.Color;
import java.awt.Rectangle;

import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.charmtree.batik.IBoundsCalculator;
import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.intvalue.IntValueControl;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.dom.svg.SVGOMDocument;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.svg.SVGCircleElement;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGLocatable;
import org.w3c.dom.svg.SVGRectElement;

public class SVGIntValueDisplay implements IIntValueView {

  private final EventListener selectionListener = new EventListener() {
    public void handleEvent(Event evt) {
      if (selectionRectangle != null) {
        removeSelectionRectangle();
        if (evt instanceof MouseEvent) {
          selectCircles(((MouseEvent) evt).getClientX());
        }
      }
    }
  };

  private final EventListener rectangleChangeListener = new EventListener() {
    public void handleEvent(Event evt) {
      if (evt instanceof MouseEvent && selectionRectangle != null) {
        MouseEvent mouseEvent = (MouseEvent) evt;
        int clientX = mouseEvent.getClientX();
        setSelectionRectangleWidth(clientX);
        selectCircles(clientX);
      }
    }
  };

  private final EventListener displayRectangleListener = new EventListener() {
    public void handleEvent(Event evt) {
      if (visible && evt instanceof MouseEvent) {
        MouseEvent mouseEvent = (MouseEvent) evt;
        if (selectionRectangle == null) {
          selectionRectangle = (SVGRectElement) document.createElementNS(
              SVGDOMImplementation.SVG_NAMESPACE_URI,
              SVGConstants.SVG_RECT_TAG);
          selectionRectangle.setAttributeNS(null, SVGConstants.SVG_X_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
          selectionRectangle.setAttributeNS(null, SVGConstants.SVG_Y_ATTRIBUTE, SVGConstants.SVG_ONE_VALUE);
          setAttribute(selectionRectangle, SVGConstants.SVG_WIDTH_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
          selectionRectangle.setAttributeNS(null, SVGConstants.SVG_HEIGHT_ATTRIBUTE, "22"); //$NON-NLS-1$
          selectionRectangle.setAttributeNS(null, SVGConstants.SVG_STROKE_ATTRIBUTE, VALUE_COLOR_SVG_BLACK);
          selectionRectangle.setAttributeNS(null, SVGConstants.SVG_FILL_ATTRIBUTE, VALUE_COLOR_LIGHT_MEDIUM_GRAY);
          selectionRectangle.setAttributeNS(
              null,
              SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE,
              String.valueOf((float) 120 / 255));
          groupElement.appendChild(selectionRectangle);
        }
        setSelectionRectangleWidth(mouseEvent.getClientX());
      }
    }
  };

  public static double getDiameter(double charmWidth) {
    return charmWidth / 10;
  }

  private final double radius;
  private final double gap;

  private final double maximumWidth;
  private final IntValueControl valueControl = new IntValueControl();
  private IBoundsCalculator boundsCalculator;
  private final int dotCount;
  private final SVGCircleElement[] circles;
  private SVGRectElement selectionRectangle;
  private final String fillColorString;
  private final String fillOpacityString;
  private Element groupElement;
  private SVGRectElement glassPane;
  private int value;
  private boolean visible = false;

  private SVGOMDocument document;

  public SVGIntValueDisplay(int maxValue, Color fillColor, int initialValue, double diameter) {
    this.dotCount = maxValue;
    this.value = initialValue;
    this.radius = diameter / 2;
    this.gap = diameter / 10;
    this.maximumWidth = EssenceTemplate.SYSTEM_ESSENCE_MAX * (diameter + gap);
    this.circles = new SVGCircleElement[dotCount];
    this.fillColorString = "rgb(" + fillColor.getRed() + "," //$NON-NLS-1$ //$NON-NLS-2$
        + fillColor.getGreen()
        + "," //$NON-NLS-1$
        + fillColor.getBlue()
        + ")"; //$NON-NLS-1$
    this.fillOpacityString = String.valueOf((float) fillColor.getAlpha() / 255);
  }

  public Element initGui(SVGOMDocument svgDocument, IBoundsCalculator calculator) {
    this.boundsCalculator = calculator;
    this.document = svgDocument;
    this.groupElement = svgDocument.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_G_TAG);
    createCircles();
    for (Element circle : circles) {
      groupElement.appendChild(circle);
    }
    this.glassPane = createGlassPane();
    groupElement.appendChild(glassPane);
    setVisible(true);
    setValue(value);
    return groupElement;
  }

  private void setSelectionRectangleWidth(float clientX) {
    Rectangle groupBounds = boundsCalculator.getBounds((SVGLocatable) groupElement);
    float width = selectionRectangle.getScreenCTM().inverse().getA() * (clientX - groupBounds.x);
    double boundedWidth = Math.max(0, Math.min(maximumWidth, width));
    setAttribute(selectionRectangle, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(boundedWidth));
  }

  private void selectCircles(int xPosition) {
    Rectangle[] bounds = new Rectangle[dotCount];
    for (int index = 0; index < circles.length; index++) {
      bounds[index] = boundsCalculator.getBounds(circles[index]);
    }
    if (xPosition - bounds[0].x <= circles[0].getScreenCTM().getA() * radius) {
      fireValueChangedEvent(0);
      return;
    }
    if (xPosition > bounds[dotCount - 1].x) {
      fireValueChangedEvent(circles.length);
      return;
    }
    for (int index = 0; index < circles.length; index++) {
      if (xPosition < bounds[index].x) {
        fireValueChangedEvent(index);
        return;
      }
    }
    return;
  }

  private SVGRectElement createGlassPane() {
    SVGRectElement rectangle = (SVGRectElement) document.createElementNS(
        SVGDOMImplementation.SVG_NAMESPACE_URI,
        SVGConstants.SVG_RECT_TAG);
    document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_RECT_TAG);
    setAttribute(rectangle, SVGConstants.SVG_X_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(rectangle, SVGConstants.SVG_Y_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(rectangle, SVGConstants.SVG_WIDTH_ATTRIBUTE, String.valueOf(maximumWidth));
    setAttribute(rectangle, SVGConstants.SVG_HEIGHT_ATTRIBUTE, String.valueOf(2 * (radius + gap)));
    setAttribute(rectangle, SVGConstants.SVG_FILL_ATTRIBUTE, VALUE_COLOR_SVG_BLACK);
    setAttribute(rectangle, SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    return rectangle;
  }

  private void setAttribute(Element element, String attributeName, String attributeValue) {
    element.setAttributeNS(null, attributeName, attributeValue);
  }

  private void createCircles() {
    for (int index = 0; index < dotCount; index++) {
      double xCoordinate = radius + gap + index * (2 * radius + gap);
      circles[index] = createCircleElement(document, xCoordinate);
    }
  }

  private SVGCircleElement createCircleElement(SVGDocument svgDocument, double xCoordinate) {
    org.w3c.dom.Element circle = svgDocument.createElementNS(
        SVGDOMImplementation.SVG_NAMESPACE_URI,
        SVGConstants.SVG_CIRCLE_TAG);
    setAttribute(circle, SVGConstants.SVG_R_ATTRIBUTE, String.valueOf(radius));
    setAttribute(circle, SVGConstants.SVG_CX_ATTRIBUTE, String.valueOf(xCoordinate));
    setAttribute(circle, SVGConstants.SVG_CY_ATTRIBUTE, String.valueOf(radius + gap));
    setAttribute(circle, SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
    setAttribute(circle, SVGConstants.SVG_FILL_ATTRIBUTE, fillColorString);
    setAttribute(circle, SVGConstants.SVG_STROKE_ATTRIBUTE, VALUE_COLOR_SVG_BLACK);
    return (SVGCircleElement) circle;
  }

  public void setValue(int value) {
    this.value = value;
    if (visible) {
      for (int imageIndex = 0; imageIndex < dotCount; imageIndex++) {
        setAttribute(circles[imageIndex], SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE, fillOpacityString);
      }
      for (int imageIndex = value; imageIndex < dotCount; imageIndex++) {
        setAttribute(circles[imageIndex], SVGConstants.SVG_FILL_OPACITY_ATTRIBUTE, SVGConstants.SVG_ZERO_VALUE);
      }
    }
    fireValueChangedEvent(value);
  }

  public void addIntValueChangedListener(IIntValueChangedListener listener) {
    valueControl.addIntValueChangeListener(listener);
  }

  public void removeIntValueChangedListener(IIntValueChangedListener listener) {
    valueControl.removeIntValueChangeListener(listener);
  }

  private void fireValueChangedEvent(int newValue) {
    valueControl.fireValueChangedEvent(newValue);
  }

  private void removeSelectionRectangle() {
    groupElement.removeChild(selectionRectangle);
    selectionRectangle = null;
  }

  public void setMaximum(int maximalValue) {
    // nothing to do
  }

  public void setVisible(boolean visible) {
    this.visible = visible;
    if (visible) {
      setValue(value);
      startListening();
    }
  }

  private void startListening() {
    glassPane.addEventListener("mousedown", displayRectangleListener, false); //$NON-NLS-1$
    document.addEventListener("mousemove", rectangleChangeListener, false); //$NON-NLS-1$
    document.addEventListener("mouseup", selectionListener, false); //$NON-NLS-1$
  }
}