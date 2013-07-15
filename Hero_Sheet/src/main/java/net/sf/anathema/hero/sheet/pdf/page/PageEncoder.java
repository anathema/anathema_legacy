package net.sf.anathema.hero.sheet.pdf.page;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.page.layout.Sheet;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

public interface PageEncoder {

  int FIRST_PAGE_CONTENT_HEIGHT = 755;

  void encode(Sheet sheet, SheetGraphics graphics, ReportSession session) throws DocumentException;
}
