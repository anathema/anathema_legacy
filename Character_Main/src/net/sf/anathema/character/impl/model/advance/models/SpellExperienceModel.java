package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.main.model.traits.TraitMap;
import net.sf.anathema.character.model.ICharacter;

public class SpellExperienceModel extends AbstractIntegerValueModel {
  private final ICharacter character;
  private final IPointCostCalculator calculator;
  private final TraitMap traitConfiguration;

  public SpellExperienceModel(ICharacter character, IPointCostCalculator calculator, TraitMap traitConfiguration) {
    super("Experience", "Spells");
    this.character = character;
    this.calculator = calculator;
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
        experienceCosts += calculator.getSpellCosts(character, spell, traitConfiguration);
      }
    }
    return experienceCosts;
  }
}