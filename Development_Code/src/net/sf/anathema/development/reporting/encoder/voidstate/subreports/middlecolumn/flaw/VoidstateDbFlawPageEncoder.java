package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.flaw;

import java.awt.Rectangle;

import net.disy.commons.core.text.font.FontStyle;
import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.voidstate.columns.IOneColumnEncoder;
import net.sf.anathema.framework.styledtext.model.ITextFormat;
import net.sf.anathema.framework.styledtext.model.ITextPart;
import net.sf.anathema.framework.styledtext.presentation.TextFormat;
import net.sf.anathema.framework.styledtext.presentation.TextPart;

import org.dom4j.Element;

public class VoidstateDbFlawPageEncoder extends AbstractVoidstateFlawPageEncoder implements IVoidStateFormatConstants {

  private IOneColumnEncoder columnEncoder;

  public VoidstateDbFlawPageEncoder(IOneColumnEncoder columnEncoder) {
    this.columnEncoder = columnEncoder;
  }

  public int encodeBand(Element bandElement) {
    ITextFormat smallTextFormat = new TextFormat(FontStyle.PLAIN, false, FONT_SIZE - 1);
    int yOffset = 3;
    Rectangle boxRectangle = calculateBounds(columnEncoder);
    Rectangle textRect = columnEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Great Curse");
    addTextWithCaret(
        bandElement,
        textRect,
        new ITextPart[] { new TextPart(
            "If temporary willpower reaches zero and the anima is glowing, the character becomes violently possessed by the aspect's element and the highest Virtue.\\nThe effect lasts for several hours.",
            smallTextFormat) },
        yOffset,
        FONT_SIZE,
        LINE_HEIGHT);
    yOffset += 3 * LINE_HEIGHT;
    addTextElement(
        bandElement,
        quotify("Great Curse Effect:"),
        FONT_SIZE,
        VALUE_LEFT,
        textRect.x,
        textRect.y + yOffset,
        textRect.width - 5,
        LINE_HEIGHT);
    addThinLineElement(bandElement, new Rectangle(
        textRect.x + 65,
        textRect.y + yOffset + LINE_HEIGHT,
        textRect.width - 70,
        0));
    yOffset += LINE_HEIGHT;
    for (int index = 0; index < 2; index++) {
      addThinLineElement(bandElement, new Rectangle(
          textRect.x,
          textRect.y + yOffset + (index + 1) * LINE_HEIGHT,
          textRect.width - 5,
          0));
    }
    return boxRectangle.height;
  }

  public String getGroupName() {
    return "VoidStateDbGreatCurse";
  }
}