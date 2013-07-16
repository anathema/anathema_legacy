package net.sf.anathema.character.main.magic.charm.special;

public interface MultipleEffectCharmSpecials extends CharmSpecialsModel {
  SubEffect[] getEffects();

  SubEffect getEffectById(String id);
}