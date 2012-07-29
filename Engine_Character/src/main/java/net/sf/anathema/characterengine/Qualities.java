package net.sf.anathema.characterengine;

public interface Qualities {

  void addQuality(Type type, Name name);

  void doFor(Type type, Name name, Closure closure);
}