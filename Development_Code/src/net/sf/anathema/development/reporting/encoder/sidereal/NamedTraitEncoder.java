package net.sf.anathema.development.reporting.encoder.sidereal;

import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.development.reporting.util.AbstractJasperEncoder;
import net.sf.anathema.development.reporting.util.TraitEncoder;

import org.dom4j.Element;

public class NamedTraitEncoder extends AbstractJasperEncoder {

  private final TraitEncoder traitEncoder;
  private final String dataSourceParameterName;
  private final String title;

  public NamedTraitEncoder(TraitEncoder traitEncoder, String dataSourceParameterName, String title) {
    this.traitEncoder = traitEncoder;
    this.dataSourceParameterName = dataSourceParameterName;
    this.title = title;
  }

  public int encodeTraitDataSourceRows(Element parent, int y, int x, int width, int rowCount) {
    int rowY = y;
    int valueWidth = traitEncoder.getBackgroundValueWidth(5);
    int valueLabelXInset = width - valueWidth;
    addTextElement(
        parent,
        quotify(title),
        IVoidStateFormatConstants.HEADER_FONT_SIZE,
        VALUE_LEFT,
        x,
        y,
        valueLabelXInset - IVoidStateFormatConstants.TEXT_PADDING,
        IVoidStateFormatConstants.LINE_HEIGHT);
    addTextElement(
        parent,
        quotify("Level"),
        IVoidStateFormatConstants.FONT_SIZE,
        VALUE_LEFT,
        x + valueLabelXInset,
        y,
        valueWidth,
        IVoidStateFormatConstants.LINE_HEIGHT);
    rowY += IVoidStateFormatConstants.LINE_HEIGHT;
    for (int index = 0; index < rowCount; index++) {
      rowY += traitEncoder.encodeNamedTraitDataSourceRow(
          parent,
          index,
          x,
          rowY,
          width,
          5,
          TraitEncoder.PADDING,
          dataSourceParameterName);
    }
    return rowY - y;
  }
}