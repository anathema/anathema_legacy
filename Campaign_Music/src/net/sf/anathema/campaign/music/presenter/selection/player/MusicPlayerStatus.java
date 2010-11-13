package net.sf.anathema.campaign.music.presenter.selection.player;

public enum MusicPlayerStatus {

  PLAYING {
    @Override
    public void accept(IMusicPlayerStatusVisitor visitor) {
      visitor.visitPlaying(this);
    }
  },
  STOPPED {
    @Override
    public void accept(IMusicPlayerStatusVisitor visitor) {
      visitor.visitStopped(this);
    }
  },
  PAUSED {
    @Override
    public void accept(IMusicPlayerStatusVisitor visitor) {
      visitor.visitPaused(this);
    }
  };

  public abstract void accept(IMusicPlayerStatusVisitor visitor);
}