package net.sf.anathema.character.equipment.impl;

import java.io.File;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditionalModelFactory;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditionalPersisterFactory;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditionalViewFactory;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.impl.item.model.db4o.Db4OEquipmentDatabase;
import net.sf.anathema.character.equipment.impl.reporting.ArmourEncoder;
import net.sf.anathema.character.equipment.impl.reporting.ArmourTableEncoder;
import net.sf.anathema.character.equipment.impl.reporting.PossessionsEncoder;
import net.sf.anathema.character.equipment.impl.reporting.WeaponryEncoder;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.reporting.CharacterReportingModule;
import net.sf.anathema.character.reporting.CharacterReportingModuleObject;
import net.sf.anathema.character.reporting.pdf.layout.extended.IEncodingRegistry;
import net.sf.anathema.character.reporting.pdf.layout.simple.SimpleEncodingRegistry;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.resources.IResources;

import com.db4o.ext.DatabaseFileLockedException;

public class EquipmentCharacterModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) throws InitializationException {
    File dataBaseFile = new File(characterGenerics.getDataFileProvider().getDataBaseDirectory(
        Db4OEquipmentDatabase.DATABASE_FOLDER), Db4OEquipmentDatabase.DATABASE_FILE);
    IEquipmentTemplateProvider equipmentDatabase;
    try {
      equipmentDatabase = new Db4OEquipmentDatabase(dataBaseFile);
    }
    catch (DatabaseFileLockedException e) {
      throw new InitializationException("Equipment database locked.\nAnathema may already be running.", e); //$NON-NLS-1$
    }
    characterGenerics.getAdditionalModelFactoryRegistry().register(
        IEquipmentAdditionalModelTemplate.ID,
        new EquipmentAdditionalModelFactory(equipmentDatabase));
    characterGenerics.getAdditonalPersisterFactoryRegistry().register(
        IEquipmentAdditionalModelTemplate.ID,
        new EquipmentAdditionalPersisterFactory());
    characterGenerics.getAdditionalViewFactoryRegistry().register(
        IEquipmentAdditionalModelTemplate.ID,
        new EquipmentAdditionalViewFactory());
    characterGenerics.getGlobalAdditionalTemplateRegistry().add(new EquipmentAdditionalModelTemplate());
  }

  @Override
  public void addReportTemplates(ICharacterGenerics generics, IResources resources) {
    CharacterReportingModuleObject moduleObject = generics.getModuleObjectMap().getModuleObject(CharacterReportingModule.class);
    registerEncoders(resources, moduleObject.getExtendedEncodingRegistry());
    registerEncoders(resources, moduleObject.getSimpleEncodingRegistry());
  }

  private void registerEncoders(IResources resources, IEncodingRegistry registry) {
    registry.setArmourContentEncoder(new ArmourEncoder(resources, registry.getBaseFont(), new ArmourTableEncoder(registry.getBaseFont(), resources)));
    registry.setWeaponContentEncoder(new WeaponryEncoder(resources, registry.getBaseFont()));
    registry.setPossessionsEncoder(new PossessionsEncoder(registry.getBaseFont()));
  }
}
