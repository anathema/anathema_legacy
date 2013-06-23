package net.sf.anathema.character.equipment.impl.reporting.content.stats.weapons;

import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.character.model.IEquipmentStatsOption;
import net.sf.anathema.character.generic.equipment.weapon.IWeaponStats;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.main.model.traits.TraitModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

public class SecondEditionDefenceWeaponStatsGroup extends AbstractDefenceWeaponStatsGroup {

  private Hero hero;
  private final EquipmentHeroEvaluator provider;
  private EquipmentOptionsProvider optionProvider;

  public SecondEditionDefenceWeaponStatsGroup(Resources resources, Hero hero, EquipmentHeroEvaluator provider,
                                              EquipmentOptionsProvider optionProvider) {
    super(resources);
    this.hero = hero;
    this.provider = provider;
    this.optionProvider = optionProvider;
  }

  @Override
  protected int getDefenceValue(IWeaponStats weapon) {
    TraitMap traitCollection = TraitModelFetcher.fetch(hero);
    double finalValue = calculateFinalValue(weapon.getDefence() + getOptionModifiers(weapon), traitCollection.getTrait(AttributeType.Dexterity),
            traitCollection.getTrait(weapon.getTraitType()));
    boolean isMortal = !hero.getTemplate().getTemplateType().getCharacterType().isEssenceUser();
    if (isMortal) {
      finalValue = Math.floor(finalValue / 2);
    } else {
      finalValue = Math.ceil(finalValue / 2);
    }
    return (int) finalValue;
  }

  private int getOptionModifiers(IWeaponStats stats) {
    if (provider == null) {
      return 0;
    }
    int mod = 0;
    for (IEquipmentStatsOption option : optionProvider.getEnabledStatOptions(stats)) {
      mod += option.getDefenseModifier();
    }
    return mod;
  }
}
