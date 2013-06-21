package net.sf.anathema.character.library.trait.specialties;

import net.sf.anathema.character.library.trait.subtrait.ISpecialtyListener;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.ChangeFlavor;

public class SpecialtiesListener implements ISpecialtyListener {
  private final ChangeAnnouncer changeAnnouncer;

  public SpecialtiesListener(ChangeAnnouncer changeAnnouncer) {
    this.changeAnnouncer = changeAnnouncer;
  }

  @Override
  public void subTraitRemoved(Specialty specialty) {
    changeAnnouncer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void subTraitAdded(Specialty specialty) {
    changeAnnouncer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void subTraitValueChanged() {
    changeAnnouncer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}
