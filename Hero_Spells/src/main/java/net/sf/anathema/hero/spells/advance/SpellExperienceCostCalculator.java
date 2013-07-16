package net.sf.anathema.hero.spells.advance;

import net.sf.anathema.character.main.magic.spells.Spell;
import net.sf.anathema.hero.charms.advance.costs.CostAnalyzerImpl;
import net.sf.anathema.hero.charms.advance.experience.MagicExperienceCosts;
import net.sf.anathema.hero.model.Hero;

public class SpellExperienceCostCalculator {

  private final MagicExperienceCosts costs;

  public SpellExperienceCostCalculator(MagicExperienceCosts costs) {
    this.costs = costs;
  }

  public int getSpellCosts(Hero hero, Spell spell) {
    return costs.getMagicCosts(spell, new CostAnalyzerImpl(hero));
  }
}