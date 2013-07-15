package net.sf.anathema.hero.model.change;

public class ChangeFlavor {

  public static final ChangeFlavor UNSPECIFIED = new ChangeFlavor("Unspecified");

  private String name;

  public ChangeFlavor(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Flavor: " + name;
  }
}
