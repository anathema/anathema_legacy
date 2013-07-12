package net.sf.anathema.cascades.presenter;

import net.sf.anathema.character.main.magic.display.view.charmtree.CharmGroupArbitrator;
import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;

public class FriendlyCharmGroupArbitrator implements CharmGroupArbitrator {

  @Override
  public Charm[] getCharms(ICharmGroup charmGroup) {
    return charmGroup.getAllCharms();
  }
}