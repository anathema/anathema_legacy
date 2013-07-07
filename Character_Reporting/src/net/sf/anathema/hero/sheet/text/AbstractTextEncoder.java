package net.sf.anathema.hero.sheet.text;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Paragraph;
import net.sf.anathema.framework.reporting.pdf.PdfReportUtils;
import net.sf.anathema.lib.resources.Resources;

public abstract class AbstractTextEncoder implements HeroTextEncoder {
  private final PdfReportUtils utils;
  private final Resources resources;
  private final TextPartFactory factory;

  public AbstractTextEncoder(PdfReportUtils utils, Resources resources) {
    this.utils = utils;
    this.resources = resources;
    this.factory = new TextPartFactory(utils);
  }

  protected final Paragraph createTextParagraph(Chunk chunk) {
    return factory.createTextParagraph(chunk);
  }

  protected final Chunk createTextChunk(String text) {
    return factory.createTextChunk(text);
  }

  protected final Chunk createBoldTitle(String title) {
    return factory.createBoldTitle(title);
  }

  protected final PdfReportUtils getUtils() {
    return utils;
  }

  protected final String getString(String key) {
    return resources.getString(key);
  }
}
