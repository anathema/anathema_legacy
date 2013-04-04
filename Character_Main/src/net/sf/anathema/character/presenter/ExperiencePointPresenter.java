package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.presenter.advance.ExperienceConfigurationPresenter;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.lib.resources.Resources;

public class ExperiencePointPresenter {

  private Resources resources;
  private ICharacter character;
  private ICharacterView view;

  public ExperiencePointPresenter(Resources resources, ICharacter character, ICharacterView view) {
    this.resources = resources;
    this.character = character;
    this.view = view;
  }

  public void initPresentation(final MultipleContentViewPresenter tabPresenter) {
    initExperiencePointPresentation(character.isExperienced(), tabPresenter);
    character.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        initExperiencePointPresentation(experienced, tabPresenter);
      }
    });
  }

  private void initExperiencePointPresentation(boolean experienced, MultipleContentViewPresenter tabPresenter) {
    if (experienced) {
      IExperienceConfigurationView experienceView = view.createExperienceConfigurationView();
      IContentPresenter presenter = new ExperienceConfigurationPresenter(resources, character.getExperiencePoints(), experienceView);
      String title = resources.getString("CardView.ExperienceConfiguration.Title");
      presenter.initPresentation();
      tabPresenter.addMiscellaneousView(title, presenter.getTabContent());
    }
  }
}