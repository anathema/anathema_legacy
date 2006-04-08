package net.sf.anathema.development.reporting.encoder.voidstate.subreports.anima;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public class VoidstateDbAnimaPageEncoder extends AbstractVoidstateAnimaPageEncoder {

  public VoidstateDbAnimaPageEncoder(IOneColumnEncoder columnEncoder) {
    super(columnEncoder);
  }

  @Override
  protected void fillAnimaTable(Element parent, Rectangle bounds, int yOffset) {
    yOffset += addAnimaTableRow(
        parent,
        bounds,
        bounds.y + yOffset,
        "1-3",
        "1-2",
        "1",
        "0",
        "Skin glows weakly",
        "Normal",
        true);
    yOffset += addAnimaTableRow(
        parent,
        bounds,
        bounds.y + yOffset,
        "4-7",
        "3-6",
        "2-5",
        "1-4",
        "Skin glows brightly",
        "+2",
        true);
    yOffset += addAnimaTableRow(
        parent,
        bounds,
        bounds.y + yOffset,
        "8-10",
        "7-9",
        "6-8",
        "5-7",
        "Shining Aura (1L/minute)",
        "Impossible",
        true);
    yOffset += addAnimaTableRow(
        parent,
        bounds,
        bounds.y + yOffset,
        "11-15",
        "10-14",
        "9-13",
        "8-12",
        "Brilliant Aura (1L/3 turns)",
        "Impossible",
        true);
    yOffset += addAnimaTableRow(
        parent,
        bounds,
        bounds.y + yOffset,
        "16+",
        "15+",
        "14+",
        "13+",
        "Totemic Aura (1L/turn)",
        "Impossible",
        false);
  }

  protected final int addAnimaTableRow(
      Element bandElement,
      Rectangle parentRect,
      int y,
      String defaultBreedingRange,
      String BreedingFourRange,
      String BreedingFiveRange,
      String BreedingSixRange,
      String anima,
      String stealth,
      boolean drawLine) {
    addMoteRangeTextElement(bandElement, parentRect, y, defaultBreedingRange, "<=3");
    addMoteRangeTextElement(bandElement, parentRect, y, BreedingFourRange, "==4");
    addMoteRangeTextElement(bandElement, parentRect, y, BreedingFiveRange, "==5");
    addMoteRangeTextElement(bandElement, parentRect, y, BreedingSixRange, "==6");
    addFlareLevelTextElement(bandElement, parentRect, y, anima);
    addStealthModifierTextElement(bandElement, parentRect, y, stealth);
    if (drawLine) {
      drawTableRowBottomLine(bandElement, parentRect, y);
    }
    return LINE_HEIGHT;
  }

  protected Element addMoteRangeTextElement(
      Element bandElement,
      Rectangle parentRect,
      int y,
      String range,
      String rangeLimit) {
    Element element = super.addMoteRangeTextElement(bandElement, parentRect, y, range);
    String breedingString = ParameterUtilities.parameterString(ICharacterReportConstants.BREEDING_VALUE);
    String printWhenExpression = breedingString + "!=null && " + breedingString + methodCall("intValue") + rangeLimit; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    element.addElement(TAG_PRINT_WHEN_EXPRESSION).addCDATA(printWhenExpression); //$NON-NLS-1$ //$NON-NLS-2$
    return element;
  }

  @Override
  protected List<ITextPart[]> getAnimaEffectTextParts() {
    List<ITextPart[]> textParts = new ArrayList<ITextPart[]>();
    TextFormat smallTextFormat = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1);
    textParts.add(new ITextPart[] { new TextPart("Cause skin to glow brightly (1 mote)", smallTextFormat) });
    textParts.add(new ITextPart[] { new TextPart(
        "Cause anima to glow bright enough to read by (1 mote)",
        smallTextFormat) });
    textParts.add(new ITextPart[] { new TextPart("Know exact location in relation to pole (1 mote)", smallTextFormat) });
    textParts.add(new ITextPart[] {
        new TextPart("Banner Flare damages everything within ", smallTextFormat),
        new TextPart("\"+$P{" + OtherTraitType.Essence.getId() + "}.toString()+\"", smallTextFormat),
        new TextPart(" yards", smallTextFormat) });
    return textParts;
  }

  public String getGroupName() {
    return "VoidStateDbAnimaSubreport";
  }
}