package net.sf.anathema.character.equipment.impl.character.model.natural.secondedition;

import net.sf.anathema.character.equipment.impl.character.model.natural.AbstractNaturalWeaponStats;
import net.sf.anathema.character.equipment.impl.creation.model.WeaponTag;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.util.Identified;
import net.sf.anathema.lib.util.Identifier;

public class Kick extends AbstractNaturalWeaponStats {

  @Override
  public int getAccuracy() {
    return 0;
  }

  @Override
  public int getDamage() {
    return 3;
  }

  @Override
  public int getMinimumDamage() {
    return 1;
  }

  @Override
  public Integer getDefence() {
    return -2;
  }

  @Override
  public Integer getRate() {
    return 2;
  }

  @Override
  public int getSpeed() {
    return 5;
  }

  @Override
  public Identified[] getTags() {
    return new Identified[]{WeaponTag.Natural};
  }

  @Override
  public ITraitType getTraitType() {
    return AbilityType.MartialArts;
  }

  @Override
  public ITraitType getDamageTraitType() {
    return AttributeType.Strength;
  }

  @Override
  public boolean inflictsNoDamage() {
    return false;
  }

  @Override
  public Identified getName() {
    return new Identifier("Kick");
  }
}