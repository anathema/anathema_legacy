package net.sf.anathema.lib.control.legality;

import net.sf.anathema.lib.util.IIdentificate;

public enum ValueLegalityState implements IIdentificate {

  Low {
    @Override
    public void accept(IValueLegalityStateVisitor visitor) {
      visitor.visitLow(this);
    }
  },
  Lowered {
    @Override
    public void accept(IValueLegalityStateVisitor visitor) {
      visitor.visitLowered(this);
    }
  },
  Okay {
    @Override
    public void accept(IValueLegalityStateVisitor visitor) {
      visitor.visitOkay(this);
    }
  },
  Increased {
    @Override
    public void accept(IValueLegalityStateVisitor visitor) {
      visitor.visitIncreased(this);
    }
  },
  High {
    @Override
    public void accept(IValueLegalityStateVisitor visitor) {
      visitor.visitHigh(this);
    }
  };

  public String getId() {
    return name();
  }

  public static ValueLegalityState max(ValueLegalityState firstState, ValueLegalityState secondState) {
    int stateCompare = firstState.compareTo(secondState);
    if (stateCompare < 0) {
      return secondState;
    }
    else if (stateCompare > 0) {
      return firstState;
    }
    else {
      return firstState;
    }
  }

  public abstract void accept(IValueLegalityStateVisitor visitor);

  public static ValueLegalityState getState(int value, int debit) {
    if (value == debit) {
      return Okay;
    }
    else if (value < debit) {
      return Low;
    }
    return High;
  }

  public static ValueLegalityState getUncriticalState(int value, int debit) {
    if (value == debit) {
      return Okay;
    }
    else if (value < debit) {
      return Lowered;
    }
    return Increased;
  }

  public static ValueLegalityState getState(int value, int lowerLimit, int debit, int higherLimit) {
    if (value == debit) {
      return Okay;
    }
    else if (value < debit) {
      return value < lowerLimit ? Low : Lowered;
    }
    return value > higherLimit ? High : Increased;
  }

  @Override
  public String toString() {
    return getId();
  }

}