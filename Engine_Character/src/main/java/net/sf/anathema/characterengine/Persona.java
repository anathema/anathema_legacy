package net.sf.anathema.characterengine;

public interface Persona {
  void execute(Command command);

  void doFor(Type type, Name name, Closure closure);
}