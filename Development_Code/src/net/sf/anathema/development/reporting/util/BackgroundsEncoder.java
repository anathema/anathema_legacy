package net.sf.anathema.development.reporting.util;

import static net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants.FONT_SIZE;
import static net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants.HEADER_FONT_SIZE;
import static net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants.LINE_HEIGHT;

import org.dom4j.Element;

public class BackgroundsEncoder extends AbstractJasperEncoder {

  private final TraitEncoder traitEncoder;

  public BackgroundsEncoder(TraitEncoder traitEncoder) {
    this.traitEncoder = traitEncoder;
  }

  public int encodeBackgrounds(Element parent, int y, int x, int width, int rowCount) {
    int rowY = y;
    for (int index = 0; index < rowCount; index++) {
      rowY += traitEncoder.encodeBackgroundRow(parent, index, x, rowY, width, 5, TraitEncoder.PADDING); //$NON-NLS-1$      
    }
    return rowY - y;
  }

  public int encodeBackgroundsWithDescription(Element parent, int y, int x, int width, int rowCount, int spacing) {
    int rowY = y;
    int backgroundWidth = (width * 3) / 5;
    int descriptionWidth = width - backgroundWidth - spacing;
    int backgroundValueWidth = traitEncoder.getBackgroundValueWidth(5);
    int valueLabelXInset = backgroundWidth - backgroundValueWidth;
    addTextElement(
        parent,
        quotify("Background"),
        HEADER_FONT_SIZE,
        VALUE_LEFT,
        x,
        y,
        valueLabelXInset - spacing,
        LINE_HEIGHT);
    addTextElement(
        parent,
        quotify("Level"),
        FONT_SIZE,
        VALUE_LEFT,
        x + valueLabelXInset,
        y,
        backgroundValueWidth,
        LINE_HEIGHT);
    addTextElement(
        parent,
        quotify("Details"),
        FONT_SIZE,
        VALUE_LEFT,
        x + backgroundWidth + spacing,
        y,
        descriptionWidth,
        LINE_HEIGHT);
    y += LINE_HEIGHT;
    for (int index = 0; index < rowCount; index++) {
      y += traitEncoder.encodeBackgroundRowWithDescription(
          parent,
          index,
          x,
          y,
          5,
          spacing,
          backgroundWidth,
          descriptionWidth);
    }
    return rowY - y;
  }
}