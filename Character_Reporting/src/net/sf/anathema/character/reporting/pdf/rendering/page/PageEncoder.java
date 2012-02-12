package net.sf.anathema.character.reporting.pdf.rendering.page;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public interface PageEncoder {

  static final int FIXED_CONTENT_HEIGHT = 755;

  void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException;
}
