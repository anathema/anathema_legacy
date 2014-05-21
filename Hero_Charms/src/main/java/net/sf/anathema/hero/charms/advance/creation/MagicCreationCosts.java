package net.sf.anathema.hero.charms.advance.creation;

import net.sf.anathema.character.magic.basic.Magic;
import net.sf.anathema.hero.charms.advance.costs.CostAnalyzer;
import net.sf.anathema.hero.charms.advance.costs.MagicCosts;
import net.sf.anathema.hero.charms.advance.costs.MagicPointsStrategy;
import net.sf.anathema.hero.charms.template.advance.MagicPointsTemplate;
import net.sf.anathema.character.magic.charm.martial.MartialArtsLevel;

import java.util.HashMap;
import java.util.Map;

public class MagicCreationCosts implements MagicCosts {

  private Map<Boolean, MagicPointsStrategy> strategyByFavored = new HashMap<>();

  public MagicCreationCosts(MagicPointsTemplate template, MartialArtsLevel standardMartialArtsLevel) {
    strategyByFavored.put(true, new MagicPointsStrategy(template.favoredCreationPoints, standardMartialArtsLevel));
    strategyByFavored.put(false, new MagicPointsStrategy(template.generalCreationPoints, standardMartialArtsLevel));
  }

  @Override
  public int getMagicCosts(Magic magic, CostAnalyzer analyzer) {
    boolean favored = analyzer.isMagicFavored(magic);
    return strategyByFavored.get(favored).getMagicCosts(magic, analyzer);
  }
}
