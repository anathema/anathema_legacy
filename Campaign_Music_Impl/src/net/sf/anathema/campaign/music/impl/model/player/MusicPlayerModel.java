package net.sf.anathema.campaign.music.impl.model.player;

import java.io.File;
import java.util.Map;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

import net.sf.anathema.campaign.music.export.Mp3Utilities;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerModel;
import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerModelListener;
import net.sf.anathema.campaign.music.presenter.selection.player.MusicPlayerStatus;
import net.sf.anathema.lib.control.GenericControl;
import net.sf.anathema.lib.control.IClosure;
import net.sf.anathema.lib.exception.AnathemaException;

public class MusicPlayerModel implements IMusicPlayerModel {

  private static final String PROP_LENGTH = "audio.length.bytes"; //$NON-NLS-1$
  private final ExtendedBasicPlayer player = new ExtendedBasicPlayer();
  private final GenericControl<IMusicPlayerModelListener> control = new GenericControl<IMusicPlayerModelListener>();
  private long lengthInMilliseconds;
  private int lengthInBytes;
  private int currentBytePosition;
  private long playStartTime;
  private IMp3Track track;

  public MusicPlayerModel() throws ClassNotFoundException {
    Class.forName("javazoom.jl.decoder.Decoder"); //$NON-NLS-1$
    player.addBasicPlayerListener(new BasicPlayerListener() {
      public void setController(BasicController controller) {
        // Nothing to do
      }

      public void stateUpdated(BasicPlayerEvent event) {
        // Nothing to do
      }

      public void progress(final int bytesread, final long microseconds, byte[] pcmdata,
                           @SuppressWarnings("rawtypes") Map properties) {
        control.forAllDo(new IClosure<IMusicPlayerModelListener>() {
          public void execute(IMusicPlayerModelListener input) {
            currentBytePosition = bytesread;
            input.positionChanged(bytesread, (playStartTime + player.getElapsedTime()) / 1000);
          }
        });
      }

      public void opened(Object stream,
                         @SuppressWarnings("rawtypes") final Map properties) {
        lengthInMilliseconds = getTimeLengthEstimation(properties);
        lengthInBytes = (Integer) properties.get(PROP_LENGTH);
        control.forAllDo(new IClosure<IMusicPlayerModelListener>() {
          public void execute(IMusicPlayerModelListener input) {
            input.trackOpenend(track, lengthInBytes, lengthInMilliseconds / 1000);
          }
        });
      }
    });
  }

  private void initPlayer(IMp3Track trackToPlay) throws AnathemaException {
    this.track = trackToPlay;
    try {
      File preferredFile = Mp3Utilities.getPreferredFile(track);
      if (preferredFile == null) {
        throw new AnathemaException("No file found for track " + track); //$NON-NLS-1$
      }
      player.open(preferredFile);
    }
    catch (BasicPlayerException e) {
      throw new AnathemaException(e);
    }
  }

  public void startPlayback() throws AnathemaException {
    try {
      player.play();
      setStatus(MusicPlayerStatus.PLAYING);
    }
    catch (BasicPlayerException e) {
      throw new AnathemaException(e);
    }
  }

  public void stopPlayback() throws AnathemaException {
    try {
      player.stop();
      playStartTime = 0;
      control.forAllDo(new IClosure<IMusicPlayerModelListener>() {
        public void execute(IMusicPlayerModelListener input) {
          input.positionChanged(0, 0);
        }
      });
      setStatus(MusicPlayerStatus.STOPPED);
    }
    catch (BasicPlayerException e) {
      throw new AnathemaException(e);
    }
  }

  public void seek(int bytePosition) throws AnathemaException {
    try {
      if (Math.abs(currentBytePosition - bytePosition) < 32769) {
        return;
      }
      playStartTime = lengthInMilliseconds * bytePosition / lengthInBytes;
      player.seek(bytePosition);
    }
    catch (BasicPlayerException e) {
      throw new AnathemaException(e);
    }
  }

  public void addMusicModelListener(IMusicPlayerModelListener listener) {
    control.addListener(listener);
  }

  public void pausePlayback() throws AnathemaException {
    try {
      player.pause();
      setStatus(MusicPlayerStatus.PAUSED);
    }
    catch (BasicPlayerException e) {
      throw new AnathemaException(e);
    }
  }

  public void resumePlayback() throws AnathemaException {
    try {
      player.resume();
      setStatus(MusicPlayerStatus.PLAYING);
    }
    catch (BasicPlayerException e) {
      throw new AnathemaException(e);
    }
  }

  private void setStatus(final MusicPlayerStatus status) {
    control.forAllDo(new IClosure<IMusicPlayerModelListener>() {
      public void execute(IMusicPlayerModelListener input) {
        input.statusChanged(status);
      }
    });
  }

  public void setTrack(IMp3Track mp3Track) throws AnathemaException {
    initPlayer(mp3Track);
  }

  private long getTimeLengthEstimation(@SuppressWarnings("rawtypes") Map properties) {
    long milliseconds = -1;
    int byteslength = -1;
    if (properties != null) {
      if (properties.containsKey(PROP_LENGTH)) {
        byteslength = (Integer) properties.get(PROP_LENGTH);
      }
      if (properties.containsKey("duration")) { //$NON-NLS-1$
        milliseconds = (int) ((Long) properties.get("duration")).longValue() / 1000; //$NON-NLS-1$
      }
      else {
        // Try to compute duration
        int bitspersample = -1;
        int channels = -1;
        float samplerate = -1.0f;
        int framesize = -1;
        if (properties.containsKey("audio.samplesize.bits")) { //$NON-NLS-1$
          bitspersample = (Integer) properties.get("audio.samplesize.bits"); //$NON-NLS-1$
        }
        if (properties.containsKey("audio.channels")) { //$NON-NLS-1$
          channels = (Integer) properties.get("audio.channels"); //$NON-NLS-1$
        }
        if (properties.containsKey("audio.samplerate.hz")) { //$NON-NLS-1$
          samplerate = (Float) properties.get("audio.samplerate.hz"); //$NON-NLS-1$
        }
        if (properties.containsKey("audio.framesize.bytes")) { //$NON-NLS-1$
          framesize = (Integer) properties.get("audio.framesize.bytes"); //$NON-NLS-1$
        }
        if (bitspersample > 0) {
          milliseconds = (int) (1000.0f * byteslength / (samplerate * channels * bitspersample / 8));
        }
        else {
          milliseconds = (int) (1000.0f * byteslength / (samplerate * framesize));
        }
      }
    }
    return milliseconds;
  }
}