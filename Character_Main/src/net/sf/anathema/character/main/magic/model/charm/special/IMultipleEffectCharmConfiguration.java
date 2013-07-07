package net.sf.anathema.character.main.magic.model.charm.special;

public interface IMultipleEffectCharmConfiguration extends ISpecialCharmConfiguration {
  ISubeffect[] getEffects();

  ISubeffect getEffectById(String id);
}