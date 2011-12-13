package net.sf.anathema.demo.campaign.music;

import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.model.track.Md5Checksum;
import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;

public class DemoMp3Track implements IMp3Track {

  private final String album;
  private final String artist;
  private final String track;
  private final String title;
  private String givenNamel;
  private IMusicEvent[] moods;
  private IMusicTheme[] themes;
  private IMusicMood[] feelings;

  public DemoMp3Track(String album, String artist, String track, String title) {
    this.album = album;
    this.artist = artist;
    this.track = track;
    this.title = title;
  }

  public String getAlbum() {
    return album;
  }

  public String getArtist() {
    return artist;
  }

  public String getTrack() {
    return track;
  }

  public String getTitle() {
    return title;
  }

  public Md5Checksum getCheckSum() {
    return null;
  }

  public String[] getFileReferences() {
    return new String[0];
  }

  public String getGivenName() {
    return givenNamel;
  }

  public void setGivenName(String givenName) {
    this.givenNamel = givenName;
  }

  public IMusicEvent[] getEvents() {
    return moods;
  }

  public void setEvents(IMusicEvent[] moods) {
    this.moods = moods;
  }

  public IMusicTheme[] getThemes() {
    return themes;
  }

  public void setThemes(IMusicTheme[] themes) {
    this.themes = themes;
  }

  public IMusicMood[] getMoods() {
    return feelings;
  }

  public void setMoods(IMusicMood[] feelings) {
    this.feelings = feelings;
  }
}