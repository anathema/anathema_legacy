package net.sf.anathema.hero.spells.display.view;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.hero.spells.display.presenter.SpellView;

@RegisteredCharacterView(SpellView.class)
public class SpellViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(CharacterType type) {
    FxSpellView fxView = new FxSpellView();
    return (T) new BridgingSpellView(fxView);
  }
}