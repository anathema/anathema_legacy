package net.sf.anathema.character.model;

import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.lib.control.ChangeListener;

public class UnspecifiedChangeListener implements ChangeListener {

  private ChangeAnnouncer changeAnnouncer;

  public UnspecifiedChangeListener(ChangeAnnouncer changeAnnouncer) {
    this.changeAnnouncer = changeAnnouncer;
  }

  @Override
  public void changeOccurred() {
    changeAnnouncer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}
