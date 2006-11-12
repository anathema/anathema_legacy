package net.sf.anathema.character.db.aspect;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum DBAspect implements ICasteType<IDBAspectVisitor> {

  Air {
    public void accept(IDBAspectVisitor visitor) {
      visitor.visitAir(this);
    }
  },
  Earth {
    public void accept(IDBAspectVisitor visitor) {
      visitor.visisEarth(this);
    }
  },
  Fire {
    public void accept(IDBAspectVisitor visitor) {
      visitor.visitFire(this);
    }
  },
  Water {
    public void accept(IDBAspectVisitor visitor) {
      visitor.visitWater(this);
    }
  },
  Wood {
    public void accept(IDBAspectVisitor visitor) {
      visitor.visitWood(this);
    }
  };

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return name();
  }
}