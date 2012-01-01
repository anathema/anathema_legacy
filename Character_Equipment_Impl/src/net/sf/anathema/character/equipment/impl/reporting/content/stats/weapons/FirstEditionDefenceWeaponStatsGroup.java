package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionDefenceWeaponStatsGroup extends AbstractDefenceWeaponStatsGroup {

  public FirstEditionDefenceWeaponStatsGroup(IResources resources, IGenericCharacter character, IEquipmentModifiers equipment) {
    super(resources, character, equipment);
  }
  
  public FirstEditionDefenceWeaponStatsGroup(IResources resources,
		  IGenericCharacter character,
		  IGenericTraitCollection traitCollection,
		  IEquipmentModifiers equipment) {
	    super(resources, character, traitCollection, equipment);
	  }

  @Override
  protected int getDefenceValue(IWeaponStats weapon, IEquipmentModifiers equipment) {
    IGenericTraitCollection traitCollection = getTraitCollection();
    int dexterityValue = traitCollection.getTrait(AttributeType.Dexterity).getCurrentValue();
    ITraitType weaponTrait = weapon.getTraitType();
    int value = traitCollection.getTrait(weaponTrait).getCurrentValue() + dexterityValue + weapon.getDefence();
    value = Math.max(0, value - CharacterUtilties.getUntrainedActionModifier(getCharacter(), weaponTrait));
    return value;
  }
}
