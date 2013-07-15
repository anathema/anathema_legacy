package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

public class NullBoxContentEncoder implements ContentEncoder {

  private final String headerKey;

  public NullBoxContentEncoder(String headerKey) {
    this.headerKey = headerKey;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    // Nothing to do
  }

  @Override
  public String getHeader(ReportSession session) {
    return headerKey;
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
