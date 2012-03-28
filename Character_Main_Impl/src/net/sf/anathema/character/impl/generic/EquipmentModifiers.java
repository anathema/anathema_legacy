package net.sf.anathema.character.impl.generic;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate;
import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IDefensiveStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.model.ICharacterStatistics;

public class EquipmentModifiers implements IEquipmentModifiers {
  private final List<ITraitModifyingStats> stats = new ArrayList<ITraitModifyingStats>();
  private int mobilityPenalty;

  public EquipmentModifiers(ICharacterStatistics statistics) {
    IEquipmentAdditionalModel equipmentModel = (IEquipmentAdditionalModel) statistics.getCharacterContext().getAdditionalModel(
            IEquipmentAdditionalModelTemplate.ID);

    for (IEquipmentItem item : equipmentModel.getEquipmentItems())
      for (IEquipmentStats equipmentStats : item.getStats()) {
        if (equipmentStats instanceof ITraitModifyingStats && item.isPrintEnabled(equipmentStats))
          stats.add((ITraitModifyingStats) equipmentStats);
        if (equipmentStats instanceof IDefensiveStats && item.isPrintEnabled(equipmentStats))
          mobilityPenalty += ((IDefensiveStats) equipmentStats).getMobilityPenalty();
      }
  }

  @Override
  public int getMobilityPenalty() {
    return mobilityPenalty;
  }

  @Override
  public int getDDVMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getDDVMod() > highest ? stat.getDDVMod() : highest;
    return highest;
  }

  @Override
  public int getJoinBattleMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getJoinBattleMod() > highest ? stat.getJoinBattleMod() : highest;
    return highest;
  }

  @Override
  public int getJoinDebateMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getJoinDebateMod() > highest ? stat.getJoinDebateMod() : highest;
    return highest;
  }

  @Override
  public int getJoinWarMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getJoinWarMod() > highest ? stat.getJoinWarMod() : highest;
    return highest;
  }

  @Override
  public int getMDDVMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getMDDVMod() > highest ? stat.getMDDVMod() : highest;
    return highest;
  }

  @Override
  public int getMPDVMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getMPDVMod() > highest ? stat.getMPDVMod() : highest;
    return highest;
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
  public int getPDVMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getPDVMod() > highest ? stat.getPDVMod() : highest;
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
}
