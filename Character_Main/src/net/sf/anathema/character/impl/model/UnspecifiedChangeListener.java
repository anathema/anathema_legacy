package net.sf.anathema.character.impl.model;

import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.lib.control.IChangeListener;

public class UnspecifiedChangeListener implements IChangeListener {

  private ChangeAnnouncer changeAnnouncer;

  public UnspecifiedChangeListener(ChangeAnnouncer changeAnnouncer) {
    this.changeAnnouncer = changeAnnouncer;
  }

  @Override
  public void changeOccurred() {
    changeAnnouncer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}
