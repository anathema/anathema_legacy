package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.character.generic.magic.ICharm;

import java.util.List;

public interface ICharmTree extends CharmIdMap, ICharmLearnableArbitrator, GroupCharmTree {

  ICharm[] getAllCharms();

  List<ICharm> getAllCharmsForGroup(String id);
}