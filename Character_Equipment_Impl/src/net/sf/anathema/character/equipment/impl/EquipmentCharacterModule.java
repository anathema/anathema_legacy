package net.sf.anathema.character.equipment.impl;

import net.sf.anathema.character.equipment.impl.character.EquipmentAdditionalModelFactory;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditionalModelPersisterFactory;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditonalModelTemplate;
import net.sf.anathema.character.equipment.impl.reporting.second.SecondEditionArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.second.SecondEditionWeaponryEncoder;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObjectMap;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.sheet.SecondEditionEncodingRegistry;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentCharacterModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) {
    characterGenerics.getAdditionalModelFactoryRegistry().register(
        EquipmentAdditonalModelTemplate.ID,
        new EquipmentAdditionalModelFactory());
    characterGenerics.getAdditonalPersisterFactoryRegistry().register(
        EquipmentAdditonalModelTemplate.ID,
        new EquipmentAdditionalModelPersisterFactory());
    characterGenerics.getGlobalAdditionalTemplateRegistry().add(new EquipmentAdditonalModelTemplate());
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    ICharacterModuleObjectMap moduleMap = generics.getModuleObjectMap();
    CharacterReportingModuleObject moduleObject = moduleMap.getModuleObject(CharacterReportingModule.class);
    SecondEditionEncodingRegistry registry = moduleObject.getSecondEditionEncodingRegistry();
    fillSecondEditionEncodingRegistry(resources, registry);
  }

  private void fillSecondEditionEncodingRegistry(IResources resources, SecondEditionEncodingRegistry registry) {
    registry.setArmourContentEncoder(new SecondEditionArmourEncoder(resources, registry.getBaseFont()));
    registry.setWeaponContentEncoder(new SecondEditionWeaponryEncoder(resources, registry.getBaseFont()));
  }
}