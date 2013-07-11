package net.sf.anathema.character.main.advance.models;

import net.sf.anathema.character.main.advance.PointCostCalculator;
import net.sf.anathema.character.main.magic.model.spells.ISpell;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.model.SpellModel;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;
import net.sf.anathema.hero.traits.TraitMap;

public class SpellExperienceModel extends AbstractIntegerValueModel {
  private final Hero hero;
  private final PointCostCalculator calculator;
  private final TraitMap traitConfiguration;

  public SpellExperienceModel(Hero hero, PointCostCalculator calculator, TraitMap traitConfiguration) {
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