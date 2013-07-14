package net.sf.anathema.hero.concept.display.caste.view;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.hero.concept.display.caste.presenter.CasteView;

@RegisteredCharacterView(CasteView.class)
public class CasteViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(CharacterType type) {
    return (T) new BridgingCasteView(new FxCasteView());
  }
}