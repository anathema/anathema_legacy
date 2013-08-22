package net.sf.anathema.fx.hero.configurableview;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.library.util.CssSkinner;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.hero.display.configurableview.ConfigurableCharacterView;
import net.sf.anathema.platform.fx.Stylesheet;

@RegisteredCharacterView(ConfigurableCharacterView.class)
public class ConfigurableViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(CharacterType type) {
    FxConfigurableView view = new FxConfigurableView();
    String[] skins = new CssSkinner().getSkins(type);
    for (String skin : skins) {
      new Stylesheet(skin).applyToParent(view.getNode());
    }
    return (T) view;
  }
}