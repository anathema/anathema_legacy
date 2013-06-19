package net.sf.anathema.hero.change;

import org.jmock.example.announcer.Announcer;

public class ChangeAnnouncerImpl implements ChangeAnnouncer {
  private final Announcer<FlavoredChangeListener> announcer = Announcer.to(FlavoredChangeListener.class);

  @Override
  public void addListener(FlavoredChangeListener listener) {
    announcer.addListener(listener);
  }

  @Override
  public void announceChangeOf(ChangeFlavor flavor) {
    announcer.announce().changeOccurred(flavor);
  }
}
