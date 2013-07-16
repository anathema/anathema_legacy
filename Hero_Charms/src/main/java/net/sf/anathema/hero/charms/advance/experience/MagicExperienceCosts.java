package net.sf.anathema.hero.charms.advance.experience;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.hero.charms.advance.costs.CostAnalyzer;
import net.sf.anathema.hero.charms.advance.costs.MagicCosts;
import net.sf.anathema.hero.charms.advance.costs.MagicPointsStrategy;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;
import net.sf.anathema.hero.charmtree.martial.MartialArtsLevel;

import java.util.HashMap;
import java.util.Map;

public class MagicExperienceCosts implements MagicCosts {

  private Map<Boolean, MagicPointsStrategy> strategyByFavored = new HashMap<>();

  public MagicExperienceCosts(MagicPointsTemplate template, MartialArtsLevel standardMartialArtsLevel) {
    strategyByFavored.put(true, new MagicPointsStrategy(template.favoredExperiencePoints, standardMartialArtsLevel));
    strategyByFavored.put(false, new MagicPointsStrategy(template.generalExperiencePoints, standardMartialArtsLevel));
  }

  @Override
  public int getMagicCosts(Magic magic, CostAnalyzer analyzer) {
    boolean favored = analyzer.isMagicFavored(magic);
    return strategyByFavored.get(favored).getMagicCosts(magic, analyzer);
  }
}
