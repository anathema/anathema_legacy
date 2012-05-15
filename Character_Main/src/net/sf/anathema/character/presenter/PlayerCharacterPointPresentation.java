package net.sf.anathema.character.presenter;

import net.sf.anathema.character.model.ICharacterStatistics;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.lib.resources.IResources;

public class PlayerCharacterPointPresentation implements PointPresentationStrategy {
  private OverviewPresenter overviewPresenter;
  private ExperiencePointPresenter experiencePointPresenter;

  public PlayerCharacterPointPresentation(IResources resources, ICharacterStatistics statistics, ICharacterView view,
                                          IBonusPointManagement bonusPoints,
                                          IExperiencePointManagement experiencePoints) {
    this.overviewPresenter = new OverviewPresenter(resources, statistics, view, bonusPoints, experiencePoints);
    this.experiencePointPresenter = new ExperiencePointPresenter(resources, statistics, view);
  }

  @Override
  public void initPresentation(MultipleContentViewPresenter viewPresenter) {
    overviewPresenter.initPresentation();
    experiencePointPresenter.initPresentation(viewPresenter);
  }
}