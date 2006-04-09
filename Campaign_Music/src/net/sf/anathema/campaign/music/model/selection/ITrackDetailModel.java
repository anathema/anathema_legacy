package net.sf.anathema.campaign.music.model.selection;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.model.util.IMusicCategorizationModel;
import net.sf.anathema.lib.control.IChangeListener;

public interface ITrackDetailModel extends IMusicCategorizationModel {

  public void updateGivenName(String givenName);
  
  public void addChangeDetailListener(IChangeListener changeListener);

  public void addTrackChangeListener(IChangeListener tracklistener);

  public IMp3Track getSelectedTrack();
}