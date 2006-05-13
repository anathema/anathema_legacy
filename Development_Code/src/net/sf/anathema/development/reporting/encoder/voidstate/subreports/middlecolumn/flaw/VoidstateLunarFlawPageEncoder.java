package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.flaw;

import java.awt.Rectangle;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.character.generic.framework.reporting.template.voidstate.ExaltVoidstateReportTemplate;
import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.ParameterUtilities;
import net.sf.anathema.framework.styledtext.model.ITextFormat;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public class VoidstateLunarFlawPageEncoder extends AbstractVoidstateFlawPageEncoder implements
    IVoidStateFormatConstants {

  private final VoidstateBasicsEncoder basicsEncoder;

  public VoidstateLunarFlawPageEncoder(VoidstateBasicsEncoder basicsEncoder) {
    this.basicsEncoder = basicsEncoder;
  }

  public int encodeBand(Element bandElement) {
    ITextFormat smallTextFormat = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1);
    Rectangle boxRectangle = calculateBounds(basicsEncoder);
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Great Curse");
    int yOffset = addShapeRow(bandElement, TAG_RECTANGLE, textRect.y + 3, textRect.x, 10, textRect.width, 4);
    yOffset += 6;
    addTextElement(
        bandElement,
        ParameterUtilities.parameterString(ExaltVoidstateReportTemplate.VIRTUE_FLAW) + methodCall("toString"),
        FONT_SIZE,
        VALUE_CENTER,
        textRect.x,
        textRect.y + yOffset,
        textRect.width,
        LINE_HEIGHT);
    Element element = addThinLineElement(bandElement, new Rectangle(
        textRect.x,
        textRect.y + yOffset + LINE_HEIGHT,
        textRect.width,
        0));
    addPrintWhenExpression(element, encodeIsStringParameterNullOrEmpty(ExaltVoidstateReportTemplate.VIRTUE_FLAW));

    yOffset += LINE_HEIGHT;
    basicsEncoder.addCentralHeader(bandElement, textRect, textRect.y + yOffset, "Rules");
    yOffset += 8;
    addTextWithCaret(
        bandElement,
        textRect,
        new ITextPart[] {
            new TextPart("Gain Limit when acting against ", smallTextFormat),
            new TextPart("\"+$P{" + ExaltVoidstateReportTemplate.VIRTUE_FLAW_ROOT + "}.toString()+\"", smallTextFormat) },
        yOffset,
        FONT_SIZE,
        LINE_HEIGHT);
    yOffset += LINE_HEIGHT;
    addTextWithCaret(bandElement, textRect, new ITextPart[] { new TextPart(
        "Roll for Limit when struck by full moon",
        smallTextFormat) }, yOffset, FONT_SIZE, LINE_HEIGHT);
    yOffset += LINE_HEIGHT;
    addTextWithCaret(bandElement, textRect, new ITextPart[] { new TextPart(
        "See Lunars, page 109-110 for detailed rules",
        smallTextFormat) }, yOffset, FONT_SIZE, LINE_HEIGHT);
    return boxRectangle.height;
  }

  public String getGroupName() {
    return "VoidstateLunarFlaw";
  }
}