package net.sf.anathema.character.equipment.impl.character.model.natural.powercombat;

import net.sf.anathema.character.equipment.impl.character.model.natural.AbstractNaturalWeaponStats;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.util.IIdentificate;
import net.sf.anathema.lib.util.Identificate;

public class Punch extends AbstractNaturalWeaponStats {

  public int getAccuracy() {
    return 1;
  }

  public int getDamage() {
    return 0;
  }

  public Integer getDefence() {
    return 2;
  }

  public Integer getRate() {
    return 5;
  }

  public int getSpeed() {
    return 0;
  }

  public IIdentificate[] getTags() {
    return new IIdentificate[0];
  }

  public ITraitType getTraitType() {
    return AbilityType.Brawl;
  }

  public ITraitType getDamageTraitType() {
    return AttributeType.Strength;
  }

  public boolean inflictsNoDamage() {
    return false;
  }

  public IIdentificate getName() {
    return new Identificate("Punch"); //$NON-NLS-1$
  }
}