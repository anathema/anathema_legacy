package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.presenter.advance.ExperienceConfigurationPresenter;
import net.sf.anathema.character.presenter.charm.IContentPresenter;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.character.view.advance.IExperienceConfigurationView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class ExperiencePointPresenter implements IPresenter {

  private IResources resources;
  private ICharacterStatistics statistics;
  private ICharacterView view;
  private MultiTabViewPresenter tabPresenter;

  public ExperiencePointPresenter(IResources resources, ICharacterStatistics statistics, ICharacterView view,
                                  MultiTabViewPresenter tabPresenter) {
    this.resources = resources;
    this.statistics = statistics;
    this.view = view;
    this.tabPresenter = tabPresenter;
  }

  @Override
  public void initPresentation() {
    initExperiencePointPresentation(statistics.isExperienced());
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        initExperiencePointPresentation(experienced);
      }
    });
  }

  private void initExperiencePointPresentation(boolean experienced) {
    if (experienced) {
      IExperienceConfigurationView experienceView = view.createExperienceConfigurationView();
      IContentPresenter presenter = new ExperienceConfigurationPresenter(resources, statistics.getExperiencePoints(),
              experienceView);
      String title = resources.getString("CardView.ExperienceConfiguration.Title");
      tabPresenter.initMultiTabViewPresentation(title, presenter, AdditionalModelType.Experience);
    }
  }
}
