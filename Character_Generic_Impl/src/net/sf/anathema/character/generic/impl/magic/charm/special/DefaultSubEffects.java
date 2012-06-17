package net.sf.anathema.character.generic.impl.magic.charm.special;

import net.sf.anathema.character.generic.magic.charms.special.ISubeffect;
import net.sf.anathema.character.generic.magic.charms.special.SubEffects;

public class DefaultSubEffects implements SubEffects{
  private final ISubeffect[] subeffects;

  public DefaultSubEffects(ISubeffect[] subeffects) {
    this.subeffects = subeffects;
  }

  @Override
  public ISubeffect[] getEffects() {
    return subeffects;
  }
}