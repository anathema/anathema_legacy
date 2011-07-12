package net.sf.anathema.character.equipment.impl.reporting.second.weaponstats;

import net.sf.anathema.character.equipment.impl.reporting.AbstractDefenceWeaponStatsGroup;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionDefenceWeaponStatsGroup extends AbstractDefenceWeaponStatsGroup {

  public SecondEditionDefenceWeaponStatsGroup(IResources resources,
		  IGenericCharacter character,
		  IGenericTraitCollection traitCollection,
		  IEquipmentModifiers equipment) {
    super(resources, character, traitCollection, equipment);
  }

  @Override
  protected int getDefenceValue(IWeaponStats weapon, IEquipmentModifiers equipment) {
    IGenericTraitCollection traitCollection = getTraitCollection();
    double finalValue = calculateFinalValue(
        weapon.getDefence(),
        traitCollection.getTrait(AttributeType.Dexterity),
        traitCollection.getTrait(weapon.getTraitType()));
    boolean isMortal = !getCharacter().getTemplate().getTemplateType().getCharacterType().isExaltType();
    if (isMortal) {
      finalValue = Math.floor(finalValue / 2);
    }
    else {
      finalValue = Math.ceil(finalValue / 2);
    }
    return (int) finalValue + equipment.getPDVMod();
  }
}