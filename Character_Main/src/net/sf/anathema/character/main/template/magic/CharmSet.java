package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.parser.charms.ICharmCache;
import net.sf.anathema.character.main.type.ICharacterType;

import static net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities.MARTIAL_ARTS;

public class CharmSet implements ICharmSet {

  private final Charm[] charms;
  private final Charm[] martialArtsCharms;

  public static ICharmSet createRegularCharmSet(ICharmCache charmProvider, ICharacterType characterType) {
    Charm[] charms = charmProvider.getCharms(characterType);
    Charm[] martialArtsCharms = charmProvider.getCharms(MARTIAL_ARTS);
    return new CharmSet(charms, martialArtsCharms);
  }

  private CharmSet(Charm[] charms, Charm[] martialArtsCharms) {
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