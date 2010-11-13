package net.sf.anathema.campaign.music.impl.model.tracks;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.spi.mpeg.sampled.file.MpegAudioFileFormat;
import javazoom.spi.mpeg.sampled.file.MpegAudioFileReader;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.model.track.Md5Checksum;
import net.sf.anathema.campaign.music.presenter.IMusicEvent;
import net.sf.anathema.campaign.music.presenter.IMusicMood;
import net.sf.anathema.campaign.music.presenter.IMusicTheme;
import net.sf.anathema.lib.exception.AnathemaException;

public class FileMp3Track implements IMp3Track {

  private final MpegAudioFileFormat audioFileFormat;
  private final Md5Checksum checksum;
  private final String[] fileReferences;
  private String givenName;
  private IMusicEvent[] moods;
  private IMusicTheme[] themes;
  private IMusicMood[] feelings;

  public FileMp3Track(File file) throws IOException, AnathemaException {
    try {
      MpegAudioFileReader audioFileReader = new MpegAudioFileReader();
      audioFileFormat = (MpegAudioFileFormat) audioFileReader.getAudioFileFormat(file);
      checksum = new Mp3ChecksumCalculator().calculate(file);
      fileReferences = new String[] { file.getCanonicalPath() };
    }
    catch (UnsupportedAudioFileException e) {
      throw new AnathemaException(e);
    }
    catch (NoSuchAlgorithmException e) {
      throw new AnathemaException(e);
    }
  }

  public String getAlbum() {
    return getBriefDescription("album"); //$NON-NLS-1$
  }

  private String getBriefDescription(String key) {
    return (String) audioFileFormat.properties().get(key);
  }

  public String getArtist() {
    return getBriefDescription("author"); //$NON-NLS-1$
  }

  public String getTrack() {
    return getBriefDescription("mp3.id3tag.track"); //$NON-NLS-1$
  }

  public String getTitle() {
    return getBriefDescription("title"); //$NON-NLS-1$
  }

  public Md5Checksum getCheckSum() {
    return checksum;
  }

  public String[] getFileReferences() {
    return fileReferences;
  }

  public String getGivenName() {
    return givenName == null ? getArtist() + " - " + getTitle() : givenName; //$NON-NLS-1$
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public IMusicEvent[] getEvents() {
    return moods;
  }

  public void setEvents(IMusicEvent[] musicMoods) {
    this.moods = musicMoods;
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