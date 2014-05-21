package net.sf.anathema.hero.traits.model.event;

import net.sf.anathema.hero.traits.model.Trait;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.traits.model.TraitChangeFlavor;
import net.sf.anathema.lib.control.IntValueChangedListener;

public class TraitValueChangedListener implements IntValueChangedListener {
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
