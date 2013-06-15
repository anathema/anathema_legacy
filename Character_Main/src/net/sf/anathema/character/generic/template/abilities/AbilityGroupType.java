package net.sf.anathema.character.generic.template.abilities;

import net.sf.anathema.lib.util.Identified;

public enum AbilityGroupType implements Identified {

  Life ,
  Wisdom,
  War;

  @Override
  public String getId() {
    return name();
  }
}