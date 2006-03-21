package net.sf.anathema.development.reporting.encoder.voidstate.subreports.ability;

import java.awt.Rectangle;

import net.sf.anathema.character.generic.framework.reporting.IAbilityReportConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.util.VoidStateBoxEncoder;
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
    int width = calculateExtents(encoder).width - 2;
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
    Element[] cross = traitEncoder.encodeCross(parent, x, y);
    for (Element crossElement : cross) {
      String printWhenExpression = ParameterUtilities.parameterString(IAbilityReportConstants.PARAM_PRINT_CROSS)
          + methodCall("booleanValue");
      addPrintWhenExpression(crossElement, printWhenExpression);
    }
  }

  public String getGroupName() {
    return "VoidstateSingleAbilitySubreport";
  }

  public static Rectangle calculateExtents(VoidstateBasicsEncoder basicsEncoder) {
    return new Rectangle(
        basicsEncoder.getSingleColumnWidth() - LINE_HEIGHT - 2 * VoidStateBoxEncoder.TEXT_INSET,
        LINE_HEIGHT);
  }
}