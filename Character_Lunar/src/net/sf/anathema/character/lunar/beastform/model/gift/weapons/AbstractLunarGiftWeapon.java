package net.sf.anathema.character.lunar.beastform.model.gift.weapons;

import net.sf.anathema.character.generic.equipment.weapon.IEquipmentStats;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public abstract class AbstractLunarGiftWeapon implements IWeaponStats {
  public ITraitType getDamageTraitType() {
    return AttributeType.Strength;
  }

  public HealthType getDamageType() {
    return HealthType.Lethal;
  }

  public Integer getRange() {
    return null;
  }

  public Integer getRate() {
    return null;
  }

  public boolean inflictsNoDamage() {
    return false;
  }

  public boolean isRangedCombat() {
    return false;
  }

  public IIdentificate[] getTags() {
    return new Identificate[0];
  }

  public ITraitType getTraitType() {
    return AbilityType.Brawl;
  }

  @Override
  public IEquipmentStats[] getViews() {
    return new IEquipmentStats[] { this };
  }
}