package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.character.main.magic.model.charm.ILearningCharmGroup;

public interface ISpecialCharmManager {

  CharmSpecialsModel getSpecialCharmConfiguration(Charm charm);

  void registerSpecialCharmConfiguration(ISpecialCharm specialCharm, Charm charm, ILearningCharmGroup group);
}