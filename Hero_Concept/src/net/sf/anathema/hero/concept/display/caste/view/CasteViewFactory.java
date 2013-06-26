package net.sf.anathema.hero.concept.display.caste.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.character.view.SubViewFactory;

@RegisteredCharacterView(CasteView.class)
public class CasteViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new SimpleCasteView();
  }
}