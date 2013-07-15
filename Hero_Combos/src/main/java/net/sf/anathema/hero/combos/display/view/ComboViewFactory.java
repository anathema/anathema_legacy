package net.sf.anathema.hero.combos.display.view;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.hero.combos.display.presenter.ComboConfigurationView;

@RegisteredCharacterView(ComboConfigurationView.class)
public class ComboViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(CharacterType type) {
    FxComboConfigurationView fxView = new FxComboConfigurationView();
    return (T) new BridgingComboConfigurationView(fxView);
  }
}