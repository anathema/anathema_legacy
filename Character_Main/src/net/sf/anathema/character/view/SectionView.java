package net.sf.anathema.character.view;

public interface SectionView {
  <T> T addView(String title, Class<T> viewClass);
}