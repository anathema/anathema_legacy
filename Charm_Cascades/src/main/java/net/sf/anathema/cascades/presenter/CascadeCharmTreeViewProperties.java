package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.hero.charms.display.view.AbstractCharmTreeViewProperties;
import net.sf.anathema.hero.charms.model.CharmIdMap;

public class CascadeCharmTreeViewProperties extends AbstractCharmTreeViewProperties implements CharmIdMap {

  private final CharmCache cache;

  public CascadeCharmTreeViewProperties(CharmCache cache) {
    this.cache = cache;
  }

  @Override
  public Charm getCharmById(String id) {
    return cache.getCharm(id);
  }
}