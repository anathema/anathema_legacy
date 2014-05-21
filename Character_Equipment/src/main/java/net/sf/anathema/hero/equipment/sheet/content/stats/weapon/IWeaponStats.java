package net.sf.anathema.hero.equipment.sheet.content.stats.weapon;

import net.sf.anathema.hero.traits.model.TraitType;
import net.sf.anathema.hero.health.HealthType;
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