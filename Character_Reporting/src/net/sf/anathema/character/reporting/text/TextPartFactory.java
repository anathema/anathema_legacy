package net.sf.anathema.character.reporting.text;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;

public final class TextPartFactory {

  private static final int STANDARD_FONT_SIZE = 8;
  private final PdfReportUtils utils;

  public TextPartFactory(PdfReportUtils utils) {
    this.utils = utils;
  }

  public Paragraph createTextParagraph(Chunk chunk) {
    Paragraph paragraph = new Paragraph(chunk);
    paragraph.setLeading(STANDARD_FONT_SIZE * 1.2f);
    return paragraph;
  }

  public Chunk createTextChunk(String text) {
    return new Chunk(text, utils.createDefaultFont(STANDARD_FONT_SIZE, Font.NORMAL));
  }

  public Chunk createBoldTitle(String title) {
    return new Chunk(title, utils.createDefaultFont(STANDARD_FONT_SIZE, Font.BOLD));
  }
}
