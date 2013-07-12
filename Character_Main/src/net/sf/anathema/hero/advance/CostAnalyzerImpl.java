package net.sf.anathema.hero.advance;

import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.hero.advance.CostAnalyzer;
import net.sf.anathema.hero.magic.model.martial.MartialArtsLevel;
import net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities;
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