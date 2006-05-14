package net.sf.anathema.development.reporting.encoder.voidstate.util;

import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;

import org.dom4j.Element;

public class HealthRectangleEncoder extends AbstractJasperEncoder implements IVoidStateFormatConstants {

  public void encodeHealthRect(Element parent, String foreColor, int x, int y, String printWhenExpression) {
    Element shapeElement = parent.addElement(TAG_RECTANGLE);
    Element reportElement = addReportElement(shapeElement, x, y, HEALTH_RECT_SIZE, HEALTH_RECT_SIZE);
    reportElement.addAttribute(ATTRIB_FORECOLOR, foreColor);
    reportElement.addAttribute(ATTRIB_BACKCOLOR, VALUE_COLOR_WHITE);
    reportElement.addAttribute(ATTRIB_POSITION_TYPE, VALUE_FIX_RELATIVE_TO_TOP);
    reportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression);
    addGraphicsElement(shapeElement, VALUE_1_POINT);
  }
}
