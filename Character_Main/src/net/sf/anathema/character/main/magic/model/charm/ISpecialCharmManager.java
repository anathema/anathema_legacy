package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;

public interface ISpecialCharmManager {

  ISpecialCharmConfiguration getSpecialCharmConfiguration(ICharm charm);

  void registerSpecialCharmConfiguration(ISpecialCharm specialCharm, ICharm charm, ILearningCharmGroup group);
}