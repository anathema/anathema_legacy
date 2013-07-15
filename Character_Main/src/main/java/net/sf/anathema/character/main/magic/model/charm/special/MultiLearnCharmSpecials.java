package net.sf.anathema.character.main.magic.model.charm.special;

import net.sf.anathema.character.main.library.trait.Trait;

public interface MultiLearnCharmSpecials extends CharmSpecialsModel {

  Trait getCategory();

  void setCurrentLearnCount(int newValue);
}