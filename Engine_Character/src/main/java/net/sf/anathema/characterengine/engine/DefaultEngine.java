package net.sf.anathema.characterengine.engine;

import net.sf.anathema.characterengine.persona.DefaultPersona;
import net.sf.anathema.characterengine.persona.DefaultQualities;
import net.sf.anathema.characterengine.persona.Persona;
import net.sf.anathema.characterengine.quality.Name;
import net.sf.anathema.characterengine.quality.NameClosure;
import net.sf.anathema.characterengine.quality.Quality;
import net.sf.anathema.characterengine.quality.QualityKey;
import net.sf.anathema.characterengine.quality.Type;
import net.sf.anathema.characterengine.quality.TypeClosure;

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
  public Quality createQuality(QualityKey key) {
    FindFactory findFactory = new FindFactory();
    key.withTypeDo(findFactory);
    return findFactory.create(key);
  }

  private class FindFactory implements TypeClosure {

    private QualityFactory factory;
    private Quality quality;

    @Override
    public void execute(Type type) {
      if (!factoryMap.containsKey(type)) {
        throw new UnknownQualityTypeException(type);
      }
      factory = factoryMap.get(type);
    }

    public Quality create(QualityKey key) {
      key.withNameDo(new NameClosure() {
        @Override
        public void execute(Name name) {
          quality = factory.create(name);
        }
      });
      return quality;
    }
  }
}