package net.sf.anathema.cascades.presenter;

import net.sf.anathema.hero.charms.display.tree.CharmTreeMap;
import net.sf.anathema.hero.charms.model.GroupCharmTree;
import net.sf.anathema.hero.charms.model.options.CharmTree;
import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class CharmTreeIdentifierMap implements CharmTreeMap {

  Map<Identifier, CharmTree> charmTreesById = new HashMap<>();

  public void put(Identifier id, CharmTree tree) {
    charmTreesById.put(id, tree);
  }

  @Override
  public GroupCharmTree getCharmTree(Identifier type) {
    return charmTreesById.get(type);
  }
}