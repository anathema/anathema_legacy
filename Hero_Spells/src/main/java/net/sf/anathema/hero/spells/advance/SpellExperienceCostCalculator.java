package net.sf.anathema.hero.spells.advance;

import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.hero.advance.CostAnalyzerImpl;
import net.sf.anathema.hero.model.Hero;

public class SpellExperienceCostCalculator {

  private final IExperiencePointCosts costs;

  public SpellExperienceCostCalculator(IExperiencePointCosts costs) {
    this.costs = costs;
  }

  public int getSpellCosts(Hero hero, Spell spell) {
    return costs.getSpellCosts(spell, new CostAnalyzerImpl(hero));
  }
}