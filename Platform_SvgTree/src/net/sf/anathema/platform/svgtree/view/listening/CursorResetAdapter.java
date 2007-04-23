package net.sf.anathema.platform.svgtree.view.listening;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CursorResetAdapter extends MouseAdapter {

  private boolean enabled = false;
  private final SvgTreeListening listening;

  public CursorResetAdapter(SvgTreeListening listening) {
    this.listening = listening;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    if (!enabled) {
      return;
    }
    listening.resetCursor();
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}