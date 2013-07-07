package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.ICharmGroup;
import net.sf.anathema.character.main.charmtree.view.CharmGroupArbitrator;

public class FriendlyCharmGroupArbitrator implements CharmGroupArbitrator {

  @Override
  public ICharm[] getCharms(ICharmGroup charmGroup) {
    return charmGroup.getAllCharms();
  }
}