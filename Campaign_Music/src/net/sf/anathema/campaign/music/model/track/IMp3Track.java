package net.sf.anathema.campaign.music.model.track;

import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;

public interface IMp3Track {

  public String getAlbum();

  public String getArtist();

  public String getTrack();

  public String getTitle();

  public Md5Checksum getCheckSum();

  public String[] getFileReferences();

  public String getGivenName();

  public void setGivenName(String givenName);

  public IMusicEvent[] getEvents();

  public void setEvents(IMusicEvent[] musics);

  public IMusicTheme[] getThemes();

  public void setThemes(IMusicTheme[] themes);

  public IMusicMood[] getMoods();

  public void setMoods(IMusicMood[] feelings);
}