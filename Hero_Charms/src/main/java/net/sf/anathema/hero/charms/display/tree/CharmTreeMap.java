package net.sf.anathema.hero.charms.display.tree;

import net.sf.anathema.hero.charms.model.GroupCharmTree;
import net.sf.anathema.lib.util.Identifier;

public interface CharmTreeMap {
  GroupCharmTree getCharmTree(Identifier type);
}