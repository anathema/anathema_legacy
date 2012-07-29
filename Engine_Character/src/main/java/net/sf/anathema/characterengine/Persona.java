package net.sf.anathema.characterengine;

public interface Persona {
  void execute(Command command);

  void doFor(QualityKey qualityKey, Closure closure);
}