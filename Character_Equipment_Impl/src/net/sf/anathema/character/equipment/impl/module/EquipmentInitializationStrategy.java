package net.sf.anathema.character.equipment.impl.module;

import net.sf.anathema.character.equipment.impl.item.model.gson.EquipmentGson;
import net.sf.anathema.character.equipment.impl.item.model.gson.GsonEquipmentDatabase;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.initialitems.RepositoryItemInitializationStrategy;

public class EquipmentInitializationStrategy implements RepositoryItemInitializationStrategy {
  private final static String EQUIPMENT_REGEX = "^.*\\.item$";

  private final EquipmentGson gson = new EquipmentGson();
  private final GsonEquipmentDatabase database;

  public EquipmentInitializationStrategy(IApplicationModel anathemaModel) {
    database = GsonEquipmentDatabase.CreateFrom(anathemaModel);
  }

  public boolean shouldInitializeData() {
    return database.isEmpty();
  }

  public void storeInRepository(String itemJSON) {
    IEquipmentTemplate iEquipmentTemplate = gson.fromJson(itemJSON);
    database.saveTemplateNoOverwrite(iEquipmentTemplate);
  }

  public String getMessageKey() {
    return "Equipment.Bootjob.DefaultDatabaseSplashmessage";
  }

  public String getItemPattern() {
    return EQUIPMENT_REGEX;
  }
}
