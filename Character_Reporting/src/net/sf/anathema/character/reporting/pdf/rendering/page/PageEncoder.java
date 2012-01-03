package net.sf.anathema.character.reporting.pdf.rendering.page;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public interface PageEncoder {

  static final int CONTENT_HEIGHT = 755;

  void encode(Document document, SheetGraphics graphics, ReportContent content) throws DocumentException;
}
