package net.sf.anathema.hero.sheet.pdf.encoder.general.box;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.encoder.extent.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;

public interface ContentEncoder {

  void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException;

  boolean hasContent(ReportSession session);

  String getHeader(ReportSession session);
}
