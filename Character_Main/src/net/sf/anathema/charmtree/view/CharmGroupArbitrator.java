package net.sf.anathema.charmtree.view;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmGroup;

public interface CharmGroupArbitrator {

  ICharm[] getCharms(ICharmGroup charmGroup);
}