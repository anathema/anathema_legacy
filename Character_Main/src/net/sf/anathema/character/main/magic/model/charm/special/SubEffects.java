package net.sf.anathema.character.main.magic.model.charm.special;

public interface SubEffects extends Iterable<SubEffect2> {
  SubEffect2[] getEffects();

  SubEffect2 getById(String id);
}