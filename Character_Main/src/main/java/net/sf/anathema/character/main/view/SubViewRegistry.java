package net.sf.anathema.character.main.view;

import net.sf.anathema.character.main.type.CharacterType;

public interface SubViewRegistry {
  <T> T get(Class<T> viewClass, CharacterType type);
}