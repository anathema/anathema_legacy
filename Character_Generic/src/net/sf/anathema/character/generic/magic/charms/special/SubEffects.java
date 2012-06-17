package net.sf.anathema.character.generic.magic.charms.special;

public interface SubEffects extends Iterable<ISubeffect> {
  ISubeffect[] getEffects();

  ISubeffect getById(String id);
}