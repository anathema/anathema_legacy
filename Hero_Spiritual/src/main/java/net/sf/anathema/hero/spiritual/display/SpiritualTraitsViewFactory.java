package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;

@RegisteredCharacterView(SpiritualTraitsView.class)
public class SpiritualTraitsViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    FxSpiritualTraitsView fxView = new FxSpiritualTraitsView();
    return (T) new BridgingSpiritualTraitsView(fxView);
  }
}