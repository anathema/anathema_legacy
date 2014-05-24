package net.sf.anathema.hero.type;

import net.sf.anathema.character.framework.type.CharacterType;
import net.sf.anathema.character.framework.type.CharacterTypes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExtensibleCharacterTypes implements CharacterTypes{

  private final List<CharacterType> types = new ArrayList<>();

  public void add(CharacterType type) {
    types.add(type);
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