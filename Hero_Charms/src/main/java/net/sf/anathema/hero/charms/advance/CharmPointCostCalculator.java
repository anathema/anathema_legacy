package net.sf.anathema.hero.charms.advance;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.advance.CostAnalyzerImpl;
import net.sf.anathema.hero.charms.advance.experience.MagicExperienceCosts;
import net.sf.anathema.hero.model.Hero;

public class CharmPointCostCalculator {

  private final MagicExperienceCosts costs;

  public CharmPointCostCalculator(MagicExperienceCosts costs) {
    this.costs = costs;
  }

  public int getCharmCosts(Hero hero, Charm charm) {
    return costs.getMagicCosts(charm, new CostAnalyzerImpl(hero));
  }
}