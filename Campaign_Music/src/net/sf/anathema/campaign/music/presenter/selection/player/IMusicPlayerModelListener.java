package net.sf.anathema.campaign.music.presenter.selection.player;

import net.sf.anathema.campaign.music.model.track.IMp3Track;

public interface IMusicPlayerModelListener {

  public void trackOpenend(IMp3Track track, int lengthInBytes, long totalTime);

  public void positionChanged(int bytesread, long timeElapsed);

  public void statusChanged(MusicPlayerStatus status);
}