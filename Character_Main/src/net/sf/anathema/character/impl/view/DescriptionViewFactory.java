package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.character.view.ICharacterDescriptionView;

@RegisteredCharacterView(ICharacterDescriptionView.class)
public class DescriptionViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new CharacterDescriptionView();
  }
}
