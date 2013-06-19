package net.sf.anathema.hero.traits.model.event;

import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.main.model.traits.TraitChangeFlavor;
import net.sf.anathema.lib.control.IIntValueChangedListener;

public class TraitValueChangedListener implements IIntValueChangedListener {
  private final ChangeAnnouncer changeAnnouncer;
  private final Trait trait;

  public TraitValueChangedListener(ChangeAnnouncer changeAnnouncer, Trait trait) {
    this.changeAnnouncer = changeAnnouncer;
    this.trait = trait;
  }

  @Override
  public void valueChanged(int newValue) {
    changeAnnouncer.announceChangeOf(new TraitChangeFlavor(trait.getType()));
  }
}
