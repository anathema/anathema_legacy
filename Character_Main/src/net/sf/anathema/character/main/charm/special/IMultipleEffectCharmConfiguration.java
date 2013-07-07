package net.sf.anathema.character.main.charm.special;

import net.sf.anathema.character.main.magic.charms.special.ISpecialCharmConfiguration;
import net.sf.anathema.character.main.magic.charms.special.ISubeffect;

public interface IMultipleEffectCharmConfiguration extends ISpecialCharmConfiguration {
  ISubeffect[] getEffects();

  ISubeffect getEffectById(String id);
}