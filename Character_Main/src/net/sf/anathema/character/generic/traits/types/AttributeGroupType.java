package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.lib.util.Identified;

public enum AttributeGroupType implements Identified {

  Physical, Social, Mental;

  @Override
  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }
}