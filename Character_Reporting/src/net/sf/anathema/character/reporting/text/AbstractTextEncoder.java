package net.sf.anathema.character.reporting.text;

import net.sf.anathema.framework.reporting.itext.ITextReportUtils;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;

public class AbstractTextEncoder {
  private static final int STANDARD_FONT_SIZE = 8;
  private final ITextReportUtils utils;
  private final IResources resources;

  public AbstractTextEncoder(ITextReportUtils utils, IResources resources) {
    this.utils = utils;
    this.resources = resources;
  }

  protected final Paragraph createTextParagraph(Chunk chunk) {
    Paragraph paragraph = new Paragraph(chunk);
    paragraph.setLeading(STANDARD_FONT_SIZE * 1.2f);
    return paragraph;
  }

  protected final Chunk createTextChunk(String text) {
    return new Chunk(text, utils.createDefaultFont(STANDARD_FONT_SIZE, Font.NORMAL));
  }

  protected final Chunk createBoldTitle(String title) {
    return new Chunk(title, utils.createDefaultFont(STANDARD_FONT_SIZE, Font.BOLD));
  }

  protected final ITextReportUtils getUtils() {
    return utils;
  }

  protected final String getString(String key) {
    return resources.getString(key);
  }
}
