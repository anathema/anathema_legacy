package net.sf.anathema.equipment.core;

import net.sf.anathema.lib.util.Identifier;

public enum MaterialComposition implements Identifier {
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
  },
  OtherWondrous(){
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