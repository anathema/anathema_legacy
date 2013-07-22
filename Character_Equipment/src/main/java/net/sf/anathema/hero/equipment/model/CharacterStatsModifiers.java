package net.sf.anathema.hero.equipment.model;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.main.util.HeroStatsModifiers;
import net.sf.anathema.hero.equipment.sheet.content.stats.ITraitModifyingStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IArmourStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IEquipmentStats;
import net.sf.anathema.hero.equipment.sheet.content.stats.weapon.IWeaponStats;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.hero.equipment.EquipmentModelFetcher;
import net.sf.anathema.hero.model.Hero;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class CharacterStatsModifiers implements HeroStatsModifiers {
  private final List<ITraitModifyingStats> stats = new ArrayList<>();
  private int mobilityPenalty;

  public static CharacterStatsModifiers extractFromCharacter(Hero hero) {
    EquipmentModel model = EquipmentModelFetcher.fetch(hero);
    IEquipmentItem[] items = model.getEquipmentItems();
    List<IEquipmentItem> list = newArrayList(items);
    return new CharacterStatsModifiers(list);
  }

  public CharacterStatsModifiers(List<IEquipmentItem> equipmentItems) {
    for (IEquipmentItem item : equipmentItems) {
      for (IEquipmentStats equipmentStats : item.getStats()) {
        boolean isDefensive = equipmentStats instanceof IArmourStats;
        boolean isShield = equipmentStats instanceof IWeaponStats;
        boolean isModifier = equipmentStats instanceof ITraitModifyingStats;
        boolean isPrinted = item.isPrintEnabled(equipmentStats);
        if (isModifier && isPrinted) {
          stats.add((ITraitModifyingStats) equipmentStats);
        }
        if (isDefensive && isPrinted) {
          mobilityPenalty += ((IArmourStats) equipmentStats).getMobilityPenalty();
        }
        if (isShield && isPrinted) {
          mobilityPenalty += ((IWeaponStats) equipmentStats).getMobilityPenalty();
        }
      }
    }
  }

  @Override
  public int getMobilityPenalty() {
    return mobilityPenalty;
  }

  @Override
  public int getDDVPoolMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getDDVPoolMod() > highest ? stat.getDDVPoolMod() : highest;
    }
    return highest;
  }

  @Override
  public int getJoinBattleMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getJoinBattleMod() > highest ? stat.getJoinBattleMod() : highest;
    }
    return highest;
  }

  @Override
  public int getJoinDebateMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getJoinDebateMod() > highest ? stat.getJoinDebateMod() : highest;
    }
    return highest;
  }

  @Override
  public int getJoinWarMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getJoinWarMod() > highest ? stat.getJoinWarMod() : highest;
    }
    return highest;
  }

  @Override
  public int getMDDVPoolMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getMDDVPoolMod() > highest ? stat.getMDDVPoolMod() : highest;
    }
    return highest;
  }

  @Override
  public int getMPDVPoolMod() {
    int highest = 0;
    for (ITraitModifyingStats stat : stats) {
      highest = stat.getMPDVPoolMod() > highest ? stat.getMPDVPoolMod() : highest;
    }
    return highest;
  }
}