package net.sf.anathema.hero.experience.model;

import net.sf.anathema.hero.advance.experience.ExperiencePointConfigurationListener;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.ChangeFlavor;

public class AnnounceExperiencePointChange implements ExperiencePointConfigurationListener {
  private final ChangeAnnouncer announcer;

  public AnnounceExperiencePointChange(ChangeAnnouncer announcer) {
    this.announcer = announcer;
  }

  @Override
  public void entriesAddedRemovedOrChanged() {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}