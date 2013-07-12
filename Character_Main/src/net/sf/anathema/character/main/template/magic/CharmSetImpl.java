package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.parser.charms.CharmCache;
import net.sf.anathema.character.main.type.CharacterType;

import static net.sf.anathema.hero.magic.model.martial.MartialArtsUtilities.MARTIAL_ARTS;

public class CharmSetImpl implements CharmSet {

  private final Charm[] charms;
  private final Charm[] martialArtsCharms;

  public static CharmSet createRegularCharmSet(CharmCache charmCache, CharacterType characterType) {
    Charm[] charms = charmCache.getCharms(characterType);
    Charm[] martialArtsCharms = charmCache.getCharms(MARTIAL_ARTS);
    return new CharmSetImpl(charms, martialArtsCharms);
  }

  private CharmSetImpl(Charm[] charms, Charm[] martialArtsCharms) {
    this.charms = charms;
    this.martialArtsCharms = martialArtsCharms;
  }

  @Override
  public Charm[] getCharms() {
    return charms;
  }

  @Override
  public Charm[] getMartialArtsCharms() {
    return martialArtsCharms;
  }
}