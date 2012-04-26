package net.sf.anathema.campaign.music.impl.model.selection;

import net.sf.anathema.campaign.music.model.selection.ITrackDetailModel;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;
import net.sf.anathema.lib.control.change.IChangeListener;
import org.jmock.example.announcer.Announcer;

public abstract class AbstractTrackDetailModel implements ITrackDetailModel {

  private final Announcer<IChangeListener> givenNameChangeControl = Announcer.to(IChangeListener.class);
  private final Announcer<IChangeListener> trackChangeControl = Announcer.to(IChangeListener.class);
  private IMp3Track selectedTrack;

  protected final void fireGivenNamesChangedEvent() {
    givenNameChangeControl.announce().changeOccurred();
  }

  @Override
  public final void addChangeDetailListener(IChangeListener changeListener) {
    givenNameChangeControl.addListener(changeListener);
  }

  @Override
  public final void addTrackChangeListener(IChangeListener tracklistener) {
    trackChangeControl.addListener(tracklistener);
  }

  public final void setSelectedTrack(IMp3Track selectedTrack) {
    this.selectedTrack = selectedTrack;
    if (selectedTrack != null) {
      getMoodsModel().setSelectedValues(selectedTrack.getMoods());
      getEventsModel().setSelectedValues(selectedTrack.getEvents());
      getThemesModel().setSelectedValues(selectedTrack.getThemes());
    }
    else {
      getMoodsModel().setSelectedValues(new IMusicMood[0]);
      getEventsModel().setSelectedValues(new IMusicEvent[0]);
      getThemesModel().setSelectedValues(new IMusicTheme[0]);
    }
    trackChangeControl.announce().changeOccurred();
  }

  @Override
  public final IMp3Track getSelectedTrack() {
    return selectedTrack;
  }
}