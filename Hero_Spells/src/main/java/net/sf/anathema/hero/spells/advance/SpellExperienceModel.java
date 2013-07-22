package net.sf.anathema.hero.spells.advance;

import net.sf.anathema.character.main.magic.spells.Spell;
import net.sf.anathema.hero.advance.overview.model.AbstractIntegerValueModel;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.spells.model.SpellsModel;
import net.sf.anathema.hero.spells.model.SpellsModelFetcher;

public class SpellExperienceModel extends AbstractIntegerValueModel {
  private final Hero hero;
  private final SpellExperienceCostCalculator calculator;

  public SpellExperienceModel(Hero hero, SpellExperienceCostCalculator calculator) {
    super("Experience", "Spells");
    this.hero = hero;
    this.calculator = calculator;
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
        experienceCosts += calculator.getSpellCosts(hero, spell);
      }
    }
    return experienceCosts;
  }
}