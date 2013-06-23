package net.sf.anathema.character.reporting.second.content.combat;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.character.reporting.pdf.content.RegisteredReportContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.lib.resources.Resources;

@RegisteredReportContent(produces = CombatStatsContent.class)
public class CombatStatsContentFactory implements ReportContentFactory<CombatStatsContent> {
  private Resources resources;

  public CombatStatsContentFactory(Resources resources) {
    this.resources = resources;
  }

  @Override
  public CombatStatsContent create(ReportSession session, IGenericCharacter character, Hero hero) {
    return new CombatStatsContent(hero, character, resources);
  }
}
