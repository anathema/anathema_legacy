package net.sf.anathema.character.main.magic.model.charmtree;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;

import java.util.List;

public interface ICharmTree extends CharmIdMap, ICharmLearnableArbitrator, GroupCharmTree {

  Charm[] getAllCharms();

  List<Charm> getAllCharmsForGroup(String id);
}