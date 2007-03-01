package net.sf.anathema.character.generic.magic.charms;

import java.util.List;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ICharmTree extends ICharmIdMap, ICharmLearnableArbitrator {

  public ICharmGroup[] getAllCharmGroups();

  public ICharm[] getAllCharms();

  public List<ICharm> getAllCharmsForGroup(String id);
}