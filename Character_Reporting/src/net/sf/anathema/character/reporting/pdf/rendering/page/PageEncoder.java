package net.sf.anathema.character.reporting.pdf.rendering.page;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public interface PageEncoder {

  static final int FIRST_PAGE_CONTENT_HEIGHT = 755;

  void encode(Document document, SheetGraphics graphics, ReportSession session) throws DocumentException;
}
