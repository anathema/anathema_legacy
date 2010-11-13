package net.sf.anathema.campaign.music.impl.model.player;

import javazoom.jlgui.basicplayer.BasicPlayer;

public class ExtendedBasicPlayer extends BasicPlayer {
  public long getElapsedTime() {
    if (m_line == null) {
      return 0;
    }
    return m_line.getMicrosecondPosition() / 1000;
  }
}