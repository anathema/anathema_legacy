package net.sf.anathema.character.equipment.impl.reporting.first.weaponstats;

import net.sf.anathema.character.equipment.impl.reporting.AccuracyWeaponStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionAccuracyWeaponStatsGroup extends AccuracyWeaponStatsGroup {

  private final IGenericCharacter character;

  public FirstEditionAccuracyWeaponStatsGroup(IResources resources,
		  IGenericCharacter character,
		  IGenericTraitCollection traitCollection)
  {
    super(resources, traitCollection);
    this.character = character;
  }

  @Override
  protected int getFinalValue(IWeaponStats weapon, int weaponValue) {
    int value = super.getFinalValue(weapon, weaponValue);
    value = Math.max(0, value - CharacterUtilties.getUntrainedActionModifier(character, weapon.getTraitType()));
    return value;
  }
}