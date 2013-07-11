package net.sf.anathema.character.main.advance;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.MartialArtsLevel;
import net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities;
import net.sf.anathema.character.main.magic.model.magic.Magic;
import net.sf.anathema.character.main.template.experience.ICostAnalyzer;
import net.sf.anathema.hero.model.Hero;

public class CostAnalyzer implements ICostAnalyzer {

  private final Hero hero;

  public CostAnalyzer(Hero hero) {
    this.hero = hero;
  }

  @Override
  public final boolean isMagicFavored(Magic magic) {
    return magic.isFavored(hero);
  }

  @Override
  public MartialArtsLevel getMartialArtsLevel(Charm charm) {
    return MartialArtsUtilities.getLevel(charm);
  }
}