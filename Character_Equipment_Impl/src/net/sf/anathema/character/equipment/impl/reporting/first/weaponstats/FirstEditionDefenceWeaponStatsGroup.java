package net.sf.anathema.character.equipment.impl.reporting.first.weaponstats;

import net.sf.anathema.character.equipment.impl.reporting.AbstractDefenceWeaponStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.impl.CharacterUtilties;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.resources.IResources;

public class FirstEditionDefenceWeaponStatsGroup extends AbstractDefenceWeaponStatsGroup {

  public FirstEditionDefenceWeaponStatsGroup(IResources resources, IGenericCharacter character) {
    super(resources, character);
  }

  @Override
  protected int getDefenceValue(IWeaponStats weapon) {
    IGenericTraitCollection traitCollection = getCharacter().getTraitCollection();
    int dexterityValue = traitCollection.getTrait(AttributeType.Dexterity).getCurrentValue();
    ITraitType weaponTrait = weapon.getTraitType();
    int value = traitCollection.getTrait(weaponTrait).getCurrentValue() + dexterityValue + weapon.getDefence();
    value = Math.max(0, value - CharacterUtilties.getUntrainedActionModifier(getCharacter(), weaponTrait));
    return value;
  }
}
