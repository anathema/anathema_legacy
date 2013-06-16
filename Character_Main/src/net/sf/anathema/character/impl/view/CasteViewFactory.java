package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.impl.view.concept.SimpleCasteView;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.character.view.concept.CasteView;

@RegisteredCharacterView(CasteView.class)
public class CasteViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new SimpleCasteView();
  }
}