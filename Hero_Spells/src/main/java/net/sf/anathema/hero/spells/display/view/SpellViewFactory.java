package net.sf.anathema.hero.spells.display.view;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.hero.spells.display.presenter.SpellView;

@RegisteredCharacterView(SpellView.class)
public class SpellViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new SwingSpellView();
  }
}
