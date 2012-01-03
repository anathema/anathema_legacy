package net.sf.anathema.character.reporting.second.content.combat;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class CombatStatsContentFactory implements ReportContentFactory<CombatStatsContent> {
  private IResources resources;

  public CombatStatsContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public CombatStatsContent create(IGenericCharacter character, IGenericDescription description) {
    return new CombatStatsContent(character, resources);
  }
}
