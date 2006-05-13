package net.sf.anathema.development.reporting.util;

import net.sf.anathema.character.reporting.pageformat.IVoidStateFormatConstants;

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
        IVoidStateFormatConstants.HEADER_FONT_SIZE,
        VALUE_LEFT,
        x,
        y,
        valueLabelXInset - spacing,
        IVoidStateFormatConstants.LINE_HEIGHT);
    addTextElement(
        parent,
        quotify("Level"),
        IVoidStateFormatConstants.FONT_SIZE,
        VALUE_LEFT,
        x + valueLabelXInset,
        y,
        backgroundValueWidth,
        IVoidStateFormatConstants.LINE_HEIGHT);
    addTextElement(parent, quotify("Details"), IVoidStateFormatConstants.FONT_SIZE, VALUE_LEFT, x
        + backgroundWidth
        + spacing, y, descriptionWidth, IVoidStateFormatConstants.LINE_HEIGHT);
    y += IVoidStateFormatConstants.LINE_HEIGHT;
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