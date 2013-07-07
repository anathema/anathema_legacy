package net.sf.anathema.character.main.template.magic;

import net.sf.anathema.character.main.magic.persistence.ICharmCache;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.type.ICharacterType;

import static net.sf.anathema.character.main.magic.model.charms.MartialArtsUtilities.MARTIAL_ARTS;

public class CharmSet implements ICharmSet {

  private final ICharm[] charms;
  private final ICharm[] martialArtsCharms;

  public static ICharmSet createRegularCharmSet(ICharmCache charmProvider, ICharacterType characterType) {
    ICharm[] charms = charmProvider.getCharms(characterType);
    ICharm[] martialArtsCharms = charmProvider.getCharms(MARTIAL_ARTS);
    return new CharmSet(charms, martialArtsCharms);
  }

  private CharmSet(ICharm[] charms, ICharm[] martialArtsCharms) {
    this.charms = charms;
    this.martialArtsCharms = martialArtsCharms;
  }

  @Override
  public ICharm[] getCharms() {
    return charms;
  }

  @Override
  public ICharm[] getMartialArtsCharms() {
    return martialArtsCharms;
  }
}