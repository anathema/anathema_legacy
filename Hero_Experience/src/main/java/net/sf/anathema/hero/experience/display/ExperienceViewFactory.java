package net.sf.anathema.hero.experience.display;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.character.main.view.SubViewFactory;

@RegisteredCharacterView(ExperienceView.class)
public class ExperienceViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create(CharacterType type) {
    FxExperienceView fxView = new FxExperienceView();
    return (T) new BridgingExperienceView(fxView);
  }
}