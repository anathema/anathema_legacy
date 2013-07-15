package net.sf.anathema.hero.charms.display.presenter;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;

public interface CharmGroupArbitrator {

  Charm[] getCharms(ICharmGroup charmGroup);
}