package net.sf.anathema.hero.equipment.sheet.content;

import net.sf.anathema.hero.sheet.pdf.content.ForReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.Resources;

@ForReportContent(content = PossessionsContent.class)
public class PossessionsContentFactory implements ReportContentFactory<PossessionsContent> {

  private Resources resources;

  public PossessionsContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public PossessionsContent create(ReportSession session) {
    return new PossessionsContent(session.getHero(), resources);
  }
}
