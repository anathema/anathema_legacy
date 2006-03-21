package net.sf.anathema.development.reporting.encoder.voidstate.util;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;

import net.sf.anathema.development.reporting.encoder.page.IPageFormat;
import net.sf.anathema.development.reporting.util.ParameterUtilities;

import org.dom4j.Element;

public class SubreportUtilities {

  public static IPageFormat createPageFormat(final Rectangle rectangle) {
    return new IPageFormat() {

      public Insets getInsets() {
        return new Insets(0, 0, 0, 0);
      }

      public int getColumnWidth() {
        return rectangle.width;
      }

      public Dimension getPageSize() {
        return rectangle.getSize();
      }

      public int getPageHeight() {
        return rectangle.height;
      }
    };
  }

  private static final String TAG_SUBREPORT_PARAMETER = "subreportParameter";
  private static final String ATTRIB_SUBREPORT_PARAMETER_NAME = "name";
  private static final String TAG_SUBREPORT_PARAMETER_EXPRESSION = "subreportParameterExpression";

  public static void addSubreportParameter(Element subreportElement, String originalParameter, String subreportParameter) {
    addSubreportExpression(subreportElement, ParameterUtilities.parameterString(originalParameter), subreportParameter);
  }

  public static void addSubreportExpression(Element subreportElement, String expression, String subreportParameter) {
    Element element = subreportElement.addElement(TAG_SUBREPORT_PARAMETER);
    element.addAttribute(ATTRIB_SUBREPORT_PARAMETER_NAME, subreportParameter);
    Element expressionElement = element.addElement(TAG_SUBREPORT_PARAMETER_EXPRESSION);
    expressionElement.addCDATA(expression);
  }
}