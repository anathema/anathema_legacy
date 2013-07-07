package net.sf.anathema.character.main.advance.models;

import net.sf.anathema.character.main.magic.ISpell;
import net.sf.anathema.hero.spells.SpellModel;
import net.sf.anathema.hero.spells.SpellsModelFetcher;
import net.sf.anathema.hero.traits.TraitMap;
import net.sf.anathema.character.main.advance.IPointCostCalculator;
import net.sf.anathema.hero.model.Hero;

public class SpellExperienceModel extends AbstractIntegerValueModel {
  private final Hero hero;
  private final IPointCostCalculator calculator;
  private final TraitMap traitConfiguration;

  public SpellExperienceModel(Hero hero, IPointCostCalculator calculator, TraitMap traitConfiguration) {
    super("Experience", "Spells");
    this.hero = hero;
    this.calculator = calculator;
    this.traitConfiguration = traitConfiguration;
  }

  @Override
  public Integer getValue() {
    return getSpellCosts();
  }

  private int getSpellCosts() {
    int experienceCosts = 0;
    SpellModel spellModel = SpellsModelFetcher.fetch(hero);
    for (ISpell spell : spellModel.getLearnedSpells(true)) {
      if (!spellModel.isLearnedOnCreation(spell)) {
        experienceCosts += calculator.getSpellCosts(hero, spell, traitConfiguration);
      }
    }
    return experienceCosts;
  }
}