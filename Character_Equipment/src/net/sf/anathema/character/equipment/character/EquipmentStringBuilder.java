package net.sf.anathema.character.equipment.character;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.generic.equipment.weapon.IArmourStats;
import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IShieldStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.lib.resources.IResources;

public class EquipmentStringBuilder implements IEquipmentStringBuilder {

  private final IResources resources;

  public EquipmentStringBuilder(IResources resources) {
    this.resources = resources;
  }

  private String createWeaponString(IWeaponStats weapon) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(weapon.getName().getId());
    stringBuilder.append(":");
    stringBuilder.append(getStatsString("Speed", weapon.getSpeed(), false));
    stringBuilder.append(getStatsString("Accuracy", weapon.getAccuracy(), true));
    if (weapon.inflictsNoDamage()) {
      stringBuilder.append(" " + resources.getString("Equipment.Stats.Short.Damage") + ":-");
    }
    else {
      stringBuilder.append(getStatsString("Damage", weapon.getDamage(), weapon.getDamageTraitType() != null));
      stringBuilder.append(resources.getString("Weapons.Damage." + weapon.getDamageType().getId() + ".Short"));
    }
    stringBuilder.append(getStatsString("Defence", weapon.getDefence(), true));
    stringBuilder.append(getStatsString("Range", weapon.getRange(), false));
    stringBuilder.append(getStatsString("Rate", weapon.getRate(), false));
    return stringBuilder.toString();
  }

  private String getStatsString(String keyPart, Integer value, boolean printSignum) {
    if (value == null) {
      return "";
    }
    String signum = printSignum && value >= 0 ? "+" : "";
    return createtNewStatsStart(keyPart) + signum + value;
  }

  private String createtNewStatsStart(String keyPart) {
    return " " + resources.getString("Equipment.Stats.Short." + keyPart) + ":";
  }

  public String createString(IEquipmentStats equipment) {
    if (equipment instanceof IWeaponStats) {
      return createWeaponString((IWeaponStats) equipment);
    }
    if (equipment instanceof IArmourStats) {
      return createArmourString((IArmourStats) equipment);
    }
    if (equipment instanceof IShieldStats) {
      return createShieldString((IShieldStats) equipment);
    }
    throw new UnreachableCodeReachedException("All subclasses covered. Something appears to be wrong."); //$NON-NLS-1$
  }

  private String createShieldString(IShieldStats stats) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(stats.getName().getId());
    stringBuilder.append(":");
    stringBuilder.append(getStatsString("CloseDV", stats.getCloseCombatDvBonus(), true));
    stringBuilder.append(getStatsString("RangedDV", stats.getRangedCombatDvBonus(), true));
    stringBuilder.append(getStatsString("MobilityPenalty", stats.getMobilityPenalty(), false));
    stringBuilder.append(getStatsString("Fatigue", stats.getFatigue(), false));
    return stringBuilder.toString();
  }

  private String createArmourString(IArmourStats armourStats) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(armourStats.getName().getId());
    stringBuilder.append(":");
    stringBuilder.append(createtNewStatsStart("Soak"));
    stringBuilder.append(createArmourStat(armourStats.getSoak(HealthType.Bashing), "+"));
    stringBuilder.append("/");
    stringBuilder.append(createArmourStat(armourStats.getSoak(HealthType.Lethal), "+"));
    stringBuilder.append("/");
    stringBuilder.append(createArmourStat(armourStats.getSoak(HealthType.Aggravated), "+"));
    stringBuilder.append(createtNewStatsStart("Hardness"));
    stringBuilder.append(createArmourStat(armourStats.getHardness(HealthType.Bashing), ""));
    stringBuilder.append("/");
    stringBuilder.append(createArmourStat(armourStats.getHardness(HealthType.Lethal), ""));
    stringBuilder.append("/");
    stringBuilder.append(createArmourStat(armourStats.getHardness(HealthType.Aggravated), "-"));
    stringBuilder.append(getStatsString("MobilityPenalty", armourStats.getMobilityPenalty(), false));
    stringBuilder.append(getStatsString("Fatigue", armourStats.getFatigue(), false));
    return stringBuilder.toString();
  }

  private CharSequence createArmourStat(Integer soak, String prefix) {
    return soak == null ? "-" : prefix + soak;
  }
}