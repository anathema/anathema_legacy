package net.sf.anathema.hero.spiritual.display;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.library.util.CssSkinner;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.platform.fx.Stylesheet;

@RegisteredCharacterView(SpiritualTraitsView.class)
public class SpiritualTraitsViewFactory implements SubViewFactory {

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) new FxSpiritualTraitsView();
  }
}