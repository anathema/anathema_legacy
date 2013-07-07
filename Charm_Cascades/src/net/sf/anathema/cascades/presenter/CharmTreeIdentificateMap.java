package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.magic.model.charmtree.ICharmTree;
import net.sf.anathema.lib.util.Identifier;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CharmTreeIdentificateMap {

  Map<Identifier, ICharmTree> charmTreesById = new HashMap<>();

  public void put(Identifier id, ICharmTree tree) {
    charmTreesById.put(id, tree);
  }

  public ICharmTree get(Identifier id) {
    return charmTreesById.get(id);
  }

  public Set<Identifier> keySet() {
    return charmTreesById.keySet();
  }
}