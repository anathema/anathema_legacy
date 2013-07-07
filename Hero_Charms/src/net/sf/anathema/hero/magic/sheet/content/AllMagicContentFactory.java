package net.sf.anathema.hero.magic.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.RegisteredReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = AllMagicContent.class)
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
