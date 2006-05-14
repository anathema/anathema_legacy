package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.flaw;

import java.awt.Rectangle;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.framework.styledtext.model.ITextFormat;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public class VoidstateSiderealFlawPageEncoder extends AbstractVoidstateFlawPageEncoder implements
    IVoidStateFormatConstants {

  private final IOneColumnEncoder columnEncoder;

  public VoidstateSiderealFlawPageEncoder(IOneColumnEncoder columnEncoder) {
    this.columnEncoder = columnEncoder;
  }

  public int encodeBand(Element bandElement) {
    ITextFormat smallTextFormat = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1);
    Rectangle boxRectangle = calculateBounds(columnEncoder);
    Rectangle textRect = columnEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Paradox");
    int yOffset = addShapeRow(bandElement, TAG_RECTANGLE, textRect.y + 3, textRect.x, 10, textRect.width, 4);
    yOffset += 6;
    columnEncoder.addCentralHeader(bandElement, textRect, textRect.y + yOffset, "Paradox Effects");
    yOffset += 8;
    addTextElement(
        bandElement,
        quotify("Current Effects:"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x,
        textRect.y + yOffset,
        textRect.width,
        LINE_HEIGHT);
    yOffset += LINE_HEIGHT;
    addLineElement(
        bandElement,
        new Rectangle(textRect.x + 50, textRect.y + yOffset, textRect.width - 55, 0),
        VALUE_PEN_THIN);
    addTextWithCaret(bandElement, textRect, new ITextPart[] { new TextPart(
        "See page 217 for resplendent destiny Paradox gain",
        smallTextFormat) }, yOffset, FONT_SIZE, LINE_HEIGHT);
    yOffset += LINE_HEIGHT;
    addTextWithCaret(bandElement, textRect, new ITextPart[] { new TextPart(
        "See page 219 for astrology Paradox gain",
        smallTextFormat) }, yOffset, FONT_SIZE, LINE_HEIGHT);
    yOffset += LINE_HEIGHT;
    addTextWithCaret(bandElement, textRect, new ITextPart[] { new TextPart(
        "See page 215 for Pattern Bite effects",
        smallTextFormat) }, yOffset, FONT_SIZE, LINE_HEIGHT);
    return boxRectangle.height;

  }

  public String getGroupName() {
    return "VoidstateSiderealParadox";
  }
}