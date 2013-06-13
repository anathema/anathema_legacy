package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.view.util.ContentProperties;

public class CharacterViewSection implements SectionView {

  private final MultipleContentView view;

  public CharacterViewSection(CharacterPane characterPane, String title) {
    view = characterPane.addMultipleContentView(title);
  }

  @Override
  public <T> T addView(String title, Class<T> viewClass) {
    T newView = new SubViewMap().get(viewClass);
    IView viewToAdd = (IView) newView;
    view.addView(viewToAdd, new ContentProperties(title));
    return newView;
  }
}
