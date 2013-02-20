package net.sf.anathema.character.equipment.impl;

import net.sf.anathema.character.equipment.MaterialRules;
import net.sf.anathema.character.equipment.ReflectionMaterialRules;
import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditionalModelFactory;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditionalPersisterFactory;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditionalViewFactory;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.impl.item.model.gson.GsonEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;

import java.nio.file.Path;

import static net.sf.anathema.character.equipment.impl.item.model.gson.GsonEquipmentDatabase.DATABASE_FOLDER;

@CharacterModule
public class EquipmentCharacterModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) throws InitializationException {
    Path dataBaseDirectory = characterGenerics.getDataFileProvider().getDataBaseDirectory(DATABASE_FOLDER);
    EquipmentDirectAccess access = new EquipmentDirectAccess(dataBaseDirectory);
    Instantiater instantiater = characterGenerics.getInstantiater();
    IEquipmentTemplateProvider equipmentDatabase = new GsonEquipmentDatabase(access);
    MaterialRules materialRules = new ReflectionMaterialRules(instantiater);
    characterGenerics.getAdditionalModelFactoryRegistry().register(IEquipmentAdditionalModelTemplate.ID,
            new EquipmentAdditionalModelFactory(equipmentDatabase, materialRules));
    characterGenerics.getAdditonalPersisterFactoryRegistry().register(IEquipmentAdditionalModelTemplate.ID,
            new EquipmentAdditionalPersisterFactory());
    characterGenerics.getAdditionalViewFactoryRegistry().register(IEquipmentAdditionalModelTemplate.ID,
            new EquipmentAdditionalViewFactory());
    characterGenerics.getGlobalAdditionalTemplateRegistry().add(
            new EquipmentAdditionalModelTemplate(characterGenerics.getCharacterTypes(), instantiater));
  }
}