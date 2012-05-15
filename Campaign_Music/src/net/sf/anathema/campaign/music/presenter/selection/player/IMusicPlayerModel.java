package net.sf.anathema.campaign.music.presenter.selection.player;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.exception.AnathemaException;

public interface IMusicPlayerModel {

  void startPlayback() throws AnathemaException;

  void stopPlayback() throws AnathemaException;

  void seek(int percent) throws AnathemaException;

  void addMusicModelListener(IMusicPlayerModelListener listener);

  void pausePlayback() throws AnathemaException;

  void resumePlayback() throws AnathemaException;

  void setTrack(IMp3Track mp3Track) throws AnathemaException;
}