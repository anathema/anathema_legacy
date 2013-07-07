package net.sf.anathema.character.model.view.magic;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.model.view.SubViewFactory;

@RegisteredCharacterView(ISpellView.class)
public class SpellViewFactory implements SubViewFactory {
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new SpellView();
  }
}
