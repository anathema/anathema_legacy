package net.sf.anathema.character.equipment.impl.character.model.natural.secondedition;

import net.sf.anathema.character.equipment.impl.character.model.natural.AbstractNaturalWeaponStats;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
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

  @Override
  public Integer getRate() {
    return 3;
  }

  public int getSpeed() {
    return 5;
  }

  public IIdentificate[] getTags() {
    return new IIdentificate[] { WeaponTag.Natural };
  }

  public ITraitType getTraitType() {
    return AbilityType.MartialArts;
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