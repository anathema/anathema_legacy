package net.sf.anathema.campaign.music.presenter.selection.player;

import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.model.selection.ITrackDetailModel;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.gui.Presenter;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Closure;

public class MusicPlayerPresenter implements Presenter {

  private final IMusicPlayerView view;
  private final IMusicPlayerModel playerModel;
  private final IMusicSelectionModel selectionModel;
  private final String errorString;

  public MusicPlayerPresenter(
          IResources resources,
          IMusicPlayerView view,
          IMusicPlayerModel playerModel,
          IMusicSelectionModel selectionModel) {
    this.view = view;
    this.playerModel = playerModel;
    this.selectionModel = selectionModel;
    this.errorString = resources.getString("Errors.MusicDatabase.Playback"); //$NON-NLS-1$
  }

  private void initSelectionModelListening() {
    if (selectionModel == null) {
      return;
    }
    final ITrackDetailModel trackDetailModel = selectionModel.getTrackDetailModel();
    trackDetailModel.addTrackChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        IMp3Track selectedTrack = trackDetailModel.getSelectedTrack();
        if (selectedTrack != null) {
          try {
            playerModel.stopPlayback();
            playerModel.setTrack(selectedTrack);
          } catch (AnathemaException e1) {
            e1.printStackTrace();
          }
        }
      }
    });
  }

  @Override
  public void initPresentation() {
    initSelectionModelListening();
    initPlayerModelListening();
    view.whenResumeIsTriggered(new Runnable() {
      @Override
      public void run() {
        try {
          playerModel.resumePlayback();
        } catch (AnathemaException e) {
          view.indicateError(new Message(errorString, e));
        }
      }
    });
    view.whenPauseIsTriggered(new Runnable() {
      @Override
      public void run() {
        try {
          playerModel.pausePlayback();
        } catch (AnathemaException e) {
          view.indicateError(new Message(errorString, e));
        }
      }
    });
    view.whenPlayIsTriggered(new Runnable() {
      @Override
      public void run() {
        try {
          playerModel.startPlayback();
        } catch (AnathemaException e) {
          view.indicateError(new Message(errorString, e));
        }
      }
    });
    view.whenStopIsTriggered(new Runnable() {
      @Override
      public void run() {
        try {
          playerModel.stopPlayback();
        } catch (AnathemaException e) {
          view.indicateError(new Message(errorString, e));
        }
      }
    });
    view.addPositionChangeListener(new Closure<Integer>() {
      @Override
      public void execute(Integer newPosition) {
        try {
          playerModel.seek(newPosition);
        } catch (AnathemaException e) {
          e.printStackTrace();
        }
      }
    });
    view.showPlay();
  }

  private void initPlayerModelListening() {
    playerModel.addMusicModelListener(new IMusicPlayerModelListener() {
      @Override
      public void trackOpenend(IMp3Track track, int bytesLength, long totalTime) {
        view.setMaximumPosition(bytesLength, getTimeLabel(totalTime));
      }

      @Override
      public void positionChanged(int bytesread, long timeElapsed) {
        view.setCurrentPosition(bytesread, getTimeLabel(timeElapsed));
      }

      @Override
      public void statusChanged(MusicPlayerStatus status) {
        status.accept(new IMusicPlayerStatusVisitor() {
          @Override
          public void visitPaused(MusicPlayerStatus visitedStatus) {
            view.showResume();
          }

          @Override
          public void visitStopped(MusicPlayerStatus visitedStatus) {
            view.showPlay();
          }

          @Override
          public void visitPlaying(MusicPlayerStatus visitedStatus) {
            view.showPause();
          }
        });
      }
    });
  }

  private String getTimeLabel(long totalTime) {
    long minutes = totalTime / 60;
    long seconds = totalTime % 60;
    if (seconds < 10) {
      return minutes + ":0" + seconds; //$NON-NLS-1$
    }
    return minutes + ":" + seconds; //$NON-NLS-1$
  }
}
