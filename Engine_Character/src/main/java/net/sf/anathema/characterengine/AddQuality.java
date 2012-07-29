package net.sf.anathema.characterengine;

public class AddQuality implements Command {
  private final Type type;
  private final Name name;

  public AddQuality(Type type, Name name) {
    this.type = type;
    this.name = name;
  }

  @Override
  public void execute(Qualities qualities) {
    qualities.addQuality(type, name);
  }
}