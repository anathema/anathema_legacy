package net.sf.anathema.character.equipment.impl.reporting.content;

import net.sf.anathema.hero.sheet.pdf.content.RegisteredReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = PossessionsContent.class)
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
