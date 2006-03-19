package net.sf.anathema.campaign.music.model.selection;

import javax.swing.event.ChangeListener;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.model.util.IMusicCategorizationModel;

public interface ITrackDetailModel extends IMusicCategorizationModel {

  public void updateGivenName(String givenName);
  
  public void addChangeDetailListener(ChangeListener changeListener);

  public void addTrackChangeListener(ChangeListener tracklistener);

  public IMp3Track getSelectedTrack();
}