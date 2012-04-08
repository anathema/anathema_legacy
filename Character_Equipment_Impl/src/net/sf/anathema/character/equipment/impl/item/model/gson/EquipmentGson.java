package net.sf.anathema.character.equipment.impl.item.model.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.character.equipment.impl.character.model.EquipmentTemplate;
import net.sf.anathema.character.equipment.item.model.ICollectionFactory;
import net.sf.anathema.character.equipment.template.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.util.IIdentificate;

public class EquipmentGson {

  private Gson gson;

  public EquipmentGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(IEquipmentStats.class, new StatsAdapter());
    gsonBuilder.registerTypeAdapter(ICollectionFactory.class, new CollectionFactoryFactory());
    gsonBuilder.registerTypeAdapter(IIdentificate.class, new IdentificateAdapter());
    gsonBuilder.setPrettyPrinting();
    gson = gsonBuilder.create();
  }

  public String toJson(IEquipmentTemplate template) {
    return gson.toJson(template);
  }

  public IEquipmentTemplate fromJson(String json) {
    return gson.fromJson(json, EquipmentTemplate.class);
  }
}