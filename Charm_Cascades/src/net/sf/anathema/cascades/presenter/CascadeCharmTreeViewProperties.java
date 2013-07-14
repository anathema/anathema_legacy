package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.magic.cache.CharmCache;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.display.view.charmtree.AbstractCharmTreeViewProperties;
import net.sf.anathema.character.main.magic.display.view.charmtree.NullSpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.hero.framework.HeroEnvironment;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class CascadeCharmTreeViewProperties extends AbstractCharmTreeViewProperties implements CharmIdMap {

  private Identifier type;
  private final CharmCache cache;
  private final HeroEnvironment generics;

  public CascadeCharmTreeViewProperties(Resources resources, MagicDescriptionProvider magicDescriptionProvider, HeroEnvironment generics, CharmCache cache) {
    super(resources, magicDescriptionProvider);
    this.generics = generics;
    this.cache = cache;
  }

  @Override
  public Charm getCharmById(String id) {
    return cache.getCharm(id);
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

  public void setCharmType(Identifier type) {
    this.type = type;
  }

  private ISpecialCharm[] getSpecialCharmSet() {
    return generics.getCharmCache().getCharmProvider().getSpecialCharms(type);
  }
}