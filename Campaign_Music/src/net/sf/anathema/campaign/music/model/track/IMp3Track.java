package net.sf.anathema.campaign.music.model.track;

import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;

public interface IMp3Track {

  String getAlbum();

  String getArtist();

  String getTrack();

  String getTitle();

  Md5Checksum getCheckSum();

  String[] getFileReferences();

  String getGivenName();

  void setGivenName(String givenName);

  IMusicEvent[] getEvents();

  void setEvents(IMusicEvent[] musics);

  IMusicTheme[] getThemes();

  void setThemes(IMusicTheme[] themes);

  IMusicMood[] getMoods();

  void setMoods(IMusicMood[] feelings);
}