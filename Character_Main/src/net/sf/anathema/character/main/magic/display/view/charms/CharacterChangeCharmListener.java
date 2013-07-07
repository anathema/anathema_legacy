/**
 *
 */
package net.sf.anathema.character.main.magic.display.view.charms;

import net.sf.anathema.character.main.magic.model.charm.ICharm;
import net.sf.anathema.character.main.magic.model.charm.CharmLearnAdapter;
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