package net.sf.anathema.character.presenter;

import net.sf.anathema.character.main.hero.CharacterModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.initializers.CharacterModelInitializer;
import net.sf.anathema.character.presenter.initializers.RegisteredInitializer;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.model.ApplicationModel;
import net.sf.anathema.lib.resources.Resources;

@RegisteredInitializer(CharacterModelGroup.Miscellaneous)
public class PlayerCharacterPointPresentation implements CharacterModelInitializer {

  @SuppressWarnings("UnusedParameters")
  public PlayerCharacterPointPresentation(ApplicationModel model) {
    //nothing to do
  }

  @Override
  public void initialize(SectionView sectionView, ICharacter character, Resources resources) {
    new ExperiencePointPresenter(resources, character).initPresentation(sectionView);
  }
}