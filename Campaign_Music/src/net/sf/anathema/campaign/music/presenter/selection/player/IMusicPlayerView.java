package net.sf.anathema.campaign.music.presenter.selection.player;

import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.util.Closure;

public interface IMusicPlayerView {

  void addPositionChangeListener(Closure<Integer> listener);

  void setMaximumPosition(int maximum, String time);

  void setCurrentPosition(int position, String time);

  void indicateError(Message message);

  void showPlay();

  void showResume();

  void showPause();

  void whenResumeIsTriggered(Runnable resumeAction);

  void whenPauseIsTriggered(Runnable pauseAction);

  void whenPlayIsTriggered(Runnable playAction);

  void whenStopIsTriggered(Runnable stopAction);
}