package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.character.main.magic.model.Magic;
import net.sf.anathema.hero.charmtree.martial.MartialArtsLevel;

public interface CostAnalyzer {

  MartialArtsLevel getMartialArtsLevel(Magic magic);

  boolean isMagicFavored(Magic magic);

}