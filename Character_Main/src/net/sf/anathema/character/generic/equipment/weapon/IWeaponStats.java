package net.sf.anathema.character.generic.equipment.weapon;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.lib.util.Identifier;

public interface IWeaponStats extends IEquipmentStats {

  int getAccuracy();

  int getDamage();

  HealthType getDamageType();

  int getSpeed();

  Identifier[] getTags();

  TraitType getTraitType();

  Integer getDefence();

  Integer getRange();

  Integer getRate();
  
  int getMinimumDamage();

  TraitType getDamageTraitType();

  boolean inflictsNoDamage();

  boolean isRangedCombat();
  
  int getMobilityPenalty();

  IEquipmentStats[] getViews();
}