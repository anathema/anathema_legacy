package net.sf.anathema.lib.data;

public class IntAttribute {

  private String description;
  private int value;

  public IntAttribute(String description, int value) {
    this.value = value;
    this.description = description;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    return description;
  }

  public String getDescription() {
    return description;
  }

}