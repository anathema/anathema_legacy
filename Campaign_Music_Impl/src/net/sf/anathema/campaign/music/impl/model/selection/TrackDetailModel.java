package net.sf.anathema.campaign.music.impl.model.selection;

import net.sf.anathema.campaign.music.impl.persistence.MusicDatabasePersister;
import net.sf.anathema.campaign.music.impl.persistence.categorization.EventProvider;
import net.sf.anathema.campaign.music.impl.persistence.categorization.MoodProvider;
import net.sf.anathema.campaign.music.impl.persistence.categorization.ThemeProvider;
import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;
import net.sf.anathema.lib.container.IGenericSelectionContainer;
import net.sf.anathema.lib.workflow.container.ISelectionContainerModel;
import net.sf.anathema.lib.workflow.container.model.SelectionContainerModel;

public class TrackDetailModel extends AbstractTrackDetailModel {

  private final MusicDatabasePersister persister;
  private final SelectionContainerModel<IMusicMood> moodsModel = new SelectionContainerModel<IMusicMood>(
      new IGenericSelectionContainer<IMusicMood>() {
        private IMusicMood[] selectedValues = new IMusicMood[0];

        public void setValues(IMusicMood[] values) {
          if (getSelectedTrack() == null) {
            return;
          }
          this.selectedValues = values;
          getSelectedTrack().setMoods(values);
          updateDbTrack();
        }

        public IMusicMood[] getValues() {
          return selectedValues;
        }

        public IMusicMood[] getAllAvailableValues() {
          return new MoodProvider(persister).getAvailableValues();
        }
      });
  private final SelectionContainerModel<IMusicTheme> themesModel = new SelectionContainerModel<IMusicTheme>(
      new IGenericSelectionContainer<IMusicTheme>() {
        private IMusicTheme[] selectedValues = new IMusicTheme[0];

        public void setValues(IMusicTheme[] values) {
          if (getSelectedTrack() == null) {
            return;
          }
          this.selectedValues = values;
          getSelectedTrack().setThemes(values);
          updateDbTrack();
        }

        public IMusicTheme[] getValues() {
          return selectedValues;
        }

        public IMusicTheme[] getAllAvailableValues() {
          return new ThemeProvider(persister).getAvailableValues();
        }
      });

  private final SelectionContainerModel<IMusicEvent> eventsModel = new SelectionContainerModel<IMusicEvent>(
      new IGenericSelectionContainer<IMusicEvent>() {
        private IMusicEvent[] selectedValues = new IMusicEvent[0];

        public void setValues(IMusicEvent[] values) {
          if (getSelectedTrack() == null) {
            return;
          }
          this.selectedValues = values;
          getSelectedTrack().setEvents(values);
          updateDbTrack();
        }

        public IMusicEvent[] getValues() {
          return selectedValues;
        }

        public IMusicEvent[] getAllAvailableValues() {
          return new EventProvider(persister).getAvailableValues();
        }
      });

  public TrackDetailModel(MusicDatabasePersister persister) {
    this.persister = persister;
  }

  public void updateGivenName(String givenName) {
    if (getSelectedTrack().getGivenName() != null && getSelectedTrack().getGivenName().equals(givenName)) {
      return;
    }
    getSelectedTrack().setGivenName(givenName);
    updateDbTrack();
    fireGivenNamesChangedEvent();
  }

  public ISelectionContainerModel<IMusicMood> getMoodsModel() {
    return moodsModel;
  }

  private void updateDbTrack() {
    persister.updateTrackInfo(getSelectedTrack());
  }

  public ISelectionContainerModel<IMusicTheme> getThemesModel() {
    return themesModel;
  }

  public ISelectionContainerModel<IMusicEvent> getEventsModel() {
    return eventsModel;
  }
}