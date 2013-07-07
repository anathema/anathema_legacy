package net.sf.anathema.character.main.magic.model.charms;

import net.sf.anathema.character.main.magic.model.charm.ICharmLearnListener;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnableArbitrator;
import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charmtree.ICharmLearnArbitrator;

public interface IExtendedCharmLearnableArbitrator extends ICharmLearnArbitrator, ICharmLearnableArbitrator {

  void addCharmLearnListener(ICharmLearnListener listener);

  boolean isCompulsiveCharm(ICharm charm);
}