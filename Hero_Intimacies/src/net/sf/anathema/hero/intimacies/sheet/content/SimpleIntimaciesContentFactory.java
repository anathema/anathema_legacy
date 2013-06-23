package net.sf.anathema.hero.intimacies.sheet.content;

import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

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
