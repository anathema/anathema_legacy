package net.sf.anathema.hero.traits.model.types;

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