package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.content.SubBoxContent;

public abstract class AbstractBoxContentEncoder<C extends SubBoxContent> implements ContentEncoder {

  private Class<C> contentClass;

  protected AbstractBoxContentEncoder(Class<C> contentClass) {
    this.contentClass = contentClass;
  }

  @Override
  public final boolean hasContent(ReportContent content) {
    return createContent(content).hasContent();
  }

  @Override
  public String getHeader(ReportContent content) {
    return createContent(content).getHeader();
  }

  protected final C createContent(ReportContent content) {
    return content.createSubContent(contentClass);
  }
}
