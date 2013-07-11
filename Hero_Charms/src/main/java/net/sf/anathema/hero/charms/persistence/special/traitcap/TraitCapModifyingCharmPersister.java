package net.sf.anathema.hero.charms.persistence.special.traitcap;

import net.sf.anathema.character.main.magic.model.charm.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.model.charm.special.TraitCapModifyingCharmConfiguration;
import net.sf.anathema.hero.charms.persistence.special.CharmSpecialsPto;
import net.sf.anathema.hero.charms.persistence.special.SpecialCharmPersister;

public class TraitCapModifyingCharmPersister implements SpecialCharmPersister {
  @Override
  public void saveCharmSpecials(CharmSpecialsModel charmSpecials, CharmSpecialsPto specialsPto) {
    // nothing to do
  }

  @Override
  public void loadCharmSpecials(CharmSpecialsModel charmSpecials, CharmSpecialsPto specialsPto) {
    ((TraitCapModifyingCharmConfiguration) charmSpecials).applyModifier();
  }
}
