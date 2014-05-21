package net.sf.anathema.character.framework.display;

public interface SectionView {
  <T> T addView(String title, Class<T> viewClass);

  void finishInitialization();
}