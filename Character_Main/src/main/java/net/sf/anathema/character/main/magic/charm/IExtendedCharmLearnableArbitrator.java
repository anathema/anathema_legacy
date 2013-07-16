package net.sf.anathema.character.main.magic.charm;

import net.sf.anathema.character.main.magic.charmtree.ICharmLearnArbitrator;
import net.sf.anathema.character.main.magic.charmtree.ICharmLearnableArbitrator;

public interface IExtendedCharmLearnableArbitrator extends ICharmLearnArbitrator, ICharmLearnableArbitrator {

  void addCharmLearnListener(ICharmLearnListener listener);

  boolean isCompulsiveCharm(Charm charm);
}