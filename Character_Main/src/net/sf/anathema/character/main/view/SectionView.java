package net.sf.anathema.character.main.view;

import net.sf.anathema.character.main.type.CharacterType;

public interface SectionView {
  <T> T addView(String title, Class<T> viewClass, CharacterType type);

  void finishInitialization();
}