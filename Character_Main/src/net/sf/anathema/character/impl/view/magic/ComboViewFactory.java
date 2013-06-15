package net.sf.anathema.character.impl.view.magic;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.view.SubViewFactory;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.character.view.magic.IComboConfigurationView;

@RegisteredCharacterView(IComboConfigurationView.class)
public class ComboViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new ComboConfigurationView();
  }
}