package net.sf.anathema.character.reporting.first;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.content.ReportContentRegistry;
import net.sf.anathema.character.reporting.pdf.content.combat.FirstEditionCombatStatsContent;
import net.sf.anathema.character.reporting.pdf.content.combat.FirstEditionCombatStatsContentFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderRegistry;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.combat.FirstEditionCombatBoxEncoderFactory;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionReportingModule extends NullObjectCharacterModuleAdapter {

  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    addReportContents(resources, moduleObject.getReportContentRegistry());
    addReportEncoders(moduleObject.getEncoderRegistry());
  }

  private void addReportEncoders(BoxContentEncoderRegistry registry) {
    registry.add(new FirstEditionCombatBoxEncoderFactory());
  }

  private void addReportContents(IResources resources, ReportContentRegistry registry) {
    registry.addFactory(FirstEditionCombatStatsContent.class, new FirstEditionCombatStatsContentFactory(resources));
  }
}
