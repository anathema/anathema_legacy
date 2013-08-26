package net.sf.anathema.hero.experience.display;

import net.sf.anathema.character.main.framework.RegisteredCharacterView;
import net.sf.anathema.character.main.view.SubViewFactory;
import net.sf.anathema.platform.fx.Stylesheet;

@RegisteredCharacterView(ExperienceView.class)
public class ExperienceViewFactory implements SubViewFactory {
  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    FxExperienceView fxView = new FxExperienceView();
    new Stylesheet("skin/experience/experience.css").applyToParent(fxView.getNode());
    return (T) fxView;
  }
}