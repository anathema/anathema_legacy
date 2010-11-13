package net.sf.anathema.character.generic.health;

import net.sf.anathema.lib.util.IIdentificate;

public enum HealthType implements IIdentificate {
  Bashing {
    @Override
    public void accept(IHealthTypeVisitor visitor) {
      visitor.visitBashing(this);
    }
  },
  Lethal {
    @Override
    public void accept(IHealthTypeVisitor visitor) {
      visitor.visitLethal(this);
    }
  },
  Aggravated {
    @Override
    public void accept(IHealthTypeVisitor visitor) {
      visitor.visitAggravated(this);
    }
  };

  public String getId() {
    return name();
  }

  @Override
  public String toString() {
    return getId();
  }

  public abstract void accept(IHealthTypeVisitor visitor);
}