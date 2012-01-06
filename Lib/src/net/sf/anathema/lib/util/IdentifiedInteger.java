package net.sf.anathema.lib.util;

public class IdentifiedInteger extends Identificate {

  private int value;
  
  public IdentifiedInteger(String id, int value) {
    super(id);
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
