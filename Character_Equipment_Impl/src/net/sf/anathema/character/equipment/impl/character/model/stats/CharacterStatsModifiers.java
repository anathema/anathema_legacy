package net.sf.anathema.character.equipment.impl.character.model.stats;

import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.equipment.ICharacterStatsModifiers;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IDefensiveStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate.ID;

public class CharacterStatsModifiers implements ICharacterStatsModifiers {
  private final List<ITraitModifyingStats> stats = new ArrayList<ITraitModifyingStats>();
  private int mobilityPenalty;

  public static CharacterStatsModifiers extractFromCharacter(IGenericCharacter character) {
    IEquipmentAdditionalModel model = (IEquipmentAdditionalModel) character.getAdditionalModel(ID);
    IEquipmentItem[] items = model.getEquipmentItems();
    List<IEquipmentItem> list = newArrayList(items);
    return new CharacterStatsModifiers(list);
  }

  public CharacterStatsModifiers(List<IEquipmentItem> equipmentItems) {
    for (IEquipmentItem item : equipmentItems)
      for (IEquipmentStats equipmentStats : item.getStats()) {
        boolean isDefensive = equipmentStats instanceof IDefensiveStats;
        boolean isModifier = equipmentStats instanceof ITraitModifyingStats;
        boolean isPrinted = item.isPrintEnabled(equipmentStats);
        if (isModifier && isPrinted) {
          stats.add((ITraitModifyingStats) equipmentStats);
        }
        if (isDefensive && isPrinted) {
          mobilityPenalty += ((IDefensiveStats) equipmentStats).getMobilityPenalty();
        }
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
  public int getPDVMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats)
      highest = stat.getPDVMod() > highest ? stat.getPDVMod() : highest;
    return highest;
  }
}