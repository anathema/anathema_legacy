package net.sf.anathema.character.impl.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.model.charm.ILearningCharmGroup;

public interface ISpecialCharmManager {

  ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm);

  void registerSpecialCharmConfiguration(ISpecialCharm specialCharm, ICharm charm, ILearningCharmGroup group);
}