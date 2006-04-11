package net.sf.anathema.campaign.music.presenter;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.campaign.music.model.selection.ITrackDetailModel;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.view.selection.ITrackDetailsView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.container.SelectionContainerPresenter;

public class TrackDetailsPresenter {

  private final IResources resources;
  private final ITrackDetailModel trackDetailModel;
  private final ITrackDetailsView trackDetailsView;

  public TrackDetailsPresenter(
      IResources resources,
      ITrackDetailsView trackDetailsView,
      ITrackDetailModel trackDetailModel) {
    this.resources = resources;
    this.trackDetailModel = trackDetailModel;
    this.trackDetailsView = trackDetailsView;
  }

  public void initPresentation() {
    trackDetailModel.addTrackChangeListener(new IChangeListener() {
      public void changeOccured() {
        updateTrackInfo(trackDetailModel.getSelectedTrack());
      }
    });
    trackDetailsView.getGivenNameView().addTextChangedListener(new IObjectValueChangedListener<String>() {
      public void valueChanged(String newValue) {
        trackDetailModel.updateGivenName(newValue);
      }
    });
    new SelectionContainerPresenter<IMusicTheme>(
        trackDetailModel.getThemesModel(),
        trackDetailsView.getMusicCategorizationView().getThemesView(),
        IMusicTheme.class).initPresentation();
    new SelectionContainerPresenter<IMusicMood>(
        trackDetailModel.getMoodsModel(),
        trackDetailsView.getMusicCategorizationView().getMoodsView(),
        IMusicMood.class).initPresentation();
    new SelectionContainerPresenter<IMusicEvent>(
        trackDetailModel.getEventsModel(),
        trackDetailsView.getMusicCategorizationView().getEventsView(),
        IMusicEvent.class).initPresentation();
  }

  private void updateTrackInfo(IMp3Track mp3Track) {
    if (mp3Track == null) {
      trackDetailsView.showTrackInfo(false);
      return;
    }
    // TODO NOW: vom (13.08.2005) (sieroux): Duplikation entfernen
    trackDetailsView.showTrackInfo(true);
    String title = mp3Track.getTitle();
    String unknownString = resources.getString("Music.TrackDetails.Unknown"); //$NON-NLS-1$
    boolean titleEmpty = false;
    if (StringUtilities.isNullOrEmpty(title)) {
      titleEmpty = true;
      trackDetailsView.setOriginalTitle(unknownString);
    }
    else {
      trackDetailsView.setOriginalTitle(title);
    }
    String album = mp3Track.getAlbum();
    if (StringUtilities.isNullOrEmpty(album)) {
      trackDetailsView.setAlbumTitle(unknownString);
    }
    else {
      trackDetailsView.setAlbumTitle(album);
    }
    String track = mp3Track.getTrack();
    if (StringUtilities.isNullOrEmpty(track)) {
      trackDetailsView.setTrackNumber(unknownString);
    }
    else {
      trackDetailsView.setTrackNumber(track);
    }
    String artist = mp3Track.getArtist();
    if (StringUtilities.isNullOrEmpty(artist)) {
      trackDetailsView.setArtistName(unknownString);
    }
    else {
      trackDetailsView.setArtistName(artist);
    }
    String givenName = mp3Track.getGivenName();
    if (StringUtilities.isNullOrEmpty(givenName)) {
      givenName = titleEmpty ? unknownString : title;
    }
    trackDetailsView.getGivenNameView().setText(givenName);
  }

}