package net.sf.anathema.cascades.presenter;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.util.IIdentificate;

import static net.sf.anathema.character.generic.impl.magic.MartialArtsUtilities.MARTIAL_ARTS;

public class CharmFinder {

  private final ICharmCache cache;
  private final String id;

  public CharmFinder(ICharmCache cache, String id) {
    this.cache = cache;
    this.id = id;
  }

  public ICharm find() {
    ICharm charm = searchCharmByCharacterType();
    if (charm == null) {
      charm = searchCharmFromMartialArts();
    }
    return charm;
  }

  private ICharm searchCharmByCharacterType() {
    String[] idParts = id.split("\\."); //$NON-NLS-1$
    ICharacterType characterTypeId = CharacterType.getById(idParts[0]);
    return findCharm(characterTypeId);
  }

  private ICharm searchCharmFromMartialArts() {
    return findCharm(MARTIAL_ARTS);
  }

  private ICharm findCharm(IIdentificate treeType) {
    ICharm[] charms = cache.getCharms(treeType);
    return ArrayUtilities.find(new Predicate<ICharm>() {
      @Override
      public boolean apply(ICharm candidate) {
        return candidate.getId().equals(id);
      }
    }, charms);
  }
}