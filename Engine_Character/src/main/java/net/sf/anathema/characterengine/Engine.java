package net.sf.anathema.characterengine;

public interface Engine {
  void setFactory(Type type, QualityFactory factory);

  Persona createCharacter();

  Quality createQuality(Type type);
}