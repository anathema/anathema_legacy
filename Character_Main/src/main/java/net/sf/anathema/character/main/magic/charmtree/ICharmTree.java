package net.sf.anathema.character.main.magic.charmtree;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.CharmIdMap;

import java.util.List;

public interface ICharmTree extends CharmIdMap, ICharmLearnableArbitrator, GroupCharmTree {

  Charm[] getAllCharms();

  List<Charm> getAllCharmsForGroup(String id);
}