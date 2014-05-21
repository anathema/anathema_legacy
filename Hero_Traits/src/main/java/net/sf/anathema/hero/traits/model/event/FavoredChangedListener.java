package net.sf.anathema.hero.traits.model.event;

import net.sf.anathema.hero.traits.model.FavorableState;
import net.sf.anathema.hero.traits.model.IFavorableStateChangedListener;
import net.sf.anathema.hero.model.change.ChangeAnnouncer;
import net.sf.anathema.hero.model.change.ChangeFlavor;

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
