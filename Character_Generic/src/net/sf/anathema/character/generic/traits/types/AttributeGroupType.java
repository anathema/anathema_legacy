package net.sf.anathema.character.generic.traits.types;

import net.sf.anathema.lib.util.IIdentificate;

public enum AttributeGroupType implements IIdentificate {

  Physical, Social, Mental;

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }
}