package net.sf.anathema.campaign.music.model.selection;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.model.util.IMusicCategorizationModel;
import net.sf.anathema.lib.control.IChangeListener;

public interface ITrackDetailModel extends IMusicCategorizationModel {

  void updateGivenName(String givenName);

  void addChangeDetailListener(IChangeListener changeListener);

  void addTrackChangeListener(IChangeListener tracklistener);

  IMp3Track getSelectedTrack();
}