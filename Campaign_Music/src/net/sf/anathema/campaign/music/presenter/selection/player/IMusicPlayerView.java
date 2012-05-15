package net.sf.anathema.campaign.music.presenter.selection.player;

import javax.swing.Action;
import javax.swing.event.ChangeListener;

public interface IMusicPlayerView {

  void setPlayAction(Action playAction);

  void setStopAction(Action action);

  void addPositionChangeListener(ChangeListener listener);

  int getCurrentPosition();

  void setMaximumPosition(int maximum, String time);

  void setCurrentPosition(int position, String time);
}