package net.sf.anathema.character.equipment.item.model.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.character.equipment.character.model.EquipmentTemplate;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.equipment.core.IEquipmentTemplate;
import net.sf.anathema.lib.util.Identifier;

public class EquipmentGson {

  private Gson gson;

  public EquipmentGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(IEquipmentStats.class, new StatsAdapter());
    gsonBuilder.registerTypeAdapter(Identifier.class, new IdentificateAdapter());
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