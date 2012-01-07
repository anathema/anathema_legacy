package net.sf.anathema.character.reporting.pdf.rendering.general;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class NullBoxContentEncoder implements ContentEncoder {

  private final String headerKey;

  public NullBoxContentEncoder() {
    this("Null"); //$NON-NLS-1$
  }

  public NullBoxContentEncoder(String headerKey) {
    this.headerKey = headerKey;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    // Nothing to do
  }

  @Override
  public String getHeader(ReportContent content) {
    return headerKey;
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
