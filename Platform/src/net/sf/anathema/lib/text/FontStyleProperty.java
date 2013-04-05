package net.sf.anathema.lib.text;

public enum FontStyleProperty {

  BOLD("Bold"), ITALICS("Italics");

  private final String name;

  private FontStyleProperty(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
