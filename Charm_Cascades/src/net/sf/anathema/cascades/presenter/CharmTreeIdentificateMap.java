package net.sf.anathema.cascades.presenter;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.magic.charms.ICharmTree;
import net.sf.anathema.lib.util.IIdentificate;

public class CharmTreeIdentificateMap {

  Map<IIdentificate, ICharmTree> charmTreesById = new HashMap<IIdentificate, ICharmTree>();

  public void put(IIdentificate id, ICharmTree tree) {
    charmTreesById.put(id, tree);
  }

  public ICharmTree get(IIdentificate id) {
    return charmTreesById.get(id);
  }
}