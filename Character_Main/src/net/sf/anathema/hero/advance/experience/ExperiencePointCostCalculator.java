package net.sf.anathema.hero.advance.experience;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.spells.Spell;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.hero.advance.CostAnalyzerImpl;
import net.sf.anathema.hero.model.Hero;

public class ExperiencePointCostCalculator implements PointCostCalculator {

  private final IExperiencePointCosts costs;

  public ExperiencePointCostCalculator(IExperiencePointCosts costs) {
    this.costs = costs;
  }

  @Override
  public int getSpellCosts(Hero hero, Spell spell) {
    return costs.getSpellCosts(spell, new CostAnalyzerImpl(hero));
  }

  @Override
  public int getCharmCosts(Hero hero, Charm charm) {
    return costs.getCharmCosts(charm, new CostAnalyzerImpl(hero));
  }
}