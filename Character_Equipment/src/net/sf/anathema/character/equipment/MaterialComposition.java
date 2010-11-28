package net.sf.anathema.character.equipment;

import net.sf.anathema.lib.util.IIdentificate;

public enum MaterialComposition implements IIdentificate {
  None() {
    @Override
    public boolean requiresMaterial() {
      return false;
    }
  },
  Fixed() {
    @Override
    public boolean requiresMaterial() {
      return true;
    }
  },
  Variable() {
    @Override
    public boolean requiresMaterial() {
      return false;
    }
  },
  Compound() {
    @Override
    public boolean requiresMaterial() {
      return false;
    }
  };

  public String getId() {
    return name();
  }

  public abstract boolean requiresMaterial();
}