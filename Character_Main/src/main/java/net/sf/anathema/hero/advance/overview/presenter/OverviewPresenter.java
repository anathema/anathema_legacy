package net.sf.anathema.hero.advance.overview.presenter;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.hero.advance.creation.IBonusPointManagement;
import net.sf.anathema.hero.advance.experience.ExperiencePointManagement;
import net.sf.anathema.hero.advance.overview.view.CategorizedOverview;
import net.sf.anathema.hero.advance.overview.view.OverviewContainer;
import net.sf.anathema.hero.experience.ExperienceChange;
import net.sf.anathema.hero.experience.ExperienceModelFetcher;
import net.sf.anathema.hero.model.Hero;

public class OverviewPresenter {

  private Resources resources;
  private Hero hero;
  private OverviewContainer container;
  private IBonusPointManagement bonusPoints;
  private ExperiencePointManagement experiencePoints;
  private IMessaging messaging;

  public OverviewPresenter(Resources resources, Hero hero, OverviewContainer container, IBonusPointManagement bonusPoints,
                           ExperiencePointManagement experiencePoints, IMessaging messaging) {
    this.resources = resources;
    this.hero = hero;
    this.container = container;
    this.bonusPoints = bonusPoints;
    this.experiencePoints = experiencePoints;
    this.messaging = messaging;
  }

  public void initPresentation() {
    CategorizedOverview creationPointView = container.addCreationOverviewView();
    new CreationOverviewPresenter(resources, hero, creationPointView, bonusPoints, messaging).initPresentation();
    CategorizedOverview experiencePointView = container.addExperienceOverviewView();
    new ExperiencedOverviewPresenter(resources, hero, experiencePointView, experiencePoints).initPresentation();
    setOverviewView(ExperienceModelFetcher.fetch(hero).isExperienced());
    hero.getChangeAnnouncer().addListener(flavor -> {
      if (flavor == ExperienceChange.FLAVOR_EXPERIENCE_STATE) {
        setOverviewView(ExperienceModelFetcher.fetch(hero).isExperienced());
      }
    });
   }

  private void setOverviewView(boolean experienced) {
    container.toggleOverviewView(experienced);
  }
}
