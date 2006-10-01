package net.sf.anathema.character.generic.framework;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.module.CharacterModuleContainer;
import net.sf.anathema.character.generic.framework.module.ICharacterModule;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.initialization.repository.IDataFileProvider;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

public class CharacterModuleContainerInitializer {

  private final Logger logger = Logger.getLogger(CharacterModuleContainerInitializer.class);

  private final List<String> moduleNameList = new ArrayList<String>() {
    {
      add("net.sf.anathema.character.reporting.CharacterReportingModule"); //$NON-NLS-1$
      add("net.sf.anathema.character.equipment.impl.EquipmentCharacterModule"); //$NON-NLS-1$
      add("net.sf.anathema.character.meritsflaws.MeritsFlawsModule"); //$NON-NLS-1$
      add("net.sf.anathema.character.impl.specialties.SpecialtiesModule"); //$NON-NLS-1$
      add("net.sf.anathema.character.craft.CraftModule"); //$NON-NLS-1$
      add("net.sf.anathema.character.intimacies.IntimaciesModule"); //$NON-NLS-1$
      add("net.sf.anathema.character.mortal.MortalCharacterModule"); //$NON-NLS-1$
      add("net.sf.anathema.character.abyssal.AbyssalCharacterModule"); //$NON-NLS-1$
      add("net.sf.anathema.character.db.DbCharacterModule"); //$NON-NLS-1$
      add("net.sf.anathema.character.lunar.LunarCharacterModule"); //$NON-NLS-1$
      add("net.sf.anathema.character.solar.SolarCharacterModule"); //$NON-NLS-1$
      add("net.sf.anathema.character.sidereal.SiderealCharacterModule"); //$NON-NLS-1$
    }
  };

  public CharacterModuleContainer initContainer(IResources resources, IDataFileProvider dataFileProvider) {
    CharacterModuleContainer container = new CharacterModuleContainer(resources, dataFileProvider);
    for (String moduleName : moduleNameList) {
      addModule(container, moduleName);
    }
    return container;
  }

  private void addModule(CharacterModuleContainer container, String moduleName) {
    try {
      Object clazz = Class.forName(moduleName).newInstance();
      ICharacterModule< ? extends ICharacterModuleObject> module = (ICharacterModule< ? extends ICharacterModuleObject>) clazz;
      container.addCharacterGenericsModule(module);
    }
    catch (ClassNotFoundException e) {
      logger.info(moduleName + " not installed."); //$NON-NLS-1$
    }
    catch (Exception e) {
      logger.error("Error initializing module " + moduleName, e); //$NON-NLS-1$
    }
  }
}