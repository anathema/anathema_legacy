package net.sf.anathema.hero.concept.display.caste.view;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.hero.concept.display.caste.presenter.CasteView;
import net.sf.anathema.platform.fx.Stylesheet;

@RegisteredCharacterView(CasteView.class)
public class CasteViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(CharacterType type) {
    FxCasteView fxView = new FxCasteView();
    new Stylesheet("skin/concept/concept.css").applyToParent(fxView.getNode());
    return (T) fxView;
  }
}