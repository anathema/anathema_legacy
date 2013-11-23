package net.sf.anathema.hero.sheet.pdf.content;

import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@ForReportContent(content = BasicContent.class)
public class BasicContentFactory implements ReportContentFactory<BasicContent> {

  @SuppressWarnings("UnusedParameters")
  public BasicContentFactory(Resources resources) {
    //Just to adhere to protocol
  }

  @Override
  public BasicContent create(ReportSession session) {
    return new BasicContent(session.getHero());
  }
}
