package net.sf.anathema.character.reporting.text;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Paragraph;
import net.sf.anathema.framework.reporting.ITextReportUtils;
import net.sf.anathema.lib.resources.IResources;

public class AbstractTextEncoder {
  private final ITextReportUtils utils;
  private final IResources resources;
  private final TextPartFactory factory;

  public AbstractTextEncoder(ITextReportUtils utils, IResources resources) {
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

  protected final ITextReportUtils getUtils() {
    return utils;
  }

  protected final String getString(String key) {
    return resources.getString(key);
  }
}
