package net.sf.anathema.character.main.magic.display.view.combos;

import net.sf.anathema.character.main.magic.model.combos.ICombo;
import net.sf.anathema.character.main.magic.model.combos.IComboConfigurationListener;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.ChangeFlavor;

public final class CharacterChangeComboListener implements IComboConfigurationListener {

  private ChangeAnnouncer announcer;

  public CharacterChangeComboListener(ChangeAnnouncer announcer) {
    this.announcer = announcer;
  }

  @Override
  public void editEnded() {
    // Nothing to do
  }

  @Override
  public void editBegun(ICombo combo) {
    // Nothing to do
  }

  @Override
  public void comboDeleted(ICombo combo) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void comboChanged(ICombo combo) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void comboAdded(ICombo combo) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}