package net.sf.anathema.hero.charms.advance.creation;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.xml.creation.template.MagicCreationCostsTto;
import net.sf.anathema.hero.advance.CostAnalyzer;
import net.sf.anathema.hero.charms.advance.costs.MagicCosts;

import java.util.HashMap;
import java.util.Map;

public class MagicCreationCosts implements MagicCosts {

  private Map<Boolean, MagicBonusPointsStrategy> strategyByFavored = new HashMap<>();

  public MagicCreationCosts(MagicCreationCostsTto tto) {
    strategyByFavored.put(true, new MagicBonusPointsStrategy(tto.favored, tto.standardMartialArtsLevel));
    strategyByFavored.put(false, new MagicBonusPointsStrategy(tto.general, tto.standardMartialArtsLevel));
  }

  @Override
  public int getMagicCosts(Magic magic, CostAnalyzer analyzer) {
    boolean favored = analyzer.isMagicFavored(magic);
    return strategyByFavored.get(favored).getMagicCosts(magic, analyzer);
  }
}
