package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.lib.model.IModifiableBooleanModel;

import java.util.HashMap;
import java.util.Map;

public class EquipmentItemPresentationModel {

  private final Map<IEquipmentStats, IModifiableBooleanModel> attuneStatFlags = new HashMap<>();
  private final Map<IEquipmentStats, IModifiableBooleanModel> otherStatFlags = new HashMap<>();

  public void clear() {
    attuneStatFlags.clear();
    otherStatFlags.clear();
  }

  public void registerViewForAttunementStats(IEquipmentStats equipment, IModifiableBooleanModel booleanModel) {
    attuneStatFlags.put(equipment, booleanModel);
  }

  public void registerViewForDefaultStats(IEquipmentStats equipment, IModifiableBooleanModel booleanModel) {
    otherStatFlags.put(equipment, booleanModel);
  }

  public Iterable<IEquipmentStats> getAttunementStats() {
    return attuneStatFlags.keySet();
  }

  public Iterable<IModifiableBooleanModel> getDefaultStatViews() {
    return otherStatFlags.values();
  }
}
