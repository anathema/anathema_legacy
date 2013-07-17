package net.sf.anathema.cascades.presenter;

import net.sf.anathema.hero.charms.compiler.CharmCache;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.hero.charms.display.view.AbstractCharmTreeViewProperties;
import net.sf.anathema.hero.charms.model.special.NullSpecialCharm;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmIdMap;
import net.sf.anathema.hero.charms.model.special.charms.ISpecialCharm;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class CascadeCharmTreeViewProperties extends AbstractCharmTreeViewProperties implements CharmIdMap {

  private Identifier type;
  private final CharmCache cache;

  public CascadeCharmTreeViewProperties(Resources resources, MagicDescriptionProvider magicDescriptionProvider, CharmCache cache) {
    super(resources, magicDescriptionProvider);
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
    return cache.getCharmProvider().getSpecialCharms(type);
  }
}