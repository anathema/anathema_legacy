package net.sf.anathema.characterengine;

public class DefaultPersona implements Persona {
  private final Qualities qualities;

  public DefaultPersona(Qualities qualities) {
    this.qualities = qualities;
  }

  @Override
  public void execute(Command command) {
    command.execute(qualities);
  }

  @Override
  public void doFor(QualityKey qualityKey, Closure closure) {
    qualities.doFor(qualityKey, closure);
  }
}