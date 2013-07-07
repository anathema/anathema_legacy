package net.sf.anathema.hero.experience.display;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.view.SubViewFactory;

@RegisteredCharacterView(ExperienceView.class)
public class ExperienceViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new ExperienceTableView();
  }
}