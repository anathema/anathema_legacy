package net.sf.anathema.character.main.view;

public interface SectionView {
  <T> T addView(String title, Class<T> viewClass);

  void finishInitialization();
}