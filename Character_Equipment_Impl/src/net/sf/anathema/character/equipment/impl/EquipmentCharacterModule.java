package net.sf.anathema.character.equipment.impl;

import com.db4o.ext.DatabaseFileLockedException;
import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditionalModelFactory;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditionalPersisterFactory;
import net.sf.anathema.character.equipment.impl.character.EquipmentAdditionalViewFactory;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.impl.item.model.db4o.Db4OEquipmentDatabase;
import net.sf.anathema.character.equipment.item.model.IEquipmentTemplateProvider;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.initialization.InitializationException;

import java.io.File;

@CharacterModule
public class EquipmentCharacterModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void addAdditionalTemplateData(ICharacterGenerics characterGenerics) throws InitializationException {
    File dataBaseFile = new File(characterGenerics.getDataFileProvider().getDataBaseDirectory(Db4OEquipmentDatabase.DATABASE_FOLDER), Db4OEquipmentDatabase.DATABASE_FILE);
    IEquipmentTemplateProvider equipmentDatabase;
    try {
      equipmentDatabase = new Db4OEquipmentDatabase(dataBaseFile);
    } catch (DatabaseFileLockedException e) {
      throw new InitializationException("Equipment database locked.\nAnathema may already be running.", e); //$NON-NLS-1$
    }
    characterGenerics.getAdditionalModelFactoryRegistry().register(IEquipmentAdditionalModelTemplate.ID, new EquipmentAdditionalModelFactory(equipmentDatabase));
    characterGenerics.getAdditonalPersisterFactoryRegistry().register(IEquipmentAdditionalModelTemplate.ID, new EquipmentAdditionalPersisterFactory());
    characterGenerics.getAdditionalViewFactoryRegistry().register(IEquipmentAdditionalModelTemplate.ID, new EquipmentAdditionalViewFactory());
    characterGenerics.getGlobalAdditionalTemplateRegistry().add(new EquipmentAdditionalModelTemplate());
  }
}