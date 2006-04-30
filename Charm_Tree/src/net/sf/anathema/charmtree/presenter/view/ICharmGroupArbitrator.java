package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;

public interface ICharmGroupArbitrator {

  public ICharm[] getCharms(ICharmGroup charmGroup);
}