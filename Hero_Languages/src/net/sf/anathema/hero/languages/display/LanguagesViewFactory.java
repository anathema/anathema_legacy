package net.sf.anathema.hero.languages.display;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.view.SubViewFactory;

@RegisteredCharacterView(LanguagesView.class)
public class LanguagesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new LanguagesViewImpl();
  }
}