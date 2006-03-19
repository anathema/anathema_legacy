package net.sf.anathema.development.reporting.encoder.util;

import java.awt.Rectangle;

import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;

import org.dom4j.Element;

public class TextLineEncoder extends AbstractJasperEncoder {

  private int fontSize;
  private int lineHeight;

  public TextLineEncoder(int fontSize, int lineHeight) {
    this.fontSize = fontSize;
    this.lineHeight = lineHeight;
  }

  public final int addLabeledTextParameter(
      Element bandElement,
      String label,
      String parameter,
      int y,
      int x,
      int labelWidth,
      int valueWidth) {
    return addLabeledTextParameter(bandElement, label, parameter, y, x, labelWidth, valueWidth, false);
  }

  public final int addLabeledTextParameter(
      Element bandElement,
      String label,
      String parameter,
      int y,
      int x,
      int labelWidth,
      int valueWidth,
      boolean printLineIfNull) {
    addTextElement(bandElement, quotify(label + ":"), fontSize, VALUE_LEFT, x, y, labelWidth, lineHeight); //$NON-NLS-1$
    addParameterOrLine(bandElement, parameter, y, x + labelWidth + 4, valueWidth - 4, printLineIfNull);
    return lineHeight;
  }

  public final void addParameterOrLine(
      Element parent,
      String parameter,
      int y,
      int x,
      int width,
      boolean printLineIfNull) {
    addTextElement(parent, ParameterUtilities.parameterString(parameter) + methodCall("toString"), //$NON-NLS-1$
        fontSize, VALUE_LEFT, x, y, width, lineHeight);
    if (printLineIfNull) {
      Element lineReportElement = addThinLineElement(parent, new Rectangle(x, y + lineHeight, width - 4, 0));
      String parameterString = ParameterUtilities.parameterString(parameter);
      String printWhenExpression = parameterString + "==null||" + parameterString + methodCall("length") + "==0"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      lineReportElement.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  public final int addLabeledLine(Element bandElement, String label, int y, int x, int labelWidth, int lineWidth) {
    addTextElement(bandElement, quotify(label + ":"), fontSize, VALUE_LEFT, x, y, labelWidth, lineHeight); //$NON-NLS-1$
    addThinLineElement(bandElement, new Rectangle(x + labelWidth + 4, y + lineHeight, lineWidth - 4, 0));
    return lineHeight;
  }
}