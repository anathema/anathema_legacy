package net.sf.anathema.character.main.type;

import net.sf.anathema.framework.environment.ObjectFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class ReflectionCharacterTypes implements CharacterTypes {

  private final List<CharacterType> types = new ArrayList<>();

  public ReflectionCharacterTypes(ObjectFactory objectFactory) {
    Collection<CharacterType> types = objectFactory.instantiateOrdered(RegisteredCharacterType.class);
    this.types.addAll(types);
  }

  @Override
  public CharacterType findById(String id) {
    for (CharacterType type : types) {
      if (type.getId().equals(id)) {
        return type;
      }
    }
    throw new IllegalArgumentException("No type defined for id:" + id);
  }

  @Override
  public Iterator<CharacterType> iterator() {
    return types.iterator();
  }
}