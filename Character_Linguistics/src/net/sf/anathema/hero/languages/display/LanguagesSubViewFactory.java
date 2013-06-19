package net.sf.anathema.hero.languages.display;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.view.SubViewFactory;
import net.sf.anathema.character.platform.RegisteredCharacterView;

@RegisteredCharacterView(LanguagesView.class)
public class LanguagesSubViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new LanguagesViewImpl();
  }
}