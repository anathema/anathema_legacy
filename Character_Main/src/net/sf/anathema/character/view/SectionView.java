package net.sf.anathema.character.view;

import net.sf.anathema.character.generic.type.ICharacterType;

public interface SectionView {
  <T> T addView(String title, Class<T> viewClass, ICharacterType type);

  void finishInitialization();
}