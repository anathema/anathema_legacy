package net.sf.anathema.hero.combos.display.view;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.hero.combos.display.presenter.ComboConfigurationView;

@RegisteredCharacterView(ComboConfigurationView.class)
public class ComboViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new SwingComboConfigurationView();
  }
}