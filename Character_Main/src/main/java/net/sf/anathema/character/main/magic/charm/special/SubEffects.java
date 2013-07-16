package net.sf.anathema.character.main.magic.charm.special;

public interface SubEffects extends Iterable<SubEffect> {
  SubEffect[] getEffects();

  SubEffect getById(String id);
}