package net.sf.anathema.character.presenter;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.view.ICharacterView;
import net.sf.anathema.lib.resources.Resources;

public class PlayerCharacterPointPresentation implements PointPresentationStrategy {
  private OverviewPresenter overviewPresenter;
  private ExperiencePointPresenter experiencePointPresenter;

  public PlayerCharacterPointPresentation(Resources resources, ICharacter character, ICharacterView view, IBonusPointManagement bonusPoints,
                                          IExperiencePointManagement experiencePoints) {
    this.overviewPresenter = new OverviewPresenter(resources, character, view, bonusPoints, experiencePoints);
    this.experiencePointPresenter = new ExperiencePointPresenter(resources, character, view);
  }

  @Override
  public void initPresentation(MultipleContentViewPresenter viewPresenter) {
    overviewPresenter.initPresentation();
    experiencePointPresenter.initPresentation(viewPresenter);
  }
}