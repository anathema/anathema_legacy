package net.sf.anathema.development.reporting.encoder.voidstate.subreports.middlecolumn.virtues;

import java.awt.Point;
import java.awt.Rectangle;

import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.encoder.AbstractCharacterSheetPageEncoder;
import net.sf.anathema.development.reporting.encoder.voidstate.format.VoidstateBasicsEncoder;
import net.sf.anathema.development.reporting.util.TraitEncoder;

import org.dom4j.Element;

public class VoidstateVirtuesPageEncoder extends AbstractCharacterSheetPageEncoder implements IVoidStateFormatConstants {

  private final TraitEncoder traitEncoder;
  private final VoidstateBasicsEncoder basicsEncoder;

  public VoidstateVirtuesPageEncoder(VoidstateBasicsEncoder basicsEncoder, TraitEncoder traitEncoder) {
    this.basicsEncoder = basicsEncoder;
    this.traitEncoder = traitEncoder;
  }

  public int encodeBand(Element bandElement) {
    Rectangle boxRectangle = getExtents(basicsEncoder);
    Rectangle textRect = basicsEncoder.encodeBoxAndQuotifyHeader(bandElement, boxRectangle, "Virtues");

    int virtueWidth = textRect.width / 2;
    int secondColumnX = textRect.x + virtueWidth;
    int yOffset = 0;
    addTextElement(
        bandElement,
        quotify("Compassion"),
        FONT_SIZE,
        VALUE_CENTER,
        textRect.x,
        textRect.y,
        virtueWidth,
        LINE_HEIGHT);
    addTextElement(
        bandElement,
        quotify("Temperance"),
        FONT_SIZE,
        VALUE_CENTER,
        secondColumnX,
        textRect.y,
        virtueWidth,
        LINE_HEIGHT);
    yOffset += LINE_HEIGHT * 2 / 3;
    int inset = 20;
    int traitHeight = traitEncoder.encodeWithoutText(bandElement, "Compassion", textRect.x + inset, textRect.y
        + yOffset, 5, false);
    traitEncoder.encodeWithoutText(bandElement, "Temperance", secondColumnX + inset, textRect.y + yOffset, 5, false);
    yOffset += traitHeight;
    yOffset += LINE_HEIGHT / 4;
    addRectangleRow(
        bandElement,
        textRect.x + inset,
        textRect.y + yOffset,
        5,
        traitEncoder.getDotSize(),
        TraitEncoder.DOT_SPACING);
    addRectangleRow(
        bandElement,
        secondColumnX + inset,
        textRect.y + yOffset,
        5,
        traitEncoder.getDotSize(),
        TraitEncoder.DOT_SPACING);

    yOffset += traitHeight;
    addTextElement(
        bandElement,
        quotify("Conviction"),
        FONT_SIZE,
        VALUE_CENTER,
        textRect.x,
        textRect.y + yOffset,
        virtueWidth,
        LINE_HEIGHT);
    addTextElement(
        bandElement,
        quotify("Valor"),
        FONT_SIZE,
        VALUE_CENTER,
        secondColumnX,
        textRect.y + yOffset,
        virtueWidth,
        LINE_HEIGHT);
    yOffset += LINE_HEIGHT * 2 / 3;
    traitEncoder.encodeWithoutText(bandElement, "Conviction", textRect.x + inset, textRect.y + yOffset, 5, false);
    traitEncoder.encodeWithoutText(bandElement, "Valor", secondColumnX + inset, textRect.y + yOffset, 5, false);
    yOffset += traitHeight;
    yOffset += LINE_HEIGHT / 4;
    addRectangleRow(
        bandElement,
        textRect.x + inset,
        textRect.y + yOffset,
        5,
        traitEncoder.getDotSize(),
        TraitEncoder.DOT_SPACING);
    addRectangleRow(
        bandElement,
        secondColumnX + inset,
        textRect.y + yOffset,
        5,
        traitEncoder.getDotSize(),
        TraitEncoder.DOT_SPACING);

    return textRect.height + TITLE_HEIGHT + TEXT_PADDING * 2;
  }

  public String getGroupName() {
    return "VoidstateVirtuesSubreport";
  }

  public static Rectangle getExtents(VoidstateBasicsEncoder encoder) {
    return encoder.createOneColumnBoxBoundsWithTitle(3, 1, new Point(0, 0));
  }
}