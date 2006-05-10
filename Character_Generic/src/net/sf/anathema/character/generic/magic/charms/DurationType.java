package net.sf.anathema.character.generic.magic.charms;

import net.sf.anathema.lib.util.IIdentificate;

public enum DurationType implements IIdentificate {
  Instant, Other, Permanent;

  public static DurationType getById(String id) {
    for (DurationType type : values()) {
      if (type.getId().equals(id)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No duration type defined for id:" + id); //$NON-NLS-1$
  }

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }
}