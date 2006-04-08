package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.flaw;

import java.awt.Rectangle;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants;
import net.sf.anathema.framework.styledtext.model.ITextFormat;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public class VoidstateAbyssalFlawPageEncoder extends AbstractVoidstateFlawPageEncoder implements IVoidStateFormatConstants {

  private final IOneColumnEncoder columnEncoder;

  public VoidstateAbyssalFlawPageEncoder(IOneColumnEncoder columnEncoder) {
    this.columnEncoder = columnEncoder;
  }

  public int encodeBand(Element bandElement) {
    ITextFormat smallTextFormat = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1);
    Rectangle boxRectangle = calculateBounds(columnEncoder);
    Rectangle textRect = columnEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Resonance");
    int yOffset = addShapeRow(bandElement, TAG_RECTANGLE, textRect.y + 3, textRect.x, 10, textRect.width, 4);
    yOffset += 6;
    columnEncoder.addCentralHeader(bandElement, textRect, textRect.y + yOffset, "Resonance Effects");
    yOffset += 8;
    addTextWithCaret(bandElement, textRect, new ITextPart[] { new TextPart(
        "Social pools decreased by (Resonance - Max. Virtue)",
        smallTextFormat) }, yOffset, FONT_SIZE, LINE_HEIGHT);
    yOffset += LINE_HEIGHT;
    addTextWithCaret(bandElement, textRect, new ITextPart[] { new TextPart(
        "Difficulty of Virtue rolls increased by (Resonance/4)",
        smallTextFormat) }, yOffset, FONT_SIZE, LINE_HEIGHT);
    yOffset += LINE_HEIGHT;
    addTextWithCaret(bandElement, textRect, new ITextPart[] { new TextPart(
        "Resonance in excess of 10 bleeds off immediately",
        smallTextFormat) }, yOffset, FONT_SIZE, LINE_HEIGHT);
    yOffset += LINE_HEIGHT;
    addTextWithCaret(bandElement, textRect, new ITextPart[] { new TextPart(
        "See Abyssals, page 147-149 for detailed rules",
        smallTextFormat) }, yOffset, FONT_SIZE, LINE_HEIGHT);
    return boxRectangle.height;
  }

  public String getGroupName() {
    return "VoidStateAbyssalResonance";
  }
}