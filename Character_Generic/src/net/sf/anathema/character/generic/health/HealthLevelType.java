package net.sf.anathema.character.generic.health;

import net.sf.anathema.lib.util.IIdentificate;

public enum HealthLevelType implements IIdentificate {

  ZERO("0", 0) { //$NON-NLS-1$
    @Override
    public void accept(IHealthLevelTypeVisitor visitor) {
      visitor.visitZero(this);
    }
  },
  ONE("1", -1) { //$NON-NLS-1$
    @Override
    public void accept(IHealthLevelTypeVisitor visitor) {
      visitor.visitOne(this);
    }
  },
  TWO("2", -2) { //$NON-NLS-1$
    @Override
    public void accept(IHealthLevelTypeVisitor visitor) {
      visitor.visitTwo(this);
    }
  },
  FOUR("4", -4) { //$NON-NLS-1$
    @Override
    public void accept(IHealthLevelTypeVisitor visitor) {
      visitor.visitFour(this);
    }
  },
  INCAPACITATED("Incapacitated", Integer.MIN_VALUE) { //$NON-NLS-1$
    @Override
    public void accept(IHealthLevelTypeVisitor visitor) {
      visitor.visitIncapacitated(this);
    }
  },
  DYING("Dying", Integer.MIN_VALUE) { //$NON-NLS-1$
	    @Override
	    public void accept(IHealthLevelTypeVisitor visitor) {
	      visitor.visitDying(this);
	    }
	  };

  private final String id;
  private final int value;

  public String getId() {
    return id;
  }

  private HealthLevelType(String id, int value) {
    this.id = id;
    this.value = value;
  }

  public abstract void accept(IHealthLevelTypeVisitor visitor);

  @Override
  public String toString() {
    return getId();
  }

  public int getIntValue() {
    return value;
  }
}