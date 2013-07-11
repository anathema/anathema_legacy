package net.sf.anathema.hero.charms.persistence.special;

import net.sf.anathema.character.main.magic.model.charm.Charm;
import net.sf.anathema.hero.charms.model.CharmsModel;

public interface SpecialCharmPersister {

  void saveCharmSpecials(CharmsModel charmsModel, Charm charm, SpecialCharmPto charmPto);
}
