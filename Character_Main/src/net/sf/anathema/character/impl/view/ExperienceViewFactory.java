package net.sf.anathema.character.impl.view;

import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.platform.RegisteredCharacterView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;

@RegisteredCharacterView(IExperienceConfigurationView.class)
public class ExperienceViewFactory implements SubViewFactory{
  @Override
  public <T> T create(ICharacterType type) {
    return (T) new ExperienceConfigurationView();
  }
}