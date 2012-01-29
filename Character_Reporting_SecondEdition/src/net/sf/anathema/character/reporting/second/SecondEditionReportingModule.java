package net.sf.anathema.character.reporting.second;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.second.content.combat.CombatStatsContent;
import net.sf.anathema.character.reporting.second.content.combat.CombatStatsContentFactory;
import net.sf.anathema.lib.resources.IResources;

@CharacterModule
public class SecondEditionReportingModule extends NullObjectCharacterModuleAdapter {

  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    addReportContents(resources, moduleObject.getContentRegistry());
  }

  private void addReportContents(IResources resources, ReportContentRegistry registry) {
    registry.addFactory(CombatStatsContent.class, new CombatStatsContentFactory(resources));
  }
}
