package net.sf.anathema.hero.charms.model.options;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.CharmIdMap;
import net.sf.anathema.hero.charms.model.GroupCharmTree;
import net.sf.anathema.hero.charms.model.learn.ICharmLearnableArbitrator;

import java.util.List;

public interface CharmTree extends CharmIdMap, ICharmLearnableArbitrator, GroupCharmTree {

  Charm[] getAllCharms();

  List<Charm> getAllCharmsForGroup(String id);
}