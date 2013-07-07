package net.sf.anathema.character.main.presenter;

import net.sf.anathema.hero.experience.ExperienceChange;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.character.main.advance.IExperiencePointManagement;
import net.sf.anathema.character.main.creation.IBonusPointManagement;
import net.sf.anathema.character.main.presenter.overview.CreationOverviewPresenter;
import net.sf.anathema.character.main.presenter.overview.ExperiencedOverviewPresenter;
import net.sf.anathema.character.main.view.OverviewContainer;
import net.sf.anathema.character.main.view.overview.CategorizedOverview;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.hero.change.FlavoredChangeListener;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.resources.Resources;

public class OverviewPresenter implements Presenter {

  private Resources resources;
  private Hero hero;
  private OverviewContainer container;
  private IBonusPointManagement bonusPoints;
  private IExperiencePointManagement experiencePoints;

  public OverviewPresenter(Resources resources, Hero hero, OverviewContainer container, IBonusPointManagement bonusPoints,
                           IExperiencePointManagement experiencePoints) {
    this.resources = resources;
    this.hero = hero;
    this.container = container;
    this.bonusPoints = bonusPoints;
    this.experiencePoints = experiencePoints;
  }

  @Override
  public void initPresentation() {
    CategorizedOverview creationPointView = container.addCreationOverviewView();
    new CreationOverviewPresenter(resources, hero, creationPointView, bonusPoints).initPresentation();
    CategorizedOverview experiencePointView = container.addExperienceOverviewView();
    new ExperiencedOverviewPresenter(resources, hero, experiencePointView, experiencePoints).initPresentation();
    setOverviewView(ExperienceModelFetcher.fetch(hero).isExperienced());
    hero.getChangeAnnouncer().addListener(new FlavoredChangeListener() {
      @Override
      public void changeOccurred(ChangeFlavor flavor) {
        if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
          setOverviewView(ExperienceModelFetcher.fetch(hero).isExperienced());
        }
      }
    });
   }

  private void setOverviewView(boolean experienced) {
    container.toggleOverviewView(experienced);
  }
}
