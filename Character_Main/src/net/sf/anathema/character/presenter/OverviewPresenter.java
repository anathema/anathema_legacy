package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.presenter.overview.CreationOverviewPresenter;
import net.sf.anathema.character.presenter.overview.ExperiencedOverviewPresenter;
import net.sf.anathema.character.view.OverviewContainer;
import net.sf.anathema.character.view.overview.IOverviewView;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class OverviewPresenter implements IPresenter {

  private IResources resources;
  private ICharacterStatistics statistics;
  private OverviewContainer container;
  private IBonusPointManagement bonusPointManagement;
  private IExperiencePointManagement experiencePointManagement;

  public OverviewPresenter(IResources resources, ICharacterStatistics statistics, OverviewContainer container,
                           IBonusPointManagement bonusPointManagement,
                           IExperiencePointManagement experiencePointManagement) {
    this.resources = resources;
    this.statistics = statistics;
    this.container = container;
    this.bonusPointManagement = bonusPointManagement;
    this.experiencePointManagement = experiencePointManagement;
  }

  @Override
  public void initPresentation() {
    IOverviewView creationPointView = container.addCreationOverviewView();
    new CreationOverviewPresenter(resources, statistics, creationPointView, bonusPointManagement).initPresentation();
    IOverviewView experiencePointView = container.addExperienceOverviewView();
    new ExperiencedOverviewPresenter(resources, statistics, experiencePointView,
            experiencePointManagement).initPresentation();
    setOverviewView(statistics.isExperienced());
    statistics.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        setOverviewView(experienced);
      }
    });
  }

  private void setOverviewView(boolean experienced) {
    container.toogleOverviewView(experienced);
  }
}