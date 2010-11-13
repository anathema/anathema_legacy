package net.sf.anathema.campaign.music.model.selection;

import net.sf.anathema.campaign.music.model.track.IMp3Track;

public interface IMusicSelection {

  public IMp3Track[] getContent();

  public void clear();

  public boolean removeTracks(IMp3Track[] tracks);

  public boolean addTracks(IMp3Track[] tracks);

  public void removeTracks(int[] indices);
}