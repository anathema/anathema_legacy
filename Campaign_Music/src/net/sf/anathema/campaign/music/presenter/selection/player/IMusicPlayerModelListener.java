package net.sf.anathema.campaign.music.presenter.selection.player;

import net.sf.anathema.campaign.music.model.track.IMp3Track;

public interface IMusicPlayerModelListener {

  void trackOpenend(IMp3Track track, int lengthInBytes, long totalTime);

  void positionChanged(int bytesread, long timeElapsed);

  void statusChanged(MusicPlayerStatus status);
}