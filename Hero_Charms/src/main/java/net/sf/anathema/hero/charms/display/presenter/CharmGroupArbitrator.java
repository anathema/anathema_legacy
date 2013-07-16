package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmGroup;

public interface CharmGroupArbitrator {

  Charm[] getCharms(ICharmGroup charmGroup);
}