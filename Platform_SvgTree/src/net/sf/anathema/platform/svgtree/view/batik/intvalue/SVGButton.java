package net.sf.anathema.platform.svgtree.view.batik.intvalue;

import net.sf.anathema.platform.svgtree.document.svg.ISVGCascadeXMLConstants;

import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.util.SVGConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.svg.SVGGElement;

public class SVGButton {

  public final static int SHADOW_OFFSET = 1;
  private final static String BUTTON_DOWN_COLOR = ISVGCascadeXMLConstants.VALUE_COLOR_SVG_SILVER;
  private static final String BUTTON_UP_COLOR = ISVGCascadeXMLConstants.VALUE_COLOR_SVG_GAINSBORO;
  private final String text;
  private final double charmWidth;
  private final double rectHeight;
  private Element upperLeftShadow;
  private Element lowerRightShadow;
  private Element buttonRectangle;

  public SVGButton(double charmWidth, String text) {
    this.charmWidth = charmWidth;
    this.rectHeight = SVGIntValueDisplay.getDiameter(charmWidth) * 1.15;
    this.text = text;
  }

  public SVGGElement initGui(Document document) {
    SVGGElement buttonGroupElement = (SVGGElement) document.createElementNS(
        SVGDOMImplementation.SVG_NAMESPACE_URI,
        SVGConstants.SVG_G_TAG);
    buttonGroupElement.appendChild(createUpperLeftShadow(document));
    buttonGroupElement.appendChild(createLowerRightShadow(document));
    buttonGroupElement.appendChild(createButtonRectangle(document));
    buttonGroupElement.appendChild(createTextLabel(document));
    return buttonGroupElement;
  }

  private Element createTextLabel(Document document) {
    Element buttonTextElement = document.createElementNS(
        SVGDOMImplementation.SVG_NAMESPACE_URI,
        SVGConstants.SVG_TEXT_TAG);
    setAttribute(buttonTextElement, SVGConstants.SVG_X_ATTRIBUTE, this.charmWidth / 2);
    setAttribute(
        buttonTextElement,
        SVGConstants.SVG_Y_ATTRIBUTE,
        String.valueOf(SVGIntValueDisplay.getDiameter(charmWidth) * 0.9));
    setAttribute(buttonTextElement, SVGConstants.SVG_TEXT_ANCHOR_ATTRIBUTE, SVGConstants.SVG_MIDDLE_VALUE);
    Text label = document.createTextNode(this.text);
    buttonTextElement.appendChild(label);
    return buttonTextElement;
  }

  private Element createButtonRectangle(Document document) {
    this.buttonRectangle = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_RECT_TAG);
    setAttribute(buttonRectangle, SVGConstants.SVG_X_ATTRIBUTE, 0);
    setAttribute(buttonRectangle, SVGConstants.SVG_Y_ATTRIBUTE, 0);
    setAttribute(buttonRectangle, SVGConstants.SVG_WIDTH_ATTRIBUTE, charmWidth);
    setAttribute(buttonRectangle, SVGConstants.SVG_HEIGHT_ATTRIBUTE, rectHeight);
    setAttribute(buttonRectangle, SVGConstants.SVG_FILL_ATTRIBUTE, BUTTON_UP_COLOR);
    return buttonRectangle;
  }

  private Element createLowerRightShadow(Document document) {
    this.lowerRightShadow = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_RECT_TAG);
    setAttribute(lowerRightShadow, SVGConstants.SVG_X_ATTRIBUTE, SHADOW_OFFSET);
    setAttribute(lowerRightShadow, SVGConstants.SVG_Y_ATTRIBUTE, SHADOW_OFFSET);
    setAttribute(lowerRightShadow, SVGConstants.SVG_WIDTH_ATTRIBUTE, charmWidth);
    setAttribute(lowerRightShadow, SVGConstants.SVG_HEIGHT_ATTRIBUTE, rectHeight);
    final String points = "0," //$NON-NLS-1$
        + rectHeight
        + " 0,0 " //$NON-NLS-1$
        + charmWidth
        + ",0"; //$NON-NLS-1$
    setAttribute(lowerRightShadow, SVGConstants.SVG_POINTS_ATTRIBUTE, points);
    setAttribute(lowerRightShadow, SVGConstants.SVG_FILL_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_COLOR_SVG_NAVY);
    return lowerRightShadow;
  }

  private Element createUpperLeftShadow(Document document) {
    this.upperLeftShadow = document.createElementNS(SVGDOMImplementation.SVG_NAMESPACE_URI, SVGConstants.SVG_RECT_TAG);
    setAttribute(upperLeftShadow, SVGConstants.SVG_X_ATTRIBUTE, -SHADOW_OFFSET);
    setAttribute(upperLeftShadow, SVGConstants.SVG_Y_ATTRIBUTE, -SHADOW_OFFSET);
    setAttribute(upperLeftShadow, SVGConstants.SVG_WIDTH_ATTRIBUTE, charmWidth);
    setAttribute(upperLeftShadow, SVGConstants.SVG_HEIGHT_ATTRIBUTE, rectHeight);
    final String points = "0," //$NON-NLS-1$
        + rectHeight
        + " 0,0 " //$NON-NLS-1$
        + charmWidth
        + ",0"; //$NON-NLS-1$
    setAttribute(upperLeftShadow, SVGConstants.SVG_POINTS_ATTRIBUTE, points);
    setAttribute(upperLeftShadow, SVGConstants.SVG_FILL_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_COLOR_SVG_WHITE);
    return upperLeftShadow;
  }

  private void setAttribute(org.w3c.dom.Element element, String attributeName, String attributeValue) {
    element.setAttributeNS(null, attributeName, attributeValue);
  }

  private void setAttribute(org.w3c.dom.Element element, String attributeName, double attributeValue) {
    element.setAttributeNS(null, attributeName, String.valueOf(attributeValue));
  }

  public void setSelected(boolean selected) {
    if (selected) {
      setAttribute(upperLeftShadow, SVGConstants.SVG_FILL_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_COLOR_SVG_NAVY);
      setAttribute(lowerRightShadow, SVGConstants.SVG_FILL_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_COLOR_SVG_WHITE);
      setAttribute(buttonRectangle, SVGConstants.SVG_FILL_ATTRIBUTE, BUTTON_DOWN_COLOR);
    }
    else {
      setAttribute(upperLeftShadow, SVGConstants.SVG_FILL_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_COLOR_SVG_WHITE);
      setAttribute(lowerRightShadow, SVGConstants.SVG_FILL_ATTRIBUTE, ISVGCascadeXMLConstants.VALUE_COLOR_SVG_NAVY);
      setAttribute(buttonRectangle, SVGConstants.SVG_FILL_ATTRIBUTE, BUTTON_UP_COLOR);
    }
  }
}