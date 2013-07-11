package net.sf.anathema.hero.charms.persistence.special;

import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;

public interface SpecialCharmPersister {

  void saveCharmSpecials(CharmSpecialsModel charmSpecials, SpecialCharmPto charmPto);
}
