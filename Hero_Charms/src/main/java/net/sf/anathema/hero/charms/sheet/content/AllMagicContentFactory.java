package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.sheet.pdf.content.ForReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

@ForReportContent(content = AllMagicContent.class)
public class AllMagicContentFactory implements ReportContentFactory<AllMagicContent> {

  private Resources resources;

  public AllMagicContentFactory(Resources resources) {
      this.resources = resources;
    }

  @Override
  public AllMagicContent create(ReportSession session) {
    return new AllMagicContent(session, resources);
  }
}
