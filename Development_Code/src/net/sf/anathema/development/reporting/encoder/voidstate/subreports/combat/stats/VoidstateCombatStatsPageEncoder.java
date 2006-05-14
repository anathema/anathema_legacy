package net.sf.anathema.development.reporting.encoder.voidstate.subreports.combat.stats;

import java.awt.Point;
import java.awt.Rectangle;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.character.generic.framework.reporting.ICharacterReportConstants;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public class VoidstateCombatStatsPageEncoder extends AbstractCharacterSheetPageEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder basicsEncoder;

  public VoidstateCombatStatsPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.basicsEncoder = basicsEncoder;
  }

  public static Rectangle calculateExtents(VoidstateBasicsEncoder basicsEncoder) {
    return basicsEncoder.createOneColumnBoxBoundsWithTitle(10, 1, new Point(0, 0));
  }

  public int encodeBand(Element bandElement) {
    Rectangle boxRectangle = VoidstateCombatStatsPageEncoder.calculateExtents(basicsEncoder);
    Rectangle textRectangle = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Combat");
    int valueXInset = 60;
    int yOffset = 0;
    addStyledTextElement(
        bandElement,
        new ITextPart[] { new TextPart("Base Initiative ", DEFAULT_TEXT_FORMAT) },
        FONT_SIZE,
        VALUE_LEFT,
        textRectangle.x,
        textRectangle.y,
        50,
        LINE_HEIGHT);
    addStyledTextElement(
        bandElement,
        new ITextPart[] {
            new TextPart("\"+$P{" + ICharacterReportConstants.INTITIATIVE + "}.intValue()+\"", DEFAULT_TEXT_FORMAT),
            new TextPart("-Wound Penalty", new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 2)) },
        FONT_SIZE,
        VALUE_LEFT,
        textRectangle.x + valueXInset,
        textRectangle.y,
        100,
        LINE_HEIGHT);
    yOffset += LINE_HEIGHT;
    addTextElement(bandElement, quotify("Dodge Pool"), FONT_SIZE, VALUE_LEFT, textRectangle.x, textRectangle.y
        + yOffset, 50, LINE_HEIGHT);
    addStyledTextElement(
        bandElement,
        new ITextPart[] {
            new TextPart("\"+$P{" + ICharacterReportConstants.DODGE + "}.intValue()+\"", DEFAULT_TEXT_FORMAT),
            new TextPart("-Mob. Penalty", new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 2)) },
        FONT_SIZE,
        VALUE_LEFT,
        textRectangle.x + valueXInset,
        textRectangle.y + yOffset,
        100,
        LINE_HEIGHT);
    yOffset += LINE_HEIGHT + 3;

    ITextPart knockDown = new TextPart("Knockdown: ", DEFAULT_TEXT_FORMAT);
    ITextPart knockDownDescription = new TextPart(
        "Character may be knocked down if receives more than Stamina + Resistance pre-soak damage in a single blow. Stamina + Resistance (difficulty 1) to avoid. Fallen characters are at -2 to all dice pools until an action is spent standing up.",
        new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1));
    addTextWithCaret(
        bandElement,
        textRectangle,
        new ITextPart[] { knockDown, knockDownDescription },
        yOffset,
        FONT_SIZE,
        LINE_HEIGHT);
    yOffset += LINE_HEIGHT * 3 + TEXT_PADDING;
    addTextElement(
        bandElement,
        quotify("Knockdown"),
        FONT_SIZE,
        VALUE_LEFT,
        textRectangle.x,
        textRectangle.y + yOffset,
        50,
        LINE_HEIGHT);
    addTextElement(
        bandElement,
        "$P{" + ICharacterReportConstants.KNOCKDOWN + "}.toString()",
        FONT_SIZE,
        VALUE_LEFT,
        textRectangle.x + valueXInset,
        textRectangle.y + yOffset,
        50,
        LINE_HEIGHT);
    yOffset += LINE_HEIGHT + 3;
    ITextPart stunning = new TextPart("Stunning: ", DEFAULT_TEXT_FORMAT);
    ITextPart stunningDescription = new TextPart(
        "Characters who take more health levels of damage than their Stamina in a single blow must make a reflexive Stamina + Resistance roll (difficulty equal to the difference) or be stunned for (6 - Stamina) turns. Stunned characters are at -2 dice to all dice pools.",
        new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1));
    addTextWithCaret(
        bandElement,
        textRectangle,
        new ITextPart[] { stunning, stunningDescription },
        yOffset,
        FONT_SIZE,
        LINE_HEIGHT);
    yOffset += 3 * LINE_HEIGHT + TEXT_PADDING;
    addTextElement(bandElement, quotify("Stun Threshold"), FONT_SIZE, VALUE_LEFT, textRectangle.x, textRectangle.y
        + yOffset, 50, LINE_HEIGHT);
    addTextElement(
        bandElement,
        "$P{" + ICharacterReportConstants.STUN_THRESHOLD + "}.toString()",
        FONT_SIZE,
        VALUE_LEFT,
        textRectangle.x + valueXInset,
        textRectangle.y + yOffset,
        50,
        LINE_HEIGHT);
    yOffset += TEXT_PADDING + 3;
    addTextElement(bandElement, quotify("Stun Duration"), FONT_SIZE, VALUE_LEFT, textRectangle.x, textRectangle.y
        + yOffset, 50, LINE_HEIGHT);
    addTextElement(
        bandElement,
        "$P{" + ICharacterReportConstants.STUN_DURATION + "}.toString()",
        FONT_SIZE,
        VALUE_LEFT,
        textRectangle.x + valueXInset,
        textRectangle.y + yOffset,
        50,
        LINE_HEIGHT);
    return boxRectangle.height;
  }

  public String getGroupName() {
    return "VoidstateCombatStatsSubreport";
  }
}