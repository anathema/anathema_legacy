package net.sf.anathema.campaign.music.presenter.selection.player;

public interface IMusicPlayerStatusVisitor {

  public void visitPaused(MusicPlayerStatus visitedStatus);

  public void visitStopped(MusicPlayerStatus visitedStatus);

  public void visitPlaying(MusicPlayerStatus visitedStatus);
}