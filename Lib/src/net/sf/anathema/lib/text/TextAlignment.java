package net.sf.anathema.lib.text;

public enum TextAlignment {
  CENTER("Center"),
  LEFT("Left"),
  RIGHT("Right");

  private final String description;

  private TextAlignment(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}