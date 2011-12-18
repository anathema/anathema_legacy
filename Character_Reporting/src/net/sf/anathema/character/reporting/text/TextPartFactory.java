package net.sf.anathema.character.reporting.text;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import net.sf.anathema.framework.reporting.ITextReportUtils;

public final class TextPartFactory {

  private static final int STANDARD_FONT_SIZE = 8;
  private final ITextReportUtils utils;

  public TextPartFactory(ITextReportUtils utils) {
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
