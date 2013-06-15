package net.sf.anathema.character.presenter;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.view.CharacterView;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

public class PlayerCharacterPointPresentation implements PointPresentationStrategy {
  private OverviewPresenter overviewPresenter;
  private ExperiencePointPresenter experiencePointPresenter;

  public PlayerCharacterPointPresentation(Resources resources, ICharacter character, CharacterView view, IBonusPointManagement bonusPoints,
                                          IExperiencePointManagement experiencePoints) {
    this.overviewPresenter = new OverviewPresenter(resources, character, view, bonusPoints, experiencePoints);
    this.experiencePointPresenter = new ExperiencePointPresenter(resources, character);
  }

  @Override
  public void initPresentation(SectionView section) {
    overviewPresenter.initPresentation();
    experiencePointPresenter.initPresentation(section);
  }
}