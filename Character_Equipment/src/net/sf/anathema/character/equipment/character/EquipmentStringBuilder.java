package net.sf.anathema.character.equipment.character;

import net.disy.commons.core.exception.UnreachableCodeReachedException;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.generic.equipment.ArtifactAttuneType;
import net.sf.anathema.character.generic.equipment.IArtifactStats;
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

  private String createWeaponString(IEquipmentItem item, IWeaponStats weapon) {
    StringBuilder stringBuilder = new StringBuilder();
    String key = EquipmentObjectPresenter.EQUIPMENT_NAME_PREFIX + weapon.getName().getId();
    if (resources.supportsKey(key)) {
      stringBuilder.append(resources.getString(key));
    }
    else {
      stringBuilder.append(new WeaponStatsNameStringFactory(resources).create(item, weapon));
    }
    stringBuilder.append(":"); //$NON-NLS-1$
    stringBuilder.append(getStatsString("Speed", weapon.getSpeed(), false)); //$NON-NLS-1$
    stringBuilder.append(getStatsString("Accuracy", weapon.getAccuracy(), true)); //$NON-NLS-1$
    if (weapon.inflictsNoDamage()) {
      stringBuilder.append(" " + resources.getString("Equipment.Stats.Short.Damage") + ":-"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
    else {
      stringBuilder.append(getStatsString("Damage", weapon.getDamage(), weapon.getDamageTraitType() != null)); //$NON-NLS-1$
      stringBuilder.append(resources.getString("HealthType." + weapon.getDamageType().getId() + ".Short")); //$NON-NLS-1$ //$NON-NLS-2$
    }
    stringBuilder.append(getStatsString("Defence", weapon.getDefence(), true)); //$NON-NLS-1$
    stringBuilder.append(getStatsString("Range", weapon.getRange(), false)); //$NON-NLS-1$
    stringBuilder.append(getStatsString("Rate", weapon.getRate(), false)); //$NON-NLS-1$
    return stringBuilder.toString();
  }

  private String getStatsString(String keyPart, Integer value, boolean printSignum) {
    if (value == null) {
      return ""; //$NON-NLS-1$
    }
    String signum = printSignum && value >= 0 ? "+" : ""; //$NON-NLS-1$ //$NON-NLS-2$
    return createtNewStatsStart(keyPart) + signum + value;
  }

  private String createtNewStatsStart(String keyPart) {
    return " " + resources.getString("Equipment.Stats.Short." + keyPart) + ":"; //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
  }

  public String createString(IEquipmentItem item, IEquipmentStats equipment) {
	if (item != null && equipment != null)
		equipment.setUseAttunementModifiers(checkAttunement(item.getAttunementState()));
    if (equipment instanceof IWeaponStats) {
      return createWeaponString(item, (IWeaponStats) equipment);
    }
    if (equipment instanceof IArmourStats) {
      return createArmourString((IArmourStats) equipment);
    }
    if (equipment instanceof IShieldStats) {
      return createShieldString((IShieldStats) equipment);
    }
    if (equipment instanceof IArtifactStats)
      return createArtifactString((IArtifactStats) equipment);
    throw new UnreachableCodeReachedException("All subclasses covered. Something appears to be wrong."); //$NON-NLS-1$
  }
  
  private boolean checkAttunement(ArtifactAttuneType state)
  {
	  switch (state)
	  {
	  case Unattuned:
	  case PartiallyAttuned:
		  return false;
	  default:
	  case FullyAttuned:
	  case UnharmoniouslyAttuned:
	  case VitriolAttuned:
		  return true;
	  }
  }
  
  private String createArtifactString(IArtifactStats stats)
  {
	  StringBuilder stringBuilder = new StringBuilder();
	  stringBuilder.append(getStatsString(stats.getAttuneType().name(), stats.getAttuneCost(), false));
	  stringBuilder.append("m");
	  return stringBuilder.toString();
  }

  private String createShieldString(IShieldStats stats) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(stats.getName().getId());
    stringBuilder.append(":"); //$NON-NLS-1$
    stringBuilder.append(getStatsString("CloseDV", stats.getCloseCombatBonus(), true)); //$NON-NLS-1$
    stringBuilder.append(getStatsString("RangedDV", stats.getRangedCombatBonus(), true)); //$NON-NLS-1$
    stringBuilder.append(getStatsString("MobilityPenalty", stats.getMobilityPenalty(), false)); //$NON-NLS-1$
    stringBuilder.append(getStatsString("Fatigue", stats.getFatigue(), false)); //$NON-NLS-1$
    return stringBuilder.toString();
  }

  private String createArmourString(IArmourStats armourStats) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(armourStats.getName().getId());
    stringBuilder.append(":"); //$NON-NLS-1$
    stringBuilder.append(createtNewStatsStart("Soak")); //$NON-NLS-1$
    stringBuilder.append(createArmourStat(armourStats.getSoak(HealthType.Bashing), "+")); //$NON-NLS-1$
    stringBuilder.append("/"); //$NON-NLS-1$
    stringBuilder.append(createArmourStat(armourStats.getSoak(HealthType.Lethal), "+")); //$NON-NLS-1$
    stringBuilder.append("/"); //$NON-NLS-1$
    stringBuilder.append(createArmourStat(armourStats.getSoak(HealthType.Aggravated), "+")); //$NON-NLS-1$
    stringBuilder.append(createtNewStatsStart("Hardness")); //$NON-NLS-1$
    stringBuilder.append(createArmourStat(armourStats.getHardness(HealthType.Bashing), "")); //$NON-NLS-1$
    stringBuilder.append("/"); //$NON-NLS-1$
    stringBuilder.append(createArmourStat(armourStats.getHardness(HealthType.Lethal), "")); //$NON-NLS-1$
    stringBuilder.append(getStatsString("MobilityPenalty", armourStats.getMobilityPenalty(), false)); //$NON-NLS-1$
    stringBuilder.append(getStatsString("Fatigue", armourStats.getFatigue(), false)); //$NON-NLS-1$
    return stringBuilder.toString();
  }

  private CharSequence createArmourStat(Integer soak, String prefix) {
    return soak == null ? "-" : prefix + soak; //$NON-NLS-1$
  }
}