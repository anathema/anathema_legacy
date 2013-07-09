package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;

public interface ISpecialCharmManager {

  ISpecialCharmConfiguration getSpecialCharmConfiguration(Charm charm);

  void registerSpecialCharmConfiguration(ISpecialCharm specialCharm, Charm charm, ILearningCharmGroup group);
}