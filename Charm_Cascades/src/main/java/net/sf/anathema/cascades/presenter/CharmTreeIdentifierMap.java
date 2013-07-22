package net.sf.anathema.cascades.presenter;

import net.sf.anathema.hero.charms.model.options.CharmTree;
import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CharmTreeIdentifierMap {

  Map<Identifier, CharmTree> charmTreesById = new HashMap<>();

  public void put(Identifier id, CharmTree tree) {
    charmTreesById.put(id, tree);
  }

  public CharmTree get(Identifier id) {
    return charmTreesById.get(id);
  }

  public Set<Identifier> keySet() {
    return charmTreesById.keySet();
  }
}