package net.sf.anathema.herotype.solar.sheet.curse;

import net.sf.anathema.hero.sheet.pdf.content.ForReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@ForReportContent(content = VirtueFlawContent.class)
public class VirtueFlawContentFactory implements ReportContentFactory<VirtueFlawContent> {

  private Resources resources;

  public VirtueFlawContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public VirtueFlawContent create(ReportSession session) {
    return new VirtueFlawContent(session.getHero(), resources);
  }
}
