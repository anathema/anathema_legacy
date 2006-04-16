package net.sf.anathema.development.reporting.encoder.voidstate.util;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;
import net.sf.anathema.framework.reporting.jasper.IJasperXmlConstants;

import org.dom4j.Element;

public class VoidStateNaturePartEncoder extends AbstractJasperEncoder implements
    IVoidStateFormatConstants,
    IJasperXmlConstants {

  private final VoidstateBasicsEncoder basicsEncoder;
  private final String invalidConditionExpression = encodeIsStringParameterNullOrEmpty(ICharacterReportConstants.NATURE_WILLPOWER_CONDITION);
  private final String invalidNatureExpression = encodeIsStringParameterNullOrEmpty(ICharacterReportConstants.NATURE);

  public VoidStateNaturePartEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.basicsEncoder = basicsEncoder;
  }

  public int addNaturePart(Element bandElement, Rectangle textRect, int yOffset) {
    basicsEncoder.addCentralHeader(bandElement, textRect, textRect.y + yOffset, "Nature");
    yOffset += 10;
    encodeFirstLine(bandElement, textRect, yOffset);
    yOffset += LINE_HEIGHT;
    encodeSecondLine(bandElement, textRect, yOffset);
    yOffset += LINE_HEIGHT;
    encodeThirdLine(bandElement, textRect, yOffset);
    return yOffset + LINE_HEIGHT;
  }

  private void encodeFirstLine(Element bandElement, Rectangle textRect, int yOffset) {
    String linePrintWhen = invalidNatureExpression;
    String simpleTextPrintWhen = invalidConditionExpression + " && " + negate(invalidNatureExpression);
    String complexTextPrintWhen = negate(invalidConditionExpression) + " && " + negate(invalidNatureExpression);
    String simpleText = ParameterUtilities.parameterString(ICharacterReportConstants.NATURE);
    String complexText = simpleText
        + "+\": Gain Willpower \"+"
        + ParameterUtilities.parameterString(ICharacterReportConstants.NATURE_WILLPOWER_CONDITION);
    int x = textRect.x;
    int y = textRect.y + yOffset;
    int width = textRect.width - 1;
    basicsEncoder.addTextOrLine(bandElement, simpleText, simpleTextPrintWhen, linePrintWhen, x, y, width);
    basicsEncoder.addOptionalText(bandElement, complexText, complexTextPrintWhen, x, y, width);
  }

  private void encodeSecondLine(Element bandElement, Rectangle textRect, int yOffset) {
    Element gainWillpowerTextelement = addTextElement(
        bandElement,
        quotify("Gain Willpower"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x,
        textRect.y + yOffset,
        textRect.width,
        LINE_HEIGHT);
    addPrintWhenExpression(gainWillpowerTextelement, invalidConditionExpression);
    Element shortFirstLineElement = addThinLineElement(bandElement, new Rectangle(textRect.x + 50, textRect.y
        + yOffset
        + LINE_HEIGHT, textRect.width - 55, 0));
    addPrintWhenExpression(shortFirstLineElement, invalidConditionExpression);
  }

  private void encodeThirdLine(Element bandElement, Rectangle textRect, int yOffset) {
    Element secondLineElement = addThinLineElement(bandElement, new Rectangle(textRect.x, textRect.y
        + yOffset
        + LINE_HEIGHT, textRect.width - 5, 0));
    addPrintWhenExpression(secondLineElement, invalidConditionExpression);
  }
}