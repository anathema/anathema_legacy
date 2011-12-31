package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionAccuracyWeaponStatsGroup extends AccuracyWeaponStatsGroup {

  private final IGenericCharacter character;

  public FirstEditionAccuracyWeaponStatsGroup(IResources resources,
		  IGenericCharacter character,
		  IGenericTraitCollection traitCollection,
		  IEquipmentModifiers equipment)
  {
    super(resources, traitCollection, equipment);
    this.character = character;
  }

  @Override
  protected int getFinalValue(IWeaponStats weapon, int weaponValue, IEquipmentModifiers equipment) {
    int value = super.getFinalValue(weapon, weaponValue, equipment);
    value = Math.max(0, value - CharacterUtilties.getUntrainedActionModifier(character, weapon.getTraitType()));
    value += weapon.isRangedCombat() ? equipment.getRangedAccuracyMod() : equipment.getMeleeAccuracyMod(); 
    return value;
  }
}
