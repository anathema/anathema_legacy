package net.sf.anathema.character.main.magic.charms;

import net.sf.anathema.character.main.magic.ICharm;

public interface IExtendedCharmLearnableArbitrator extends ICharmLearnArbitrator, ICharmLearnableArbitrator {

  void addCharmLearnListener(ICharmLearnListener listener);

  boolean isCompulsiveCharm(ICharm charm);
}