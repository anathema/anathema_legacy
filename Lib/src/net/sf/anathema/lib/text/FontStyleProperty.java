package net.sf.anathema.lib.text;

public enum FontStyleProperty {

  BOLD("Bold"), ITALICS("Italics"); //$NON-NLS-1$ //$NON-NLS-2$

  private final String name;

  private FontStyleProperty(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
