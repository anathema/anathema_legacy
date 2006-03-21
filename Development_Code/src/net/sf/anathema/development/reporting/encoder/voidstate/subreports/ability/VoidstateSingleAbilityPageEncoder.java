package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;
import net.sf.anathema.development.reporting.util.TraitEncoder;

import org.dom4j.Element;

public class VoidstateSingleAbilityPageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder encoder;
  private final TraitEncoder traitEncoder;
  private final int dotCount;

  public VoidstateSingleAbilityPageEncoder(VoidstateBasicsEncoder basicsEncoder, TraitEncoder traitEncoder, int dotCount) {
    this.encoder = basicsEncoder;
    this.traitEncoder = traitEncoder;
    this.dotCount = dotCount;
  }

  public int encodeBand(Element bandElement) {
    int x = 0;
    int y = 0;
    int dotsSize = traitEncoder.getDotSize();
    int width = calculateExtents(encoder).width;
    int textWidth = width - traitEncoder.calculateVoidstateDotWidth(dotCount);
    addCross(bandElement, x, (LINE_HEIGHT - dotsSize - 1) + y);
    x += dotsSize + 2;
    traitEncoder.addRectanglePair(
        bandElement,
        x,
        (LINE_HEIGHT - dotsSize - 1) + y,
        IAbilityReportConstants.PARAM_FILL_RECTANGLE);
    x += dotsSize + 2;
    String traitNameParameter = ParameterUtilities.parameterString(IAbilityReportConstants.PARAM_TRAIT_NAME);
    addTextElement(bandElement, traitNameParameter, FONT_SIZE, VALUE_LEFT, x, y, textWidth, LINE_HEIGHT);
    traitEncoder.encodeWithoutText(
        bandElement,
        IAbilityReportConstants.PARAM_TRAIT_VALUE,
        x + textWidth + PADDING - 2,
        y,
        dotCount,
        true);
    return LINE_HEIGHT;
  }

  public void addCross(Element parent, int x, int y) {
    int dotsSize = traitEncoder.getDotSize();
    Element[] star = new Element[2];
    star[0] = addNormalLineElement(parent, new Rectangle(x + 1, y + dotsSize / 2, dotsSize - 2, 0));
    star[1] = addNormalLineElement(parent, new Rectangle(x + dotsSize / 2, y + 1, 0, dotsSize - 2));
    for (Element starElement : star) {
      String printWhenExpression = ParameterUtilities.parameterString(IAbilityReportConstants.PARAM_PRINT_CROSS)
          + methodCall("booleanValue");
      addPrintWhenExpression(starElement, printWhenExpression);
    }
  }

  public String getGroupName() {
    return "VoidstateSingleAbilitySubreport";
  }

  public static Rectangle calculateExtents(VoidstateBasicsEncoder basicsEncoder) {
    return new Rectangle(basicsEncoder.getSingleColumnWidth() - LINE_HEIGHT, LINE_HEIGHT);
  }
}