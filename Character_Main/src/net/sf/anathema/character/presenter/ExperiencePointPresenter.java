package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.advance.ExperienceConfigurationPresenter;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.view.CharacterView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.lib.resources.Resources;

public class ExperiencePointPresenter {

  private Resources resources;
  private ICharacter character;
  private CharacterView view;

  public ExperiencePointPresenter(Resources resources, ICharacter character, CharacterView view) {
    this.resources = resources;
    this.character = character;
    this.view = view;
  }

  public void initPresentation(final SectionView section) {
    initExperiencePointPresentation(character.isExperienced(), section);
    character.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        initExperiencePointPresentation(experienced, section);
      }
    });
  }

  private void initExperiencePointPresentation(boolean experienced, SectionView section) {
    if (experienced) {
      IExperienceConfigurationView experienceView = view.createExperienceConfigurationView();
      IContentPresenter presenter = new ExperienceConfigurationPresenter(resources, character.getExperiencePoints(), experienceView);
      presenter.initPresentation();
      section.addView(presenter.getTabContent());
    }
  }
}