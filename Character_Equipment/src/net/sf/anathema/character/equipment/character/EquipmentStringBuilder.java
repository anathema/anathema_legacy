package net.sf.anathema.character.equipment.character;

import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.main.equipment.ArtifactStats;
import net.sf.anathema.character.main.equipment.ITraitModifyingStats;
import net.sf.anathema.character.main.equipment.weapon.IArmourStats;
import net.sf.anathema.character.main.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.main.equipment.weapon.IWeaponStats;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.lib.exception.UnreachableCodeReachedException;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class EquipmentStringBuilder implements IEquipmentStringBuilder {

  private final Resources resources;

  public EquipmentStringBuilder(Resources resources) {
    this.resources = resources;
  }

  private String createWeaponString(IEquipmentItem item, IWeaponStats weapon) {
    StringBuilder stringBuilder = new StringBuilder();
    String key = EquipmentObjectPresenter.EQUIPMENT_NAME_PREFIX + weapon.getName().getId();
    if (resources.supportsKey(key)) {
      stringBuilder.append(resources.getString(key));
    } else {
      stringBuilder.append(new WeaponStatsNameStringFactory(resources).create(item, weapon));
    }
    stringBuilder.append(":");
    stringBuilder.append(getStatsString("Speed", weapon.getSpeed(), false));
    stringBuilder.append(getStatsString("Accuracy", weapon.getAccuracy(), true));
    if (weapon.inflictsNoDamage()) {
      stringBuilder.append(" ").append(resources.getString("Equipment.Stats.Short.Damage")).append(":-");
    } else {
      stringBuilder.append(getStatsString("Damage", weapon.getDamage(), weapon.getDamageTraitType() != null));
      stringBuilder.append(resources.getString("HealthType." + weapon.getDamageType().getId() + ".Short"));
      if (weapon.getMinimumDamage() > 1)
    	  stringBuilder.append("/").append(weapon.getMinimumDamage());
    }
    stringBuilder.append(getStatsString("Defence", weapon.getDefence(), true));
    stringBuilder.append(getStatsString("Range", weapon.getRange(), false));
    stringBuilder.append(getStatsString("Rate", weapon.getRate(), false));
    stringBuilder.append( getTagsString( weapon.getTags() ) );
    
    return stringBuilder.toString();
  }

  private String getStatsString(String keyPart, Integer value, boolean printSignum) {
    if (value == null) {
      return "";
    }
    String signum = printSignum && value >= 0 ? "+" : "";
    return createtNewStatsStart(keyPart) + signum + value;
  }
  
  private String getTagsString( Identifier[] tags ) {
      StringBuilder result = new StringBuilder();
      for( Identifier tag : tags ) {
          result.append(" ").append(tag.getId());
      }
      return result.toString();
  }

  private String createtNewStatsStart(String keyPart) {
    return " " + resources.getString("Equipment.Stats.Short." + keyPart) + ":";
  }

  @Override
  public String createString(IEquipmentItem item, IEquipmentStats equipment) {
    if (equipment instanceof IWeaponStats) {
      return createWeaponString(item, (IWeaponStats) equipment);
    }
    if (equipment instanceof IArmourStats) {
      return createArmourString((IArmourStats) equipment);
    }
    if (equipment instanceof ArtifactStats) {
      return createArtifactString((ArtifactStats) equipment);
    }
    if (equipment instanceof ITraitModifyingStats) {
      return createTraitModifyingString((ITraitModifyingStats) equipment);
    }
    throw new UnreachableCodeReachedException("All subclasses covered. Something appears to be wrong.");
  }

  private String createArtifactString(ArtifactStats stats) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(getStatsString(stats.getAttuneType().name(), stats.getAttuneCost(), false));
    stringBuilder.append("m");
    return stringBuilder.toString();
  }

  private String createTraitModifyingString(ITraitModifyingStats stats) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(stats.getName().getId());
    stringBuilder.append(":");
    if (stats.getDDVPoolMod() != 0)
      stringBuilder.append(getStatsString("DDV", stats.getDDVPoolMod(), true));
    if (stats.getPDVPoolMod() != 0)
      stringBuilder.append(getStatsString("PDV", stats.getPDVPoolMod(), true));
    if (stats.getMDDVPoolMod() != 0)
      stringBuilder.append(getStatsString("MDDV", stats.getMDDVPoolMod(), true));
    if (stats.getMPDVPoolMod() != 0)
      stringBuilder.append(getStatsString("MPDV", stats.getMPDVPoolMod(), true));
    if (stats.getMeleeSpeedMod() != 0)
      stringBuilder.append(getStatsString("MeleeSpeed", stats.getMeleeSpeedMod(), true));
    if (stats.getMeleeAccuracyMod() != 0)
      stringBuilder.append(getStatsString("MeleeAccuracy", stats.getMeleeAccuracyMod(), true));
    if (stats.getMeleeDamageMod() != 0)
      stringBuilder.append(getStatsString("MeleeDamage", stats.getMeleeDamageMod(), true));
    if (stats.getMeleeRateMod() != 0)
      stringBuilder.append(getStatsString("MeleeRate", stats.getMeleeRateMod(), true));
    if (stats.getRangedSpeedMod() != 0)
      stringBuilder.append(getStatsString("RangedSpeed", stats.getRangedSpeedMod(), true));
    if (stats.getRangedAccuracyMod() != 0)
      stringBuilder.append(getStatsString("RangedAccuracy", stats.getRangedAccuracyMod(), true));
    if (stats.getRangedDamageMod() != 0)
      stringBuilder.append(getStatsString("RangedDamage", stats.getRangedDamageMod(), true));
    if (stats.getRangedRateMod() != 0)
      stringBuilder.append(getStatsString("RangedRate", stats.getRangedRateMod(), true));
    if (stats.getJoinBattleMod() != 0)
      stringBuilder.append(getStatsString("JoinBattle", stats.getJoinBattleMod(), true));
    if (stats.getJoinDebateMod() != 0)
      stringBuilder.append(getStatsString("JoinDebate", stats.getJoinDebateMod(), true));
    if (stats.getJoinWarMod() != 0)
      stringBuilder.append(getStatsString("JoinWar", stats.getJoinWarMod(), true));
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
    stringBuilder.append(getStatsString("MobilityPenalty", armourStats.getMobilityPenalty(), false));
    stringBuilder.append(getStatsString("Fatigue", armourStats.getFatigue(), false));
    return stringBuilder.toString();
  }

  private CharSequence createArmourStat(Integer soak, String prefix) {
    return soak == null ? "-" : prefix + soak;
  }
}