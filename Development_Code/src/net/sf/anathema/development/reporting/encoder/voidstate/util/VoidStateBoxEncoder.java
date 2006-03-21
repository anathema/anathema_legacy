package net.sf.anathema.development.reporting.encoder.voidstate.util;

import java.awt.Rectangle;

import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;

import org.dom4j.Element;

public class VoidStateBoxEncoder extends AbstractJasperEncoder {

  public static final int TEXT_INSET = 5;
  private final int radius;
  private int titleHeight;

  public VoidStateBoxEncoder(int titleHeight) {
    this.titleHeight = titleHeight;
    this.radius = titleHeight / 2;
  }

  public Rectangle encodeWithTitle(Element bandElement, Rectangle bounds, String titleText) {
    Rectangle rectangle = encodeWithoutTitle(bandElement, bounds);
    encodeTitle(bandElement, bounds, titleText);
    return rectangle;
  }

  public Rectangle encodeWithoutTitle(Element bandElement, Rectangle bounds) {
    encodeBackground(bandElement, bounds);
    int textY = bounds.y + titleHeight;
    int textHeight = bounds.height - titleHeight - 2 * TEXT_INSET;
    return new Rectangle(bounds.x + TEXT_INSET, textY, bounds.width - 2 * TEXT_INSET, textHeight);
  }

  private void encodeTitle(Element bandElement, Rectangle bounds, String titleText) {
    encodeTitleBackground(bandElement, bounds.x, bounds.y, bounds.width);
    encodeTitleText(bandElement, bounds.x, bounds.y, bounds.width, titleText);
  }

  public Rectangle calculateBounds(int x, int y, int boxWidth, int contentHeight) {
    return new Rectangle(x, y, boxWidth, contentHeight + 2 * TEXT_INSET + titleHeight);
  }

  public Rectangle calculateCorrectedBounds(int x, int y, int boxWidth, int contentHeight) {
    return new Rectangle(x, y, boxWidth, contentHeight + TEXT_INSET + titleHeight);
  }

  private void encodeTitleText(Element bandElement, int x, int y, int width, String titleText) {
    Element reportElement = addTextElement(bandElement, titleText, 11, VALUE_CENTER, x, y, width, titleHeight);
    reportElement.addAttribute(ATTRIB_MODE, VALUE_TRANSPARENT);
    reportElement.addAttribute(ATTRIB_FORECOLOR, VALUE_COLOR_WHITE);
  }

  private void encodeBackground(Element bandElement, Rectangle rectangle) {
    Element rectangleElement = bandElement.addElement(TAG_RECTANGLE);
    rectangleElement.addAttribute(ATTRIB_RADIUS, String.valueOf(radius));
    Element reportElement = addReportElement(
        rectangleElement,
        rectangle.x,
        rectangle.y + radius,
        rectangle.width,
        rectangle.height - radius);
    reportElement.addAttribute(ATTRIB_BACKCOLOR, VALUE_COLOR_WHITE);
    reportElement.addAttribute(ATTRIB_FORECOLOR, VALUE_COLOR_BLACK);
  }

  private void encodeTitleBackground(Element bandElement, int x, int y, int width) {
    Element rectangleElement = bandElement.addElement(TAG_RECTANGLE);
    rectangleElement.addAttribute(ATTRIB_RADIUS, String.valueOf(radius));
    Element reportElement = addReportElement(rectangleElement, x, y, width, titleHeight);
    reportElement.addAttribute(ATTRIB_BACKCOLOR, VALUE_COLOR_BLACK);
    reportElement.addAttribute(ATTRIB_FORECOLOR, VALUE_COLOR_BLACK);
  }
}