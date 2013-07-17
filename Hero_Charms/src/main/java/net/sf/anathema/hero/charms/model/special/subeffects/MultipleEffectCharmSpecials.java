package net.sf.anathema.hero.charms.model.special.subeffects;

import net.sf.anathema.hero.charms.model.special.CharmSpecialsModel;
import net.sf.anathema.character.main.magic.charm.special.SubEffect;

public interface MultipleEffectCharmSpecials extends CharmSpecialsModel {
  SubEffect[] getEffects();

  SubEffect getEffectById(String id);
}