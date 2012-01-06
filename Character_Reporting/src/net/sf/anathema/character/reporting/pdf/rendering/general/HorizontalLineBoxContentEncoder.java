package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;

public class HorizontalLineBoxContentEncoder implements ContentEncoder {

  private static final float LINE_HEIGHT = IVoidStateFormatConstants.LINE_HEIGHT - 2f;
  private final int columnCount;
  private IResources resources;
  private final String headerKey;

  public HorizontalLineBoxContentEncoder(int columnCount, IResources resources, String headerKey) {
    this.columnCount = columnCount;
    this.resources = resources;
    this.headerKey = headerKey;
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header." + headerKey);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    float columnWidth = (bounds.width - (columnCount - 1) * IVoidStateFormatConstants.TEXT_PADDING) / columnCount;
    for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
      float columnX = bounds.x + columnIndex * columnWidth + columnIndex * IVoidStateFormatConstants.TEXT_PADDING;
      Bounds columnBounds = new Bounds(columnX, bounds.y + IVoidStateFormatConstants.TEXT_PADDING / 2f, columnWidth, bounds.height - IVoidStateFormatConstants.TEXT_PADDING / 2f);
      new HorizontalLineEncoder().encodeLines(graphics, columnBounds, LINE_HEIGHT);
    }
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
