package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.ICharmLearnArbitrator;

public interface ICharmLearnableArbitrator extends ICharmLearnArbitrator {

  public boolean isLearnable(ICharm charm);

  public void addCharmLearnListener(ICharmLearnListener listener);

  public boolean isCompulsiveCharm(ICharm charm);
}