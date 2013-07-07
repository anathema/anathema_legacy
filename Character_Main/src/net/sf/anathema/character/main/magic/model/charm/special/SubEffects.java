package net.sf.anathema.character.main.magic.model.charm.special;

public interface SubEffects extends Iterable<ISubeffect> {
  ISubeffect[] getEffects();

  ISubeffect getById(String id);
}