package net.sf.anathema.development.character;

import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class DemoMeleeWeapon implements IWeapon {

  public int getAccuracy() {
    return -5;
  }

  public int getDamage() {
    return 3;
  }

  public HealthType getDamageType() {
    return HealthType.Aggravated;
  }

  public Integer getDefence() {
    return -1;
  }

  public Integer getRange() {
    return null;
  }

  public Integer getRate() {
    return 6;
  }

  public int getSpeed() {
    return 2;
  }

  public IIdentificate[] getTags() {
    return new IIdentificate[] {WeaponTag.ClinchEnhancer, WeaponTag.Piercing};
  }

  public ITraitType getTraitType() {
    return AbilityType.Melee;
  }

  public IIdentificate getName() {
    return new Identificate("Melee");
  }
}