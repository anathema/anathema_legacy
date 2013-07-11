package net.sf.anathema.hero.spells.display;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;

@RegisteredCharacterView(SpellView.class)
public class SpellViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new SwingSpellView();
  }
}
