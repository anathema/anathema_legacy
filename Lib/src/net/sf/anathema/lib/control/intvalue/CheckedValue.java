package net.sf.anathema.lib.control.intvalue;

import net.sf.anathema.lib.control.legality.ValueLegalityState;

public class CheckedValue {

  private final int correctedValue;
  private final ValueLegalityState state;

  public CheckedValue(int correctedValue, ValueLegalityState correctedState) {
    this.correctedValue = correctedValue;
    this.state = correctedState;
  }

  public int getCorrectedValue() {
    return correctedValue;
  }

  public ValueLegalityState getState() {
    return state;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CheckedValue)) {
      return false;
    }
    CheckedValue other = (CheckedValue) obj;
    return other.correctedValue == correctedValue && other.state == state;
  }

  @Override
  public int hashCode() {
    return correctedValue;
  }

  @Override
  public String toString() {
    return state + "(" + correctedValue + ")"; //$NON-NLS-1$ //$NON-NLS-2$ 
  }
}