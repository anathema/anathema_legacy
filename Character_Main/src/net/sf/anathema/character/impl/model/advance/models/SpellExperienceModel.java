package net.sf.anathema.character.impl.model.advance.models;

import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.impl.model.advance.IPointCostCalculator;
import net.sf.anathema.character.main.model.spells.SpellModel;
import net.sf.anathema.character.main.model.spells.SpellsModelFetcher;
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
    SpellModel spellModel = SpellsModelFetcher.fetch(character);
    for (ISpell spell : spellModel.getLearnedSpells(true)) {
      if (!spellModel.isLearnedOnCreation(spell)) {
        experienceCosts += calculator.getSpellCosts(character, spell, traitConfiguration);
      }
    }
    return experienceCosts;
  }
}