package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.character.main.magic.basic.Magic;
import net.sf.anathema.character.main.magic.charm.martial.MartialArtsLevel;

public interface CostAnalyzer {

  MartialArtsLevel getMartialArtsLevel(Magic magic);

  boolean isMagicFavored(Magic magic);

}