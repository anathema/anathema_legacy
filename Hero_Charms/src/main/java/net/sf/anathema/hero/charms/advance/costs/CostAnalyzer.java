package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;

public interface CostAnalyzer {

  MartialArtsLevel getMartialArtsLevel(Magic magic);

  boolean isMagicFavored(Magic magic);

}