package net.sf.anathema.hero.magic.advance.creation;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.template.experience.CostAnalyzer;
import net.sf.anathema.character.main.xml.creation.template.MagicCreationCostsTto;

import java.util.HashMap;
import java.util.Map;

public class MagicCostsImpl implements MagicCosts {

  private Map<Boolean, MagicCostStrategy> strategyByFavored = new HashMap<>();

  public MagicCostsImpl(MagicCreationCostsTto tto) {
    strategyByFavored.put(true, new MagicCostStrategy(tto.favored, tto.standardMartialArtsLevel));
    strategyByFavored.put(false, new MagicCostStrategy(tto.general, tto.standardMartialArtsLevel));
  }

  private int getCharmCosts(Charm charm, CostAnalyzer analyzer) {
    boolean favored = analyzer.isMagicFavored(charm);
    return strategyByFavored.get(favored).getCharmCosts(charm);
  }

  private int getSpellCosts(Magic spell, CostAnalyzer analyzer) {
    boolean favored = analyzer.isMagicFavored(spell);
    return strategyByFavored.get(favored).getSpellCosts();
  }

  @Override
  public int getMagicCosts(Magic magic, final CostAnalyzer analyzer) {
    if (magic instanceof Charm) {
      return getCharmCosts((Charm) magic, analyzer);
    }
    return getSpellCosts(magic, analyzer);
  }
}
