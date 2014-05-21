package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;

public interface MultiLearnCharmSpecials extends CharmSpecialsModel {

  Trait getCategory();

  void setCurrentLearnCount(int newValue);
}