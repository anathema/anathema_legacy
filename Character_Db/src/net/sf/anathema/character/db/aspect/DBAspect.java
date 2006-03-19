package net.sf.anathema.character.db.aspect;

import net.sf.anathema.character.generic.caste.ICasteType;

public enum DBAspect implements ICasteType<IDBAspectVisitor> {

  Air {
    @Override
    public void accept(IDBAspectVisitor visitor) {
      visitor.visitAir(this);
    }
  },
  Earth {
    @Override
    public void accept(IDBAspectVisitor visitor) {
      visitor.visisEarth(this);
    }
  },
  Fire {
    @Override
    public void accept(IDBAspectVisitor visitor) {
      visitor.visitFire(this);
    }
  },
  Water {
    @Override
    public void accept(IDBAspectVisitor visitor) {
      visitor.visitWater(this);
    }
  },
  Wood {
    @Override
    public void accept(IDBAspectVisitor visitor) {
      visitor.visitWood(this);
    }
  };

  public String getId() {
    return name();
  }

  public abstract void accept(IDBAspectVisitor visitor);

  @Override
  public String toString() {
    return getId();
  }
}