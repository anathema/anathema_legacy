package net.sf.anathema.character.generic.impl.template.magic;

import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.magic.ICharmSet;
import net.sf.anathema.character.generic.template.magic.IUniqueCharmType;
import net.sf.anathema.character.generic.type.ICharacterType;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;

public class CharmSet implements ICharmSet {

  private final ICharm[] charms;
  private final ICharm[] uniqueCharms;
  private final ICharm[] martialArtsCharms;

  public static ICharmSet createRegularCharmSet(ICharmCache charmProvider, ICharacterType characterType,
                                                IUniqueCharmType uniqueType) {
    ICharm[] charms = charmProvider.getCharms(characterType);
    ICharm[] uniqueCharms = new ICharm[0];
    ICharm[] martialArtsCharms = charmProvider.getCharms(MARTIAL_ARTS);
    if (uniqueType != null) {
      uniqueCharms = charmProvider.getCharms(uniqueType.getId());
    }
    return new CharmSet(charms, uniqueCharms, martialArtsCharms);
  }

  private CharmSet(ICharm[] charms, ICharm[] uniqueCharms, ICharm[] martialArtsCharms) {
    this.charms = charms;
    this.uniqueCharms = uniqueCharms;
    this.martialArtsCharms = martialArtsCharms;
  }

  @Override
  public ICharm[] getCharms() {
    return charms;
  }

  @Override
  public ICharm[] getUniqueCharms() {
    return uniqueCharms;
  }

  @Override
  public ICharm[] getMartialArtsCharms() {
    return martialArtsCharms;
  }
}