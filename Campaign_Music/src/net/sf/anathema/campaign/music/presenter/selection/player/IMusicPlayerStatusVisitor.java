package net.sf.anathema.campaign.music.presenter.selection.player;

public interface IMusicPlayerStatusVisitor {

  void visitPaused(MusicPlayerStatus visitedStatus);

  void visitStopped(MusicPlayerStatus visitedStatus);

  void visitPlaying(MusicPlayerStatus visitedStatus);
}