package net.sf.anathema.character.equipment.impl.character.model.natural;

import net.sf.anathema.character.equipment.character.model.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

public class NaturalWeaponTemplate implements IEquipmentTemplate {

  private IEquipmentStats[] equipmentStats = new IEquipmentStats[] { new Punch(), new Kick(), new Clinch() };

  public String getDescription() {
    return "The Characters natural weapons";
  }

  public IEquipmentStats[] getEquipmentStats() {
    return equipmentStats;
  }

  public String getName() {
    return "Natural Weapons";
  }
}