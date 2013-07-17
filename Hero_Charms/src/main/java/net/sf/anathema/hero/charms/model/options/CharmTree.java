package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.charmtree.GroupCharmTree;
import net.sf.anathema.character.main.magic.charmtree.ICharmLearnableArbitrator;

import java.util.List;

public interface CharmTree extends CharmIdMap, ICharmLearnableArbitrator, GroupCharmTree {

  Charm[] getAllCharms();

  List<Charm> getAllCharmsForGroup(String id);
}