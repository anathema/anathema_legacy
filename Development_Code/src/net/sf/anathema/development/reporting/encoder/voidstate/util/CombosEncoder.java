package net.sf.anathema.development.reporting.encoder.voidstate.util;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.template.AbstractMagicUserCharacterReportTemplate;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;
import net.sf.anathema.framework.reporting.encoding.TextEncoding;
import net.sf.anathema.framework.reporting.jasper.IJasperXmlConstants;

import org.dom4j.Element;

public class CombosEncoder extends AbstractJasperEncoder {

  private final int fontSize;
  private final VoidstateBasicsEncoder basicsEncoder;

  public CombosEncoder(int fontSize, VoidstateBasicsEncoder basicsEncoder) {
    this.fontSize = fontSize;
    this.basicsEncoder = basicsEncoder;
  }

  public void encodeWithStyledText(Element parent, Rectangle bounds, int lineCount) {
    String styledCDataParameter = ParameterUtilities.parameterString(AbstractMagicUserCharacterReportTemplate.COMBO_STYLED_CDATA);
    Element textField = TextEncoding.addTextFieldElement(parent);
    Element reportElement = addReportElement(textField, bounds);
    String printWhenExpression = styledCDataParameter + " != null";
    addPrintWhenExpression(reportElement, printWhenExpression);
    Element textElement = TextEncoding.addHorizontalTextElement(textField, fontSize, VALUE_LEFT, true);
    textElement.addAttribute(IJasperXmlConstants.ATTRIB_VERTICAL_ALIGNMENT, IJasperXmlConstants.VALUE_TOP);
    addStyleText(textField, styledCDataParameter);
    basicsEncoder.addSometimesPrintedEmptyLines(parent, bounds, lineCount, negate(printWhenExpression));
  }
}