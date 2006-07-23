package net.sf.anathema.character.equipment.impl.character.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.character.model.IEquipmentTemplate;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;

public class EquipmentAdditionalModel extends AbstractEquipmentAdditionalModel {
  private final IEquipmentTemplate[] availableTemplates;
  private final IArmourStats naturalArmour;

  public EquipmentAdditionalModel(
      IArmourStats naturalArmour,
      IEquipmentTemplate naturalWeapons,
      IEquipmentTemplate[] availableTemplates) {
    this.naturalArmour = naturalArmour;
    this.availableTemplates = availableTemplates;
    if (naturalWeapons != null) {
      addEquipmentObject(naturalWeapons);
    }
  }

  public IArmourStats[] getPrintArmours() {
    List<IArmourStats> printStats = new ArrayList<IArmourStats>();
    printStats.add(naturalArmour);
    for (IEquipmentItem item : getEquipmentItems()) {
      for (IEquipmentStats stats : item.getEquipments()) {
        if (stats instanceof IArmourStats && item.isPrintEnabled(stats)) {
          printStats.add((IArmourStats) stats);
        }
      }
    }
    return printStats.toArray(new IArmourStats[printStats.size()]);
  }

  public IWeaponStats[] getPrintWeapons() {
    List<IWeaponStats> printStats = new ArrayList<IWeaponStats>();
    for (IEquipmentItem item : getEquipmentItems()) {
      for (IEquipmentStats stats : item.getEquipments()) {
        if (stats instanceof IWeaponStats && item.isPrintEnabled(stats)) {
          printStats.add((IWeaponStats) stats);
        }
      }
    }
    return printStats.toArray(new IWeaponStats[printStats.size()]);
  }

  public IEquipmentTemplate[] getAvailableTemplates() {
    return availableTemplates;
  }
}