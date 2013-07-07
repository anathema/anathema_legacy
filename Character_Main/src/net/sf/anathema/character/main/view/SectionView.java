package net.sf.anathema.character.main.view;

import net.sf.anathema.character.main.type.ICharacterType;

public interface SectionView {
  <T> T addView(String title, Class<T> viewClass, ICharacterType type);

  void finishInitialization();
}