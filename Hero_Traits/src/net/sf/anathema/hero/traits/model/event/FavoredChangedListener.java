package net.sf.anathema.hero.traits.model.event;

import net.sf.anathema.character.library.trait.favorable.FavorableState;
import net.sf.anathema.character.library.trait.favorable.IFavorableStateChangedListener;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.ChangeFlavor;

public class FavoredChangedListener implements IFavorableStateChangedListener {
  private final ChangeAnnouncer changeAnnouncer;

  public FavoredChangedListener(ChangeAnnouncer changeAnnouncer) {
    this.changeAnnouncer = changeAnnouncer;
  }

  @Override
  public void favorableStateChanged(FavorableState state) {
    changeAnnouncer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}
