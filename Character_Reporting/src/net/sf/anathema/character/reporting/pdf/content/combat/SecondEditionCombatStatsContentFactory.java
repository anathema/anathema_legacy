package net.sf.anathema.character.reporting.pdf.content.combat;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.content.ReportContentFactory;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionCombatStatsContentFactory implements ReportContentFactory<SecondEditionCombatStatsContent> {
  private IResources resources;

  public SecondEditionCombatStatsContentFactory(IResources resources) {
    this.resources = resources;
  }

  @Override
  public SecondEditionCombatStatsContent create(IGenericCharacter character, IGenericDescription description) {
    return new SecondEditionCombatStatsContent(character, resources);
  }
}
