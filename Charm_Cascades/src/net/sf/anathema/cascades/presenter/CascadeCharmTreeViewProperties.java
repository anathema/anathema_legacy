package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.magic.parser.ICharmCache;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.display.view.charmtree.AbstractCharmTreeViewProperties;
import net.sf.anathema.character.main.magic.display.view.charmtree.NullSpecialCharm;
import net.sf.anathema.lib.resources.Resources;
import net.sf.anathema.lib.util.Identifier;

public class CascadeCharmTreeViewProperties extends AbstractCharmTreeViewProperties implements CharmIdMap {

  private Identifier type;
  private CharmTreeIdentificateMap treeIdentificateMap;
  private final ICharmCache cache;
  private final HeroEnvironment generics;

  public CascadeCharmTreeViewProperties(Resources resources, MagicDescriptionProvider magicDescriptionProvider, HeroEnvironment generics,
                                        ICharmCache cache, CharmTreeIdentificateMap treeIdentificateMap) {
    super(resources, magicDescriptionProvider);
    this.generics = generics;
    this.cache = cache;
    this.treeIdentificateMap = treeIdentificateMap;
  }

  @Override
  public ICharm getCharmById(String id) {
    ICharm charm = treeIdentificateMap.get(type).getCharmById(id);
    if (charm == null) {
      CharmFinder charmFinder = new CharmFinder(generics.getCharacterTypes(), cache, id);
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

  public void setCharmType(Identifier type) {
    this.type = type;
  }

  private ISpecialCharm[] getSpecialCharmSet() {
    return generics.getCharmProvider().getSpecialCharms(type);
  }
}