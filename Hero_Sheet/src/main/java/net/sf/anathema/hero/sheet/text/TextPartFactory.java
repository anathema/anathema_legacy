package net.sf.anathema.hero.sheet.text;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;

public class TextPartFactory {

  private static final int STANDARD_FONT_SIZE = 8;
  private static final int LARGE_FONT_SIZE = 11;
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

  public Chunk createBoldChunk(String title) {
    return new Chunk(title, utils.createDefaultFont(STANDARD_FONT_SIZE, Font.BOLD));
  }

  public Paragraph createTitleParagraph(String title) {
    Font titleFont = utils.createDefaultFont(LARGE_FONT_SIZE, Font.BOLD);
    Chunk chunk = new Chunk(title, titleFont);
    Paragraph paragraph = new Paragraph(chunk);
    paragraph.setAlignment(Element.ALIGN_JUSTIFIED);
    paragraph.setLeading(titleFont.getSize() * 1.2f);
    return paragraph;
  }
}
