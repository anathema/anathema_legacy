package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import net.sf.anathema.character.equipment.character.IEquipmentCharacterDataProvider;
import net.sf.anathema.character.equipment.character.IEquipmentCharacterOptionProvider;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.equipment.IEquipmentModifiers;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionDefenceWeaponStatsGroup extends AbstractDefenceWeaponStatsGroup {
	
  private final IEquipmentCharacterDataProvider provider;
  private IEquipmentCharacterOptionProvider optionProvider;

  public SecondEditionDefenceWeaponStatsGroup(IResources resources, IGenericCharacter character, IGenericTraitCollection traitCollection,
                                              IEquipmentCharacterDataProvider provider,
                                              IEquipmentCharacterOptionProvider optionProvider, IEquipmentModifiers equipment) {
    super(resources, character, traitCollection, equipment);
    this.provider = provider;
    this.optionProvider = optionProvider;
  }

  @Override
  protected int getDefenceValue(IWeaponStats weapon, IEquipmentModifiers equipment) {
    IGenericTraitCollection traitCollection = getTraitCollection();
    double finalValue = calculateFinalValue(
        weapon.getDefence() + getOptionModifiers(weapon),
        traitCollection.getTrait(AttributeType.Dexterity),
        traitCollection.getTrait(weapon.getTraitType()));
    boolean isMortal = !getCharacter().getTemplate().getTemplateType().getCharacterType().isEssenceUser();
    if (isMortal) {
      finalValue = Math.floor(finalValue / 2);
    }
    else {
      finalValue = Math.ceil(finalValue / 2);
    }
    return (int) finalValue + equipment.getPDVMod();
  }
  
  private int getOptionModifiers(IWeaponStats stats) {
	  if (provider == null) return 0;
	  int mod = 0;
	  for (IEquipmentStatsOption option : optionProvider.getEnabledStatOptions(stats))
		  mod += option.getDefenseModifier();
	  return mod;
  }
}
