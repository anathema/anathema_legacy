package net.sf.anathema.hero.change;

import net.sf.anathema.lib.control.ChangeListener;

public class AnnounceChangeListener implements ChangeListener {
  private final ChangeAnnouncer announcer;
  private final ChangeFlavor flavor;

  public AnnounceChangeListener(ChangeAnnouncer announcer, ChangeFlavor flavor) {
    this.announcer = announcer;
    this.flavor = flavor;
  }

  @Override
  public void changeOccurred() {
    announcer.announceChangeOf(flavor);
  }
}
