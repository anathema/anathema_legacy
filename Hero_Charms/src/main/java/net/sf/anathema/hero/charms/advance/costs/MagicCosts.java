package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.hero.advance.CostAnalyzer;

public interface MagicCosts {

  int getMagicCosts(Magic magic, CostAnalyzer analyzer);
}
