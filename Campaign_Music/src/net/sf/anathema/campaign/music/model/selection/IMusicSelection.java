package net.sf.anathema.campaign.music.model.selection;

import net.sf.anathema.campaign.music.model.track.IMp3Track;

public interface IMusicSelection {

  IMp3Track[] getContent();

  void clear();

  boolean removeTracks(IMp3Track[] tracks);

  boolean addTracks(IMp3Track[] tracks);

  void removeTracks(int[] indices);
}