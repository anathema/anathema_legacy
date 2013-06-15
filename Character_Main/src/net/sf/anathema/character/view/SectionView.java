package net.sf.anathema.character.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.framework.presenter.view.ContentView;

public interface SectionView {
  <T> T addView(String title, Class<T> viewClass, ICharacterType type);

  @Deprecated
  void addView(ContentView tabContent);
}