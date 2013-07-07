package net.sf.anathema.character.reporting.second.content.combat;

import net.sf.anathema.hero.sheet.pdf.content.RegisteredReportContent;
import net.sf.anathema.hero.sheet.pdf.content.ReportContentFactory;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = CombatStatsContent.class)
public class CombatStatsContentFactory implements ReportContentFactory<CombatStatsContent> {
  private Resources resources;

  public CombatStatsContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public CombatStatsContent create(ReportSession session) {
    return new CombatStatsContent(session.getHero(), resources);
  }
}
