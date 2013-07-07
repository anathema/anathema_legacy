package net.sf.anathema.character.main.view;

import net.sf.anathema.character.main.type.ICharacterType;

public interface SubViewRegistry {
  <T> T get(Class<T> viewClass, ICharacterType type);
}