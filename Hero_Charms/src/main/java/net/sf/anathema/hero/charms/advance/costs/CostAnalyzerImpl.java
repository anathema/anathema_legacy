package net.sf.anathema.hero.charms.advance.costs;

import net.sf.anathema.character.main.magic.model.Magic;
import net.sf.anathema.character.main.magic.charm.martial.MartialArtsLevel;
import net.sf.anathema.character.main.magic.charm.martial.MartialArtsUtilities;
import net.sf.anathema.hero.model.Hero;

public class CostAnalyzerImpl implements CostAnalyzer {

  private final Hero hero;

  public CostAnalyzerImpl(Hero hero) {
    this.hero = hero;
  }

  @Override
  public final boolean isMagicFavored(Magic magic) {
    return magic.isFavored(hero);
  }

  @Override
  public MartialArtsLevel getMartialArtsLevel(Magic magic) {
    return MartialArtsUtilities.getLevel(magic);
  }
}