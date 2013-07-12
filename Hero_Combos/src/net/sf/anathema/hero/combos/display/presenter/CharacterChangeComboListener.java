package net.sf.anathema.hero.combos.display.presenter;

import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.ChangeFlavor;

public class CharacterChangeComboListener implements ComboConfigurationListener {

  private ChangeAnnouncer announcer;

  public CharacterChangeComboListener(ChangeAnnouncer announcer) {
    this.announcer = announcer;
  }

  @Override
  public void editEnded() {
    // Nothing to do
  }

  @Override
  public void editBegun(Combo combo) {
    // Nothing to do
  }

  @Override
  public void comboDeleted(Combo combo) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void comboChanged(Combo combo) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void comboAdded(Combo combo) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}