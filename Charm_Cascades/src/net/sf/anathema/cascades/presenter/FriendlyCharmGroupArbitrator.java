package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;
import net.sf.anathema.charmtree.view.CharmGroupArbitrator;

public class FriendlyCharmGroupArbitrator implements CharmGroupArbitrator {

  @Override
  public ICharm[] getCharms(ICharmGroup charmGroup) {
    return charmGroup.getAllCharms();
  }
}