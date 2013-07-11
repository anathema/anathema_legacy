package net.sf.anathema.hero.othertraits.display;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;

@RegisteredCharacterView(AdvantageView.class)
public class AdvantageViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    FxOtherTraitsView fxView = new FxOtherTraitsView();
    return (T) new BridgingOtherTraitsView(fxView);
  }
}