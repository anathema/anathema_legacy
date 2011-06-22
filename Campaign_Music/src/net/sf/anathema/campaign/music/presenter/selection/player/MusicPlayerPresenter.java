package net.sf.anathema.campaign.music.presenter.selection.player;

import java.awt.Component;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.disy.commons.core.message.Message;
import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.campaign.music.model.selection.IMusicSelectionModel;
import net.sf.anathema.campaign.music.model.selection.ITrackDetailModel;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.campaign.music.presenter.MusicUI;
import net.sf.anathema.framework.message.MessageUtilities;
import net.sf.anathema.lib.control.change.IChangeListener;
import net.sf.anathema.lib.exception.AnathemaException;
import net.sf.anathema.lib.gui.IPresenter;
import net.sf.anathema.lib.resources.IResources;

public class MusicPlayerPresenter implements IPresenter {

  private final IMusicPlayerView view;
  private final IMusicPlayerModel playerModel;
  private SmartAction playAction;
  private SmartAction pauseAction;
  private SmartAction resumeAction;
  private final IMusicSelectionModel selectionModel;
  private final IResources resources;
  private final String errorString;

  public MusicPlayerPresenter(
      IResources resources,
      final IMusicPlayerView view,
      final IMusicPlayerModel playerModel,
      final IMusicSelectionModel selectionModel) {
    this.resources = resources;
    this.view = view;
    this.playerModel = playerModel;
    this.selectionModel = selectionModel;
    errorString = resources.getString("Errors.MusicDatabase.Playback"); //$NON-NLS-1$
  }

  private void initSelectionModelListening() {
    if (selectionModel == null) {
      return;
    }
    final ITrackDetailModel trackDetailModel = selectionModel.getTrackDetailModel();
    trackDetailModel.addTrackChangeListener(new IChangeListener() {
      public void changeOccured() {
        IMp3Track selectedTrack = trackDetailModel.getSelectedTrack();
        if (selectedTrack != null) {
          try {
            playerModel.stopPlayback();
            playerModel.setTrack(selectedTrack);
          }
          catch (AnathemaException e1) {
            e1.printStackTrace();
          }
        }
      }
    });
  }

  public void initPresentation() {
    initSelectionModelListening();
    initPlayerModelListening();
    MusicUI musicUI = new MusicUI(resources);
    pauseAction = new SmartAction(musicUI.getPauseButtonIcon()) {
      private static final long serialVersionUID = -8116745122433330752L;

      @Override
      protected void execute(Component parentComponent) {
        try {
          playerModel.pausePlayback();
        }
        catch (AnathemaException e) {
          Message message = new Message(errorString, e);
          MessageUtilities.indicateMessage(MusicPlayerPresenter.class, parentComponent, message);
        }
      }
    };
    resumeAction = new SmartAction(musicUI.getResumeButtonIcon()) {
      private static final long serialVersionUID = 4815188262326471020L;

      @Override
      protected void execute(Component parentComponent) {
        try {
          playerModel.resumePlayback();
        }
        catch (AnathemaException e) {
          Message message = new Message(errorString, e);
          MessageUtilities.indicateMessage(MusicPlayerPresenter.class, parentComponent, message);
        }
      }
    };

    playAction = new SmartAction(musicUI.getPlayButtonIcon()) {
      private static final long serialVersionUID = -8530073924466518798L;

      @Override
      protected void execute(Component parentComponent) {
        try {
          playerModel.startPlayback();
        }
        catch (AnathemaException e) {
          Message message = new Message(errorString, e);
          MessageUtilities.indicateMessage(MusicPlayerPresenter.class, parentComponent, message);
        }
      }
    };
    view.setPlayAction(playAction);

    view.setStopAction(new SmartAction(musicUI.getStopButtonIcon()) {
      private static final long serialVersionUID = 4822218505247692013L;

      @Override
      protected void execute(Component parentComponent) {
        try {
          playerModel.stopPlayback();
        }
        catch (AnathemaException e) {
          Message message = new Message(errorString, e);
          MessageUtilities.indicateMessage(MusicPlayerPresenter.class, parentComponent, message);
        }
      }
    });
    view.addPositionChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent event) {
        JSlider slider = (JSlider) event.getSource();
        int bytes = slider.getValue();
        if (!slider.getValueIsAdjusting()) {
          try {
            playerModel.seek(bytes);
          }
          catch (AnathemaException e) {
            e.printStackTrace();
          }
        }
      }
    });
  }

  private void initPlayerModelListening() {
    playerModel.addMusicModelListener(new IMusicPlayerModelListener() {
      public void trackOpenend(IMp3Track track, int bytesLength, long totalTime) {
        view.setMaximumPosition(bytesLength, getTimeLabel(totalTime));
      }

      public void positionChanged(int bytesread, long timeElapsed) {
        view.setCurrentPosition(bytesread, getTimeLabel(timeElapsed));
      }

      public void statusChanged(MusicPlayerStatus status) {
        status.accept(new IMusicPlayerStatusVisitor() {
          public void visitPaused(MusicPlayerStatus visitedStatus) {
            view.setPlayAction(resumeAction);
          }

          public void visitStopped(MusicPlayerStatus visitedStatus) {
            view.setPlayAction(playAction);
          }

          public void visitPlaying(MusicPlayerStatus visitedStatus) {
            view.setPlayAction(pauseAction);
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