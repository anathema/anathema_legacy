package net.sf.anathema.hero.advance.experience.models;

import net.sf.anathema.hero.advance.experience.PointCostCalculator;
import net.sf.anathema.hero.advance.AbstractIntegerValueModel;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.model.SpellsModel;
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
    SpellsModel spellsModel = SpellsModelFetcher.fetch(hero);
    for (Spell spell : spellsModel.getLearnedSpells(true)) {
      if (!spellsModel.isLearnedOnCreation(spell)) {
        experienceCosts += calculator.getSpellCosts(hero, spell, traitConfiguration);
      }
    }
    return experienceCosts;
  }
}