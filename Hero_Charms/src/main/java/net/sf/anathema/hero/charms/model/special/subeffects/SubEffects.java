package net.sf.anathema.hero.charms.model.special.subeffects;

public interface SubEffects extends Iterable<SubEffect> {
  SubEffect[] getEffects();

  SubEffect getById(String id);
}