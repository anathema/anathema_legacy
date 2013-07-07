package net.sf.anathema.character.main.magic.charms.special;

public interface IMultipleEffectCharmConfiguration extends ISpecialCharmConfiguration {
  ISubeffect[] getEffects();

  ISubeffect getEffectById(String id);
}