package net.sf.anathema.character.equipment;

import net.sf.anathema.lib.util.Identified;

public enum MaterialComposition implements Identified {
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
  },
  MalfeanMaterials() {
    @Override
    public boolean requiresMaterial() {
      return false;
    }
  };

  @Override
  public String getId() {
    return name();
  }

  public abstract boolean requiresMaterial();
}