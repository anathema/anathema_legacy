package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.presenter.advance.ExperienceConfigurationPresenter;
import net.sf.anathema.character.presenter.magic.IContentPresenter;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.lib.resources.IResources;

public class ExperiencePointPresenter {

  private IResources resources;
  private ICharacterStatistics statistics;
  private ICharacterView view;

  public ExperiencePointPresenter(IResources resources, ICharacterStatistics statistics, ICharacterView view) {
    this.resources = resources;
    this.statistics = statistics;
    this.view = view;
  }

  public void initPresentation(final MultipleContentViewPresenter tabPresenter){
    initExperiencePointPresentation(statistics.isExperienced(), tabPresenter);
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        initExperiencePointPresentation(experienced, tabPresenter);
      }
    });
  }

  private void initExperiencePointPresentation(boolean experienced, MultipleContentViewPresenter tabPresenter) {
    if (experienced) {
      IExperienceConfigurationView experienceView = view.createExperienceConfigurationView();
      IContentPresenter presenter = new ExperienceConfigurationPresenter(resources, statistics.getExperiencePoints(),
              experienceView);
      String title = resources.getString("CardView.ExperienceConfiguration.Title");
      tabPresenter.initMultipleContentPresentation(title, AdditionalModelType.Experience, presenter);
    }
  }
}
