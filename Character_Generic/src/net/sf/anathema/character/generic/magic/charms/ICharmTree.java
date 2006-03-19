package net.sf.anathema.character.generic.magic.charms;

import java.util.List;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ICharmTree<C extends ICharm> {

  public ICharmGroup[] getAllCharmGroups();

  public C[] getAllCharms();

  public List<C> getAllCharmsForGroup(String id);

  public C getCharmByID(String id);
}