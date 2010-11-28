package net.sf.anathema.character.model.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;

public interface IMultipleEffectCharmConfiguration extends ISpecialCharmConfiguration {
  public ISubeffect[] getEffects();

  public ISubeffect getEffectById(String id);
}