package net.sf.anathema.character.main.magic.model.charmtree;

import net.sf.anathema.character.main.magic.model.charm.CharmIdMap;
import net.sf.anathema.character.main.magic.model.charm.ICharm;

import java.util.List;

public interface ICharmTree extends CharmIdMap, ICharmLearnableArbitrator, GroupCharmTree {

  ICharm[] getAllCharms();

  List<ICharm> getAllCharmsForGroup(String id);
}