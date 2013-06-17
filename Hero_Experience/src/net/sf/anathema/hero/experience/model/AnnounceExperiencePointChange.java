package net.sf.anathema.hero.experience.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.model.advance.IExperiencePointConfigurationListener;
import net.sf.anathema.character.model.advance.IExperiencePointEntry;

public class AnnounceExperiencePointChange implements IExperiencePointConfigurationListener {
  private final ChangeAnnouncer announcer;

  public AnnounceExperiencePointChange(ChangeAnnouncer announcer) {
    this.announcer = announcer;
  }

  @Override
  public void entryAdded(IExperiencePointEntry entry) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void entryRemoved(IExperiencePointEntry entry) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void entryChanged(IExperiencePointEntry entry) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}
