package net.sf.anathema.character.generic.magic.charms;

import java.util.List;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ICharmTree {

  public ICharmGroup[] getAllCharmGroups();

  public ICharm[] getAllCharms();

  public List<ICharm> getAllCharmsForGroup(String id);

  public ICharm getCharmByID(String id);
}