package net.sf.anathema.campaign.music.presenter.selection.player;

import javax.swing.Action;
import javax.swing.event.ChangeListener;

public interface IMusicPlayerView {

  public void setPlayAction(Action playAction);

  public void setStopAction(Action action);

  public void addPositionChangeListener(ChangeListener listener);

  public int getCurrentPosition();

  public void setMaximumPosition(int maximum, String time);

  public void setCurrentPosition(int position, String time);
}