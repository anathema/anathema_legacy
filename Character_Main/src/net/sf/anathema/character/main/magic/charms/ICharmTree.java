package net.sf.anathema.character.main.magic.charms;

import net.sf.anathema.character.main.magic.ICharm;

import java.util.List;

public interface ICharmTree extends CharmIdMap, ICharmLearnableArbitrator, GroupCharmTree {

  ICharm[] getAllCharms();

  List<ICharm> getAllCharmsForGroup(String id);
}