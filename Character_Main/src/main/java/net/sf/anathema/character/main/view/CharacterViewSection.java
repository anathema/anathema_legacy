package net.sf.anathema.character.main.view;

import net.sf.anathema.hero.display.ContentProperties;
import net.sf.anathema.hero.display.MultipleContentView;
import net.sf.anathema.platform.fx.NodeHolder;

//TODO (Swing->FX): Move to Character_Main_FX
public class CharacterViewSection implements SectionView {

  private final MultipleContentView view;
  private final SubViewRegistry subViewFactory;

  public CharacterViewSection(CharacterPane characterPane, String title, SubViewRegistry subViewFactory) {
    this.view = characterPane.addMultipleContentView(title);
    this.subViewFactory = subViewFactory;
  }

  @Override
  public <T> T addView(String title, Class<T> viewClass) {
    T newView = subViewFactory.get(viewClass);
    NodeHolder viewToAdd = (NodeHolder) newView;
    view.addView(viewToAdd, new ContentProperties(title));
    return newView;
  }

  @Override
  public void finishInitialization() {
    view.finishInitialization();
  }
}