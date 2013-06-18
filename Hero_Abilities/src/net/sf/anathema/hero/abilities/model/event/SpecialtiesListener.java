package net.sf.anathema.hero.abilities.model.event;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.change.ChangeFlavor;
import net.sf.anathema.character.library.trait.specialties.Specialty;
import net.sf.anathema.character.library.trait.subtrait.ISpecialtyListener;

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
