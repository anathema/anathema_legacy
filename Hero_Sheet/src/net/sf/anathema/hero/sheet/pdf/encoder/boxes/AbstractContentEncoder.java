package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.hero.sheet.pdf.content.SubBoxContent;

public abstract class AbstractContentEncoder<C extends SubBoxContent> implements ContentEncoder {

  private Class<C> contentClass;

  protected AbstractContentEncoder(Class<C> contentClass) {
    this.contentClass = contentClass;
  }

  @Override
  public final boolean hasContent(ReportSession session) {
    return createContent(session).hasContent();
  }

  @Override
  public String getHeader(ReportSession session) {
    return createContent(session).getHeader();
  }

  protected final C createContent(ReportSession session) {
    return session.createContent(contentClass);
  }
}
