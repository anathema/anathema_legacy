package net.sf.anathema.character.main.testing.dummy;

import net.sf.anathema.lib.data.Condition;

public class DummyCondition implements Condition {

  private boolean value;

  @Override
  public boolean isFulfilled() {
    return value;
  }

  public void setValue(boolean value) {
    this.value = value;
  }

}
