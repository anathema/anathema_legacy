package net.sf.anathema.character.impl.generic;

import net.sf.anathema.character.equipment.character.model.IEquipmentAdditionalModel;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.ITraitModifyingStats;
import net.sf.anathema.character.generic.equipment.weapon.IDefensiveStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.model.ICharacterStatistics;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static net.sf.anathema.character.equipment.IEquipmentAdditionalModelTemplate.ID;

public class EquipmentModifiers implements IEquipmentModifiers {
  private final List<ITraitModifyingStats> stats = new ArrayList<ITraitModifyingStats>();
  private int mobilityPenalty;

  public static EquipmentModifiers extractFromStatistics(ICharacterStatistics statistics) {
    ICharacterModelContext characterContext = statistics.getCharacterContext();
    IEquipmentAdditionalModel model = (IEquipmentAdditionalModel) characterContext.getAdditionalModel(ID);
    IEquipmentItem[] items = model.getEquipmentItems();
    List<IEquipmentItem> list = newArrayList(items);
    return new EquipmentModifiers(list);
  }

  public EquipmentModifiers(List<IEquipmentItem> equipmentItems) {
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
}