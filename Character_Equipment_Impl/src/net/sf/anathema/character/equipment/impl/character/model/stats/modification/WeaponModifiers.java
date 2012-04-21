package net.sf.anathema.character.equipment.impl.character.model.stats.modification;

import net.sf.anathema.character.equipment.IWeaponModifiers;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

import java.util.ArrayList;
import java.util.List;

public class WeaponModifiers implements IWeaponModifiers {
  private final List<ITraitModifyingStats> stats = new ArrayList<ITraitModifyingStats>();

  public WeaponModifiers(List<IEquipmentItem> equipmentItems) {
    for (IEquipmentItem item : equipmentItems)
      for (IEquipmentStats equipmentStats : item.getStats()) {
        boolean isModifier = equipmentStats instanceof ITraitModifyingStats;
        boolean isPrinted = item.isPrintEnabled(equipmentStats);
        if (isModifier && isPrinted) {
          stats.add((ITraitModifyingStats) equipmentStats);
        }
      }
  }

  @Override
  public int getMeleeAccuracyMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getMeleeAccuracyMod() > highest ? stat.getMeleeAccuracyMod() : highest;
    return highest;
  }

  @Override
  public int getMeleeDamageMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getMeleeDamageMod() > highest ? stat.getMeleeDamageMod() : highest;
    return highest;
  }

  @Override
  public int getMeleeRateMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getMeleeRateMod() > highest ? stat.getMeleeRateMod() : highest;
    return highest;
  }

  @Override
  public int getMeleeSpeedMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getMeleeSpeedMod() < highest ? stat.getMeleeSpeedMod() : highest;
    return highest;
  }

  @Override
  public int getRangedAccuracyMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getRangedAccuracyMod() > highest ? stat.getRangedAccuracyMod() : highest;
    return highest;
  }

  @Override
  public int getRangedDamageMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getRangedDamageMod() > highest ? stat.getRangedDamageMod() : highest;
    return highest;
  }

  @Override
  public int getRangedRateMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getRangedRateMod() > highest ? stat.getRangedRateMod() : highest;
    return highest;
  }

  @Override
  public int getRangedSpeedMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getRangedSpeedMod() < highest ? stat.getRangedSpeedMod() : highest;
    return highest;
  }

  @Override
  public int getPDVPoolMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getPDVPoolMod() > highest ? stat.getPDVPoolMod() : highest;
    return highest;
  }
}