package net.sf.anathema.lib.model;

public class BooleanModel extends AbstractChangeableModel implements IModifiableBooleanModel {

  private boolean value;

  public BooleanModel() {
    this(false);
  }

  public BooleanModel(boolean value) {
    this.value = value;
  }

  @Override
  public boolean getValue() {
    return value;
  }

  @Override
  public void setValue(boolean selected) {
    if (this.value == selected) {
      return;
    }
    this.value = selected;
    fireChangeEvent();
  }

  @Override
  public String toString() {
    return "BooleanModel: " + value; //$NON-NLS-1$
  }

}