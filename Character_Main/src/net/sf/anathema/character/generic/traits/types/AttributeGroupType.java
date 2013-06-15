package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.lib.util.Identifier;

public enum AttributeGroupType implements Identifier {

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