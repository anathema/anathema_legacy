package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.main.traits.model.TraitMap;
import net.sf.anathema.character.model.ICharacter;

public class SpellExperienceModel extends AbstractIntegerValueModel {
  private final ICharacter character;
  private final IPointCostCalculator calculator;
  private final IBasicCharacterData basicCharacter;
  private final TraitMap traitConfiguration;

  public SpellExperienceModel(ICharacter character, IPointCostCalculator calculator, IBasicCharacterData basicCharacter,
                              TraitMap traitConfiguration) {
    super("Experience", "Spells");
    this.character = character;
    this.calculator = calculator;
    this.basicCharacter = basicCharacter;
    this.traitConfiguration = traitConfiguration;
  }

  @Override
  public Integer getValue() {
    return getSpellCosts();
  }

  private int getSpellCosts() {
    int experienceCosts = 0;
    for (ISpell spell : character.getSpells().getLearnedSpells(true)) {
      if (!character.getSpells().isLearnedOnCreation(spell)) {
        experienceCosts += calculator.getSpellCosts(spell, basicCharacter, traitConfiguration);
      }
    }
    return experienceCosts;
  }
}