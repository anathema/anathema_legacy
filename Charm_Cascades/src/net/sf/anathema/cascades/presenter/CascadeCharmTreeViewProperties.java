package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.impl.magic.persistence.ICharmCache;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmIdMap;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.charmtree.presenter.view.AbstractCharmTreeViewProperties;
import net.sf.anathema.charmtree.presenter.view.NullSpecialCharm;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identified;

public class CascadeCharmTreeViewProperties extends AbstractCharmTreeViewProperties implements ICharmIdMap {

  private Identified type;
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
  public ICharm getCharmById(String id) {
    ICharm charm = treeIdentificateMap.get(type).getCharmById(id);
    if (charm == null) {
      CharmFinder charmFinder = new CharmFinder(cache, id);
      charm = charmFinder.find();
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

  public void setCharmType(Identified type) {
    this.type = type;
  }

  private ISpecialCharm[] getSpecialCharmSet() {
    return generics.getCharmProvider().getSpecialCharms(type);
  }
}