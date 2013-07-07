package net.sf.anathema.character.main.equipment.weapon;

import net.sf.anathema.character.main.health.HealthType;
import net.sf.anathema.character.main.traits.TraitType;
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