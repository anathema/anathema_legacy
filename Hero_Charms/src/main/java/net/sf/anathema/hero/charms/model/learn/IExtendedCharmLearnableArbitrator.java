package net.sf.anathema.hero.charms.model.learn;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.character.main.magic.charm.ICharmLearnArbitrator;

public interface IExtendedCharmLearnableArbitrator extends ICharmLearnArbitrator, ICharmLearnableArbitrator {

  void addCharmLearnListener(ICharmLearnListener listener);

  boolean isCompulsiveCharm(Charm charm);
}