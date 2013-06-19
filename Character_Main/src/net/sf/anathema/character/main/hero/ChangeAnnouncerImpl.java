package net.sf.anathema.character.main.hero;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.main.hero.change.FlavoredChangeListener;
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
