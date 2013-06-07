package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.magic.ICharmSet;
import net.sf.anathema.character.generic.type.ICharacterType;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;

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