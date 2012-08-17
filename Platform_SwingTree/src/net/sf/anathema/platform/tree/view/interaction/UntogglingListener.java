package net.sf.anathema.platform.tree.view.interaction;

import javax.swing.JToggleButton;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class UntogglingListener implements PopupMenuListener {
  private final JToggleButton parent;

  public UntogglingListener(JToggleButton parent) {
    this.parent = parent;
  }

  @Override
  public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
    //nothing to do
  }

  @Override
  public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
    parent.setSelected(false);
  }

  @Override
  public void popupMenuCanceled(PopupMenuEvent e) {
    //nothing to do
  }
}