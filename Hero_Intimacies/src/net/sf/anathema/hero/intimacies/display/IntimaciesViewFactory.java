package net.sf.anathema.hero.intimacies.display;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;

@RegisteredCharacterView(IntimaciesView.class)
public class IntimaciesViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    FxIntimaciesView fxView = new FxIntimaciesView();
    return (T) new BridgingIntimaciesView(fxView, type);
  }
}
