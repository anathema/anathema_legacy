package net.sf.anathema.character.generic.equipment.weapon;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.Identifier;

public interface IWeaponStats extends IEquipmentStats {

  int getAccuracy();

  int getDamage();

  HealthType getDamageType();

  int getSpeed();

  Identifier[] getTags();

  ITraitType getTraitType();

  Integer getDefence();

  Integer getRange();

  Integer getRate();
  
  int getMinimumDamage();

  ITraitType getDamageTraitType();

  boolean inflictsNoDamage();

  boolean isRangedCombat();
  
  int getMobilityPenalty();

  IEquipmentStats[] getViews();
}