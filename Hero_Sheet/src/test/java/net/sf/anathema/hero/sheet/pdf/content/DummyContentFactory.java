package net.sf.anathema.hero.sheet.pdf.content;

import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

@DoNotInstantiateAutomatically
@ForReportContent(content = DummyContent.class)
public class DummyContentFactory implements ReportContentFactory<DummyContent> {

  @Override
  public DummyContent create(ReportSession session) {
    return new DummyContent();
  }
}