package net.sf.anathema.development.character;

import net.sf.anathema.character.generic.equipment.weapon.IWeapon;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class DemoRangeWeapon implements IWeapon {

  public int getAccuracy() {
    return 12;
  }

  public int getDamage() {
    return 15;
  }

  public HealthType getDamageType() {
    return HealthType.Bashing;
  }

  public Integer getDefence() {
    return null;
  }

  public Integer getRange() {
    return new Integer(200);
  }

  public Integer getRate() {
    return 3;
  }

  public int getSpeed() {
    return 0;
  }

  public IIdentificate[] getTags() {
    return new IIdentificate[0];
  }

  public ITraitType getTraitType() {
    return AbilityType.MartialArts;
  }

  public ITraitType getDamageTraitType() {
    return null;
  }

  public boolean inflictsNoDamage() {
    return true;
  }

  public IIdentificate getName() {
    return new Identificate("Range");
  }
}