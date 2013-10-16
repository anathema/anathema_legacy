package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmLearnableArbitrator;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.GroupCharmTree;

import java.util.List;

public interface CharmTree extends CharmIdMap, ICharmLearnableArbitrator, GroupCharmTree {

  Charm[] getAllCharms();

  List<Charm> getAllCharmsForGroup(String id);
}