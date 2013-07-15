package net.sf.anathema.fx.hero.configurableview;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.hero.display.configurableview.ConfigurableCharacterView;

@RegisteredCharacterView(ConfigurableCharacterView.class)
public class ConfigurableViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(CharacterType type) {
    FxConfigurableView view = new FxConfigurableView();
    return (T) new BridgingConfigurableView(view, type);
  }
}