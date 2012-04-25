package net.sf.anathema.cascades.presenter;

import com.google.common.base.Predicate;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmTreeViewProperties;
import net.sf.anathema.charmtree.presenter.view.NullSpecialCharm;
import net.sf.anathema.lib.lang.ArrayUtilities;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.IIdentificate;

import java.awt.Cursor;

public class CascadeCharmTreeViewProperties extends AbstractCharmTreeViewProperties {

  private IIdentificate type;
  private CharmTreeIdentificateMap treeIdentificateMap;
  private final ICharmCache cache;
  private final ICharacterGenerics generics;

  public CascadeCharmTreeViewProperties(IResources resources, MagicDescriptionProvider magicDescriptionProvider,
                                        ICharacterGenerics generics, ICharmCache cache,
                                        CharmTreeIdentificateMap treeIdentificateMap) {
    super(resources, magicDescriptionProvider);
    this.generics = generics;
    this.cache = cache;
    this.treeIdentificateMap = treeIdentificateMap;
  }

  @Override
  protected ICharm getCharmById(String id) {
    ICharm charm = treeIdentificateMap.get(type).getCharmById(id);
    if (charm == null) {
      charm = searchCharm(id);
    }
    return charm;
  }

  @Override
  protected ISpecialCharm getSpecialCharm(String id) {
    for (ISpecialCharm special : getSpecialCharmSet()) {
      if (special.getCharmId().equals(id)) {
        return special;
      }
    }
    return new NullSpecialCharm();
  }

  private ICharm searchCharm(final String charmId) {
    String[] idParts = charmId.split("\\."); //$NON-NLS-1$
    ICharacterType characterTypeId = CharacterType.getById(idParts[0]);
    ICharm[] charms = cache.getCharms(characterTypeId);
    return ArrayUtilities.find(new Predicate<ICharm>() {
      @Override
      public boolean apply(ICharm candidate) {
        return candidate.getId().equals(charmId);
      }
    }, charms);
  }

  public void setCharmType(IIdentificate type) {
    this.type = type;
  }

  @Override
  public Cursor getCursor(String nodeId) {
    if (nodeId == null) {
      return getDefaultCursor();
    }
    return getControlCursor();
  }

  private ISpecialCharm[] getSpecialCharmSet() {
     return generics.getCharmProvider().getSpecialCharms(type);
  }
}
