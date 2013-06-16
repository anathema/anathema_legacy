package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.presenter.view.MultipleContentView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.view.util.ContentProperties;

public class CharacterViewSection implements SectionView {

  private final MultipleContentView view;
  private final SubViewRegistry subViewFactory;

  public CharacterViewSection(CharacterPane characterPane, String title, SubViewRegistry subViewFactory) {
    this.view = characterPane.addMultipleContentView(title);
    this.subViewFactory = subViewFactory;
  }

  @Override
  public <T> T addView(String title, Class<T> viewClass, ICharacterType type) {
    T newView = subViewFactory.get(viewClass, type);
    IView viewToAdd = (IView) newView;
    view.addView(viewToAdd, new ContentProperties(title));
    return newView;
  }

  @Override
  public void finishInitialization() {
    view.finishInitialization();
  }
}