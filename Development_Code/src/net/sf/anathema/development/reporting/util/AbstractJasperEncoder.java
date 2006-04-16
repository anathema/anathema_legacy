package net.sf.anathema.development.reporting.util;

import java.awt.Color;
import java.awt.Rectangle;
import java.net.URL;
import java.util.Map;

import net.sf.anathema.development.reporting.encoder.voidstate.util.SubreportUtilities;
import net.sf.anathema.framework.reporting.encoding.TextEncoding;
import net.sf.anathema.framework.reporting.jasper.IJasperXmlConstants;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.lib.awt.ColorUtilities;
import net.sf.jasperreports.engine.JasperReport;

import org.dom4j.Element;

public class AbstractJasperEncoder implements IJasperXmlConstants {

  protected final Element addTextElement(
      Element parent,
      String stringExpression,
      int fontSize,
      String textAlignment,
      int x,
      int y,
      int width,
      int height) {
    return addTextContent(
        TextEncoding.addTextFieldElement(parent),
        stringExpression,
        fontSize,
        textAlignment,
        new Rectangle(x, y, width, height));
  }

  protected final Element addTextElement(
      Element parent,
      String stringExpression,
      int fontSize,
      String textAlignment,
      Rectangle bounds) {
    return addTextContent(TextEncoding.addTextFieldElement(parent), stringExpression, fontSize, textAlignment, bounds);
  }

  protected final Element addStyledTextElement(
      Element parent,
      ITextPart[] textParts,
      int fontSize,
      String textAlignment,
      Rectangle bounds) {
    return addStyledTextContent(TextEncoding.addTextFieldElement(parent), textParts, fontSize, textAlignment, bounds);
  }

  protected final Element addStyledTextElement(
      Element parent,
      ITextPart[] textParts,
      int fontSize,
      String textAlignment,
      int x,
      int y,
      int width,
      int height) {
    Rectangle bounds = new Rectangle(x, y, width, height);
    return addStyledTextContent(TextEncoding.addTextFieldElement(parent), textParts, fontSize, textAlignment, bounds);
  }

  protected final Element addStyledTextElement(
      Element parent,
      String styledCData,
      int fontSize,
      String textAlignment,
      Rectangle bounds) {
    return addStyledTextContent(TextEncoding.addTextFieldElement(parent), styledCData, fontSize, textAlignment, bounds);
  }

  protected final Element addStyledTextToTextFieldElement(
      Element textFieldElement,
      ITextPart[] textParts,
      int fontSize,
      String textAlignment,
      Rectangle bounds) {
    return addStyledTextContent(textFieldElement, textParts, fontSize, textAlignment, bounds);
  }

  protected final Element addStyledTextContent(
      Element textField,
      ITextPart[] textParts,
      int fontSize,
      String textAlignment,
      Rectangle bounds) {
    String styledCData = TextEncoding.createQuotedStyledCData(textParts);
    return addStyledTextContent(textField, styledCData, fontSize, textAlignment, bounds);
  }

  protected final Element addStyledTextContent(
      Element textField,
      String styledCData,
      int fontSize,
      String textAlignment,
      Rectangle bounds) {
    Element reportElement = addReportElement(textField, bounds);
    TextEncoding.addHorizontalTextElement(textField, fontSize, textAlignment, true);
    addStyleText(textField, styledCData);
    return reportElement;
  }

  protected final void addStyleText(Element textField, String styledCData) {
    Element textFieldExpression = textField.addElement(IJasperXmlConstants.TAG_TEXT_FIELD_EXPRESSION);
    textFieldExpression.addAttribute(IJasperXmlConstants.ATTRIB_CLASS, IJasperXmlConstants.VALUE_CLASS_STRING);
    textFieldExpression.addCDATA(styledCData);
  }

  protected final Element addTextContent(
      Element textField,
      String stringExpression,
      int fontSize,
      String textAlignment,
      Rectangle bounds) {
    Element reportElement = addReportElement(textField, bounds);
    TextEncoding.addHorizontalTextElement(textField, fontSize, textAlignment, false);
    TextEncoding.addTextFieldExpression(textField, stringExpression);
    return reportElement;
  }

  protected final Element addThinLineElement(Element parent, Rectangle bounds) {
    return addLineElement(parent, bounds, VALUE_PEN_THIN);
  }

  protected final Element addNormalLineElement(Element parent, Rectangle bounds) {
    return addLineElement(parent, bounds, VALUE_1_POINT);
  }

  protected final Element addLineElement(Element parent, Rectangle bounds, String penWidth) {
    return addLineElement(parent, bounds, penWidth, false);
  }

  protected final Element addLineElement(Element parent, Rectangle bounds, String penWidth, boolean drawBottomUp) {
    return addLineElement(parent, bounds, penWidth, drawBottomUp, null);
  }

  protected final Element addLineElement(
      Element parent,
      Rectangle bounds,
      String penWidth,
      boolean drawBottomUp,
      Color color) {
    Element line = parent.addElement(TAG_LINE);
    if (drawBottomUp) {
      line.addAttribute(ATTRIB_DIRECTION, "BottomUp");
    }
    Element reportElement = addReportElement(line, bounds);
    if (color != null) {
      reportElement.addAttribute(ATTRIB_FORECOLOR, ColorUtilities.convertColorToHexString(color));
    }
    addGraphicsElement(line, penWidth);
    return reportElement;
  }

  protected final Element addImageElement(Element parent, int x, int y, int width, int height, String imageURL) {
    Element imageElement = parent.addElement(TAG_IMAGE);
    addReportElement(imageElement, x, y, width, height);
    Element imageExpression = imageElement.addElement(TAG_IMAGE_EXPRESSION);
    imageExpression.addAttribute(ATTRIB_CLASS, VALUE_CLASS_URL);
    imageExpression.addCDATA(imageURL);
    return imageElement;
  }

  protected final Element addReportElement(Element element, int x, int y, int width, int height) {
    Element reportElement = element.addElement(TAG_REPORT_ELEMENT);
    reportElement.addAttribute(ATTRIB_X, String.valueOf(x));
    reportElement.addAttribute(ATTRIB_Y, String.valueOf(y));
    reportElement.addAttribute(ATTRIB_HEIGHT, String.valueOf(height));
    reportElement.addAttribute(ATTRIB_WIDTH, String.valueOf(width));
    return reportElement;
  }

  protected final Element addReportElement(Element element, Rectangle bounds) {
    Element reportElement = element.addElement(TAG_REPORT_ELEMENT);
    reportElement.addAttribute(ATTRIB_X, String.valueOf(bounds.x));
    reportElement.addAttribute(ATTRIB_Y, String.valueOf(bounds.y));
    reportElement.addAttribute(ATTRIB_HEIGHT, String.valueOf(bounds.height));
    reportElement.addAttribute(ATTRIB_WIDTH, String.valueOf(bounds.width));
    return reportElement;
  }

  public static final String quotify(String value) {
    return "\"" + value + "\""; //$NON-NLS-1$ //$NON-NLS-2$
  }

  protected void addHyperlink(Element parent, URL hyperlinkURL) {
    parent.addAttribute(ATTRIB_HYPERLINK_TYPE, "Reference"); //$NON-NLS-1$
    parent.addAttribute(ATTRIB_HYPERLINK_TARGET, "Blank"); //$NON-NLS-1$
    Element hyperlinkElement = parent.addElement(TAG_HYPERLINK_REFERENCE_EXPRESSION);
    hyperlinkElement.addCDATA(quotify(hyperlinkURL.toExternalForm()));
  }

  protected final void addGraphicsElement(Element parent, String penSize) {
    Element graphicsElement = parent.addElement(TAG_GRAPHIC_ELEMENT);
    graphicsElement.addAttribute(ATTRIB_PEN, penSize);
    graphicsElement.addAttribute(ATTRIB_FILL, VALUE_SOLID);
  }

  protected void addShape(
      Element parent,
      String shape,
      String backColor,
      int x,
      int y,
      int size,
      String printWhenExpression) {
    addShape(parent, shape, backColor, x, y, size, size, printWhenExpression);
  }

  protected void addShape(
      Element parent,
      String shape,
      String backColor,
      int x,
      int y,
      int width,
      int height,
      String printWhenExpression) {
    Element shapeElement = parent.addElement(shape);
    Element reportElement = addReportElement(shapeElement, x, y, width, height);
    reportElement.addAttribute(ATTRIB_FORECOLOR, IJasperXmlConstants.VALUE_COLOR_BLACK);
    reportElement.addAttribute(ATTRIB_BACKCOLOR, backColor);
    reportElement.addAttribute(ATTRIB_POSITION_TYPE, VALUE_FIX_RELATIVE_TO_TOP);
    reportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression);
    addGraphicsElement(shapeElement, VALUE_1_POINT);
  }

  protected final int addRectangleRow(Element parent, int x, int y, int count, int squareSize, int spacing) {
    for (int i = 0; i < count; i++) {
      addShape(parent, TAG_RECTANGLE, VALUE_COLOR_WHITE, x + i * (spacing + squareSize), y, squareSize, "true"); //$NON-NLS-1$
    }
    return squareSize;
  }

  protected int addShapeRow(Element parent, String shape, int y, int x, int count, int width, int spacing) {
    int size = (width - (spacing * (count - 1))) / count;
    for (int index = 0; index < count; index++) {
      addShape(parent, shape, VALUE_COLOR_WHITE, x + spacing / 2 + index * (size + spacing), y, size, "true"); //$NON-NLS-1$
    }
    return size;
  }

  protected final String methodCall(String methodName) {
    return methodCall(methodName, new Object[0]);
  }

  protected final String methodCall(String methodName, Object[] parameters) {
    String methodCall = "." + methodName + "("; //$NON-NLS-1$ //$NON-NLS-2$
    for (int index = 0; index < parameters.length; index++) {
      if (index != 0) {
        methodCall += ","; //$NON-NLS-1$
      }
      methodCall += parameters[index];
    }
    return methodCall + ")"; //$NON-NLS-1$
  }

  protected final void addPrintWhenExpression(Element reportElement, String printWhenExpression) {
    reportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression);
  }

  protected final String negate(String printWhenExpression) {
    return "!(" + printWhenExpression + ")";
  }

  protected final String encodeIsStringParameterNullOrEmpty(String parameter) {
    String parameterString = ParameterUtilities.parameterString(parameter);
    return parameterString + "==null||" + parameterString + methodCall("length") + "==0";
  }

  protected final void encodeSubreport(Element bandElement, Rectangle bounds, String subreportParameterName) {
    encodeSubreportWithDatasource(bandElement, bounds, subreportParameterName, null);
  }

  protected final void encodeSubreportWithDatasource(
      Element bandElement,
      Rectangle bounds,
      String subreportParameterName,
      String dataSourceName) {
    encodeSubreportWithParameters(bandElement, bounds, subreportParameterName, dataSourceName, null);
  }

  protected final void encodeSubreportWithParameters(
      Element bandElement,
      Rectangle bounds,
      String subreportParameterName,
      String dataSourceName,
      Map<String, String> subreportParameterMap) {
    Element subreportElement = bandElement.addElement("subreport");
    addReportElement(subreportElement, bounds);
    subreportElement.addElement("parametersMapExpression").addCDATA("new HashMap($P{REPORT_PARAMETERS_MAP})");
    if (subreportParameterMap != null) {
      for (String originalKey : subreportParameterMap.keySet()) {
        SubreportUtilities.addSubreportParameter(subreportElement, originalKey, subreportParameterMap.get(originalKey));
      }
    }
    if (dataSourceName != null) {
      Element dataSourceElement = subreportElement.addElement("dataSourceExpression");
      dataSourceElement.addCDATA(ParameterUtilities.parameterString(dataSourceName));
    }
    Element subreportExpression = subreportElement.addElement("subreportExpression");
    subreportExpression.addAttribute(ATTRIB_CLASS, JasperReport.class.getName());
    subreportExpression.addCDATA(ParameterUtilities.parameterString(subreportParameterName));
  }

  protected final void encodeSubreportWithExpressions(
      Element bandElement,
      Rectangle bounds,
      String subreportParameterName,
      String dataSourceName,
      Map<String, String> subreportExpressionMap) {
    Element subreportElement = bandElement.addElement("subreport");
    addReportElement(subreportElement, bounds);
    subreportElement.addElement("parametersMapExpression").addCDATA("new HashMap($P{REPORT_PARAMETERS_MAP})");
    if (subreportExpressionMap != null) {
      for (String originalKey : subreportExpressionMap.keySet()) {
        SubreportUtilities.addSubreportExpression(
            subreportElement,
            originalKey,
            subreportExpressionMap.get(originalKey));
      }
    }
    if (dataSourceName != null) {
      Element dataSourceElement = subreportElement.addElement("dataSourceExpression");
      dataSourceElement.addCDATA(ParameterUtilities.parameterString(dataSourceName));
    }
    Element subreportExpression = subreportElement.addElement("subreportExpression");
    subreportExpression.addAttribute(ATTRIB_CLASS, JasperReport.class.getName());
    subreportExpression.addCDATA(ParameterUtilities.parameterString(subreportParameterName));
  }
}