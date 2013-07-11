package net.sf.anathema.character.main.magic.model.charm;

import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.special.ISpecialCharm;
import net.sf.anathema.character.main.magic.model.charms.ILearningCharmGroup;

public interface ISpecialCharmManager {

  CharmSpecialsModel getSpecialCharmConfiguration(Charm charm);

  void registerSpecialCharmConfiguration(ISpecialCharm specialCharm, Charm charm, ILearningCharmGroup group);
}