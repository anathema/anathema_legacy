package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;

public interface MultipleEffectCharmSpecials extends CharmSpecialsModel {
  SubEffect[] getEffects();

  SubEffect getEffectById(String id);
}