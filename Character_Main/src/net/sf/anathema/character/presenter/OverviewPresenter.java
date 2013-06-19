package net.sf.anathema.character.presenter;

import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.character.main.model.experience.ExperienceChange;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.model.advance.IExperiencePointManagement;
import net.sf.anathema.character.model.creation.IBonusPointManagement;
import net.sf.anathema.character.presenter.overview.CreationOverviewPresenter;
import net.sf.anathema.character.presenter.overview.ExperiencedOverviewPresenter;
import net.sf.anathema.character.view.OverviewContainer;
import net.sf.anathema.character.view.overview.CategorizedOverview;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

public class OverviewPresenter implements Presenter {

  private Resources resources;
  private ICharacter character;
  private OverviewContainer container;
  private IBonusPointManagement bonusPoints;
  private IExperiencePointManagement experiencePoints;

  public OverviewPresenter(Resources resources, ICharacter character, OverviewContainer container, IBonusPointManagement bonusPoints,
                           IExperiencePointManagement experiencePoints) {
    this.resources = resources;
    this.character = character;
    this.container = container;
    this.bonusPoints = bonusPoints;
    this.experiencePoints = experiencePoints;
  }

  @Override
  public void initPresentation() {
    CategorizedOverview creationPointView = container.addCreationOverviewView();
    new CreationOverviewPresenter(resources, character, creationPointView, bonusPoints).initPresentation();
    CategorizedOverview experiencePointView = container.addExperienceOverviewView();
    new ExperiencedOverviewPresenter(resources, character, experiencePointView, experiencePoints).initPresentation();
    setOverviewView(ExperienceModelFetcher.fetch(character).isExperienced());
    character.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
          setOverviewView(ExperienceModelFetcher.fetch(character).isExperienced());
        }
      }
    });
   }

  private void setOverviewView(boolean experienced) {
    container.toggleOverviewView(experienced);
  }
}
