package net.sf.anathema.character.reporting.pdf.content.combat;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionCombatStatsContentFactory implements ReportContentFactory<FirstEditionCombatStatsContent> {
  private IResources resources;

  public FirstEditionCombatStatsContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public FirstEditionCombatStatsContent create(IGenericCharacter character, IGenericDescription description) {
    return new FirstEditionCombatStatsContent(character, resources);
  }
}
