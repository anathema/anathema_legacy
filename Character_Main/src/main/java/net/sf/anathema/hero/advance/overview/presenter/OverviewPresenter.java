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
    CreationOverviewPresenter creationOverviewPresenter = new CreationOverviewPresenter(resources, hero, creationPointView, bonusPoints, messaging);
    creationOverviewPresenter.initPresentation();
    CategorizedOverview experiencePointView = container.addExperienceOverviewView();
    ExperiencedOverviewPresenter experiencedOverviewPresenter = new ExperiencedOverviewPresenter(resources, hero, experiencePointView, experiencePoints, messaging);
    experiencedOverviewPresenter.initPresentation();
    boolean experienced = ExperienceModelFetcher.fetch(hero).isExperienced();
    setOverviewView(experienced);
    if (experienced) {
      experiencedOverviewPresenter.refresh();
    }
    else {
      creationOverviewPresenter.refresh();
    }
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
