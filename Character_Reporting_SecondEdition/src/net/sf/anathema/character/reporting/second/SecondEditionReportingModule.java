package net.sf.anathema.character.reporting.second;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderRegistry;
import net.sf.anathema.character.reporting.second.content.combat.CombatStatsContent;
import net.sf.anathema.character.reporting.second.content.combat.CombatStatsContentFactory;
import net.sf.anathema.character.reporting.second.rendering.combat.CombatBoxEncoderFactory;
import net.sf.anathema.character.reporting.second.rendering.health.HealthAndMovementBoxEncoderFactory;
import net.sf.anathema.lib.resources.IResources;

@CharacterModule
public class SecondEditionReportingModule extends NullObjectCharacterModuleAdapter {

  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    addReportContents(resources, moduleObject.getContentRegistry());
    addReportEncoders(moduleObject.getEncoderRegistry());
  }

  private void addReportEncoders(EncoderRegistry registry) {
    registry.add(new CombatBoxEncoderFactory());
    registry.add(new HealthAndMovementBoxEncoderFactory());
  }

  private void addReportContents(IResources resources, ReportContentRegistry registry) {
    registry.addFactory(CombatStatsContent.class, new CombatStatsContentFactory(resources));
  }
}
