package net.sf.anathema.hero.charms.advance;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.template.experience.IExperiencePointCosts;
import net.sf.anathema.hero.advance.CostAnalyzerImpl;
import net.sf.anathema.hero.model.Hero;

public class CharmPointCostCalculator {

  private final IExperiencePointCosts costs;

  public CharmPointCostCalculator(IExperiencePointCosts costs) {
    this.costs = costs;
  }

  public int getCharmCosts(Hero hero, Charm charm) {
    return costs.getCharmCosts(charm, new CostAnalyzerImpl(hero));
  }
}