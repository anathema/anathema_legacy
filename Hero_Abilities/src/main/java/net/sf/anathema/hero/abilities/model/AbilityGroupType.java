package net.sf.anathema.hero.abilities.model;

import net.sf.anathema.lib.util.Identifier;

public enum AbilityGroupType implements Identifier {

  Life ,
  Wisdom,
  War;

  @Override
  public String getId() {
    return name();
  }
}