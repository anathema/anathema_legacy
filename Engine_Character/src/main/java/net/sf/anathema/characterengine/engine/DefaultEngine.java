package net.sf.anathema.characterengine.engine;

import net.sf.anathema.characterengine.persona.DefaultPersona;
import net.sf.anathema.characterengine.persona.DefaultQualities;
import net.sf.anathema.characterengine.persona.Persona;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.Type;

import java.util.HashMap;
import java.util.Map;

public class DefaultEngine implements Engine {

  private final Map<Type, QualityFactory> factoryMap = new HashMap<Type, QualityFactory>();

  @Override
  public void setFactory(Type type, QualityFactory factory) {
    factoryMap.put(type, factory);
  }

  @Override
  public Persona createCharacter() {
    return new DefaultPersona(new DefaultQualities(this));
  }

  @Override
  public Quality createQuality(Type type) {
    if (!factoryMap.containsKey(type)) {
      throw new UnknownQualityTypeException(type);
    }
    QualityFactory factory = factoryMap.get(type);
    return factory.create();
  }
}