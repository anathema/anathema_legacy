package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.framework.additionaltemplate.listening.DedicatedCharacterChangeAdapter;
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
    setOverviewView(character.isExperienced());
    character.getCharacterContext().getCharacterListening().addChangeListener(new DedicatedCharacterChangeAdapter() {
      @Override
      public void experiencedChanged(boolean experienced) {
        setOverviewView(experienced);
      }
    });
  }

  private void setOverviewView(boolean experienced) {
    container.toggleOverviewView(experienced);
  }
}
