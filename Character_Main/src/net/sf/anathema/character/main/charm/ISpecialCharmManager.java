package net.sf.anathema.character.main.charm;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharm;
import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;

public interface ISpecialCharmManager {

  ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm);

  void registerSpecialCharmConfiguration(ISpecialCharm specialCharm, ICharm charm, ILearningCharmGroup group);
}