package net.sf.anathema.character.main.magic.display.view.charmtree;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.ICharmGroup;

public interface CharmGroupArbitrator {

  ICharm[] getCharms(ICharmGroup charmGroup);
}