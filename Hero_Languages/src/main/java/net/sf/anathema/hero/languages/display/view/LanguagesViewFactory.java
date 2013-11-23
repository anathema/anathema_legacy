package net.sf.anathema.hero.languages.display.view;

import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.framework.util.Produces;
import net.sf.anathema.hero.languages.display.presenter.LanguagesView;

@Produces(LanguagesView.class)
public class LanguagesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxLanguagesView();
  }
}