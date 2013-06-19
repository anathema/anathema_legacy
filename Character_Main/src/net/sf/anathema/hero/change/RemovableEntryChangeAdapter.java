package net.sf.anathema.hero.change;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryListener;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.ChangeFlavor;
import net.sf.anathema.lib.util.Identifier;

public class RemovableEntryChangeAdapter implements IRemovableEntryListener<Identifier> {
  private final ChangeAnnouncer announcer;

  public RemovableEntryChangeAdapter(ChangeAnnouncer announcer) {
    this.announcer = announcer;
  }

  @Override
  public void entryAdded(Identifier entry) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void entryRemoved(Identifier entry) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void entryAllowed(boolean complete) {
    // nothing to do
  }
}
