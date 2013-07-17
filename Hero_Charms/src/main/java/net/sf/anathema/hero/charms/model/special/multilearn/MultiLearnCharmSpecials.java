package net.sf.anathema.hero.charms.model.special.multilearn;

import net.sf.anathema.character.main.library.trait.Trait;
import net.sf.anathema.character.main.magic.charm.special.CharmSpecialsModel;

public interface MultiLearnCharmSpecials extends CharmSpecialsModel {

  Trait getCategory();

  void setCurrentLearnCount(int newValue);
}