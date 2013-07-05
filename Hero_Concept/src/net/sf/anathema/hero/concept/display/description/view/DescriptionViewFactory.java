package net.sf.anathema.hero.concept.display.description.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.character.view.ICharacterDescriptionView;
import net.sf.anathema.character.view.SubViewFactory;

@RegisteredCharacterView(ICharacterDescriptionView.class)
public class DescriptionViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new CharacterDescriptionView();
  }
}
