package net.sf.anathema.hero.intimacies.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.RegisteredReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@RegisteredReportContent(produces = SimpleIntimaciesContent.class)
public class SimpleIntimaciesContentFactory implements ReportContentFactory<SimpleIntimaciesContent> {
  private Resources resources;

  public SimpleIntimaciesContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override

  public SimpleIntimaciesContent create(ReportSession session) {
    return new SimpleIntimaciesContent(session.getHero(), resources);
  }
}
