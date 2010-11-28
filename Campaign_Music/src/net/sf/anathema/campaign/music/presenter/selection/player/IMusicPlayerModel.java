package net.sf.anathema.campaign.music.presenter.selection.player;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.exception.AnathemaException;

public interface IMusicPlayerModel {

  public void startPlayback() throws AnathemaException;

  public void stopPlayback() throws AnathemaException;

  public void seek(int percent) throws AnathemaException;

  public void addMusicModelListener(IMusicPlayerModelListener listener);

  public void pausePlayback() throws AnathemaException;

  public void resumePlayback() throws AnathemaException;

  public void setTrack(IMp3Track mp3Track) throws AnathemaException;
}