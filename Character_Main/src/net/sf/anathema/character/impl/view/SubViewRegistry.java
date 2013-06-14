package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.generic.type.ICharacterType;

public interface SubViewRegistry {
  <T> T get(Class<T> viewClass, ICharacterType type);
}