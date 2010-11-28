package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.charmtree.presenter.view.ICharmGroupArbitrator;

public class FriendlyCharmGroupArbitrator implements ICharmGroupArbitrator {

  public ICharm[] getCharms(ICharmGroup charmGroup) {
    return charmGroup.getAllCharms();
  }
}