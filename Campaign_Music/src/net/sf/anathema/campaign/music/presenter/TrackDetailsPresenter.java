package net.sf.anathema.campaign.music.presenter;

import net.disy.commons.core.util.IClosure;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.campaign.music.model.selection.ITrackDetailModel;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.view.selection.ITrackDetailsView;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.container.SelectionContainerPresenter;

public class TrackDetailsPresenter implements IPresenter {

  private final ITrackDetailModel trackDetailModel;
  private final ITrackDetailsView trackDetailsView;
  private final String unknownString;

  public TrackDetailsPresenter(
      IResources resources,
      ITrackDetailsView trackDetailsView,
      ITrackDetailModel trackDetailModel) {
    this.trackDetailModel = trackDetailModel;
    this.trackDetailsView = trackDetailsView;
    this.unknownString = resources.getString("Music.TrackDetails.Unknown"); //$NON-NLS-1$
  }

  public void initPresentation() {
    trackDetailModel.addTrackChangeListener(new IChangeListener() {
      public void changeOccurred() {
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
    trackDetailsView.showTrackInfo(true);
    setStringValue(mp3Track.getTitle(), new IClosure<String>() {
      public void execute(String input) {
        trackDetailsView.setOriginalTitle(input);
      }
    });
    setStringValue(mp3Track.getAlbum(), new IClosure<String>() {
      public void execute(String input) {
        trackDetailsView.setAlbumTitle(input);
      }
    });
    setStringValue(mp3Track.getTrack(), new IClosure<String>() {
      public void execute(String input) {
        trackDetailsView.setTrackNumber(input);
      }
    });
    setStringValue(mp3Track.getArtist(), new IClosure<String>() {
      public void execute(String input) {
        trackDetailsView.setArtistName(input);
      }
    });
    String givenName = mp3Track.getGivenName();
    if (StringUtilities.isNullOrEmpty(givenName)) {
      givenName = mp3Track.getTitle();
    }
    setStringValue(givenName, new IClosure<String>() {
      public void execute(String input) {
        trackDetailsView.getGivenNameView().setText(input);
      }
    });
  }

  private void setStringValue(String value, IClosure<String> setter) {
    String setValue = StringUtilities.isNullOrEmpty(value) ? unknownString : value;
    setter.execute(setValue);
  }
}