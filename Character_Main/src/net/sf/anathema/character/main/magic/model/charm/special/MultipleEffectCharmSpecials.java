package net.sf.anathema.character.main.magic.model.charm.special;

public interface MultipleEffectCharmSpecials extends CharmSpecialsModel {
  SubEffect2[] getEffects();

  SubEffect2 getEffectById(String id);
}