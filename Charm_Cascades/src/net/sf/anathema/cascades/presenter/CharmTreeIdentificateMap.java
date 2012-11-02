package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.lib.util.Identified;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CharmTreeIdentificateMap {

  Map<Identified, ICharmTree> charmTreesById = new HashMap<>();

  public void put(Identified id, ICharmTree tree) {
    charmTreesById.put(id, tree);
  }

  public ICharmTree get(Identified id) {
    return charmTreesById.get(id);
  }

  public Set<Identified> keySet() {
    return charmTreesById.keySet();
  }
}