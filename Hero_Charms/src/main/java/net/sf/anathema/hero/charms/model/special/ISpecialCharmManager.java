package net.sf.anathema.hero.charms.model.special;

import net.sf.anathema.character.main.magic.charm.Charm;
import net.sf.anathema.hero.charms.model.ILearningCharmGroup;
import net.sf.anathema.hero.charms.model.special.charms.ISpecialCharm;

public interface ISpecialCharmManager {

  CharmSpecialsModel getSpecialCharmConfiguration(Charm charm);

  void registerSpecialCharmConfiguration(ISpecialCharm specialCharm, Charm charm, ILearningCharmGroup group);
}