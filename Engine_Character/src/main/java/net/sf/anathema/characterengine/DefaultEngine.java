package net.sf.anathema.characterengine;

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