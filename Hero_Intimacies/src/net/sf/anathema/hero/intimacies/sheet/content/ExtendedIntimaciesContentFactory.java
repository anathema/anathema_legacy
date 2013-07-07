package net.sf.anathema.hero.intimacies.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.RegisteredReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = ExtendedIntimaciesContent.class)
public class ExtendedIntimaciesContentFactory implements ReportContentFactory<ExtendedIntimaciesContent> {
  private Resources resources;

  public ExtendedIntimaciesContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public ExtendedIntimaciesContent create(ReportSession session) {
    return new ExtendedIntimaciesContent(session.getHero(), resources);
  }
}
