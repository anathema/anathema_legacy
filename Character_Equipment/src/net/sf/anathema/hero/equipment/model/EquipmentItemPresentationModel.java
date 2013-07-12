package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.model.BooleanModel;

import java.util.HashMap;
import java.util.Map;

public class EquipmentItemPresentationModel {

  private final Map<IEquipmentStats, BooleanModel> attuneStatFlags = new HashMap<>();
  private final Map<IEquipmentStats, BooleanModel> otherStatFlags = new HashMap<>();

  public void clear() {
    attuneStatFlags.clear();
    otherStatFlags.clear();
  }

  public void registerViewForAttunementStats(IEquipmentStats equipment, BooleanModel booleanModel) {
    attuneStatFlags.put(equipment, booleanModel);
  }

  public void registerViewForDefaultStats(IEquipmentStats equipment, BooleanModel booleanModel) {
    otherStatFlags.put(equipment, booleanModel);
  }

  public Iterable<IEquipmentStats> getAttunementStats() {
    return attuneStatFlags.keySet();
  }

  public Iterable<BooleanModel> getDefaultStatViews() {
    return otherStatFlags.values();
  }
}
