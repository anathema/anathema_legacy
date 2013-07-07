/**
 *
 */
package net.sf.anathema.character.main.magic;

import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.magic.charms.CharmLearnAdapter;
import net.sf.anathema.hero.change.ChangeAnnouncer;
import net.sf.anathema.hero.change.ChangeFlavor;

public final class CharacterChangeCharmListener extends CharmLearnAdapter {

  private final ChangeAnnouncer announcer;

  public CharacterChangeCharmListener(ChangeAnnouncer announcer) {
    this.announcer = announcer;
  }

  @Override
  public void charmForgotten(ICharm charm) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void charmLearned(ICharm charm) {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }

  @Override
  public void recalculateRequested() {
    announcer.announceChangeOf(ChangeFlavor.UNSPECIFIED);
  }
}