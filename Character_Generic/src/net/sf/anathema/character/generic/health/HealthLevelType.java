package net.sf.anathema.character.generic.health;

import net.sf.anathema.lib.util.IIdentificate;

public enum HealthLevelType implements IIdentificate {

  ZERO("0") { //$NON-NLS-1$
    @Override
    public void accept(IHealthLevelTypeVisitor visitor) {
      visitor.visitZero(this);
    }
  },
  ONE("1") { //$NON-NLS-1$
    @Override
    public void accept(IHealthLevelTypeVisitor visitor) {
      visitor.visitOne(this);
    }
  },
  TWO("2") { //$NON-NLS-1$
    @Override
    public void accept(IHealthLevelTypeVisitor visitor) {
      visitor.visitTwo(this);
    }
  },
  FOUR("4") { //$NON-NLS-1$
    @Override
    public void accept(IHealthLevelTypeVisitor visitor) {
      visitor.visitFour(this);
    }
  },
  INCAPACITATED("Incapacitated") { //$NON-NLS-1$
    @Override
    public void accept(IHealthLevelTypeVisitor visitor) {
      visitor.visitIncapacitated(this);
    }
  };

  private final String id;

  public String getId() {
    return id;
  }

  private HealthLevelType(String id) {
    this.id = id;
  }

  public abstract void accept(IHealthLevelTypeVisitor visitor);

  @Override
  public String toString() {
    return getId();
  }
}