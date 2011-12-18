package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;

public class PdfHorizontalLineContentEncoder implements IBoxContentEncoder {

  private static final float LINE_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2f;
  private final int columnCount;
  private final String headerKey;

  public String getHeaderKey(ReportContent reportContent) {
    return headerKey;
  }

  public PdfHorizontalLineContentEncoder(int columnCount, String headerKey) {
    this.columnCount = columnCount;
    this.headerKey = headerKey;
  }

  public void encode(Graphics graphics, ReportContent reportContent) throws DocumentException {
    float columnWidth = (graphics.getBounds().width - (columnCount - 1) * IVoidStateFormatConstants.TEXT_PADDING) / columnCount;
    for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
      float columnX = graphics.getBounds().x + columnIndex * columnWidth + columnIndex * IVoidStateFormatConstants.TEXT_PADDING;
      Bounds columnBounds = new Bounds(columnX, graphics.getBounds().y + IVoidStateFormatConstants.TEXT_PADDING / 2f, columnWidth,
        graphics.getBounds().height - IVoidStateFormatConstants.TEXT_PADDING / 2f);
      new HorizontalLineListEncoder().encodeLines(graphics.getDirectContent(), columnBounds, LINE_HEIGHT);
    }
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
