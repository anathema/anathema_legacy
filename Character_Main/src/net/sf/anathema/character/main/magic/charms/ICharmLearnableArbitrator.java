package net.sf.anathema.character.main.magic.charms;

import net.sf.anathema.character.main.magic.ICharm;

public interface ICharmLearnableArbitrator {
  boolean isLearnable(ICharm charm);
}