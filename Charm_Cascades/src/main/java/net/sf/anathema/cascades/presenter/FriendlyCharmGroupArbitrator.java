package net.sf.anathema.cascades.presenter;

import net.sf.anathema.hero.charms.display.presenter.CharmGroupArbitrator;
import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.ICharmGroup;

public class FriendlyCharmGroupArbitrator implements CharmGroupArbitrator {

  @Override
  public Charm[] getCharms(ICharmGroup charmGroup) {
    return charmGroup.getAllCharms();
  }
}