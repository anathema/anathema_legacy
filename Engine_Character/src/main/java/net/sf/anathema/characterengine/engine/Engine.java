package net.sf.anathema.characterengine.engine;

import net.sf.anathema.characterengine.persona.Persona;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.Type;

public interface Engine {
  void setFactory(Type type, QualityFactory factory);

  Persona createCharacter();

  Quality createQuality(Type type);
}