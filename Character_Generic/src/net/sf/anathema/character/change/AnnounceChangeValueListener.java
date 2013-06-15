package net.sf.anathema.character.change;

import net.sf.anathema.lib.control.ObjectValueListener;

public class AnnounceChangeValueListener implements ObjectValueListener<String> {
  private final ChangeAnnouncer announcer;
  private final ChangeFlavor flavor;

  public AnnounceChangeValueListener(ChangeAnnouncer announcer) {
    this(announcer, ChangeFlavor.UNSPECIFIED);
  }

  public AnnounceChangeValueListener(ChangeAnnouncer announcer, ChangeFlavor flavor) {
    this.announcer = announcer;
    this.flavor = flavor;
  }

  @Override
  public void valueChanged(String newValue) {
    announcer.announceChangeOf(flavor);
  }
}
