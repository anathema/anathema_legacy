package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.HorizontalLineListEncoder;
import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class LunarFaceEncoder implements IPdfContentBoxEncoder {

  private static final int LINE_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private final int columnCount = 1;

  public String getHeaderKey() {
    return "Lunar.Face";
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    float columnWidth = (bounds.width - (columnCount - 1) * IVoidStateFormatConstants.TEXT_PADDING) / columnCount;
    for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
      float columnX = bounds.x + columnIndex * columnWidth + columnIndex * IVoidStateFormatConstants.TEXT_PADDING;
      Bounds columnBounds = new Bounds(columnX, bounds.y, columnWidth, bounds.height);
      new HorizontalLineListEncoder().encodeLines(directContent, columnBounds, LINE_HEIGHT);
    }
  }
}