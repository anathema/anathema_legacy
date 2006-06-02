package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.Duration;

public class FirstEditionComboArbitrator extends ComboArbitrator {

  @Override
  protected boolean isCharmLegalByRules(ICharm charm) {
    return charm.getDuration() == Duration.INSTANT_DURATION;
  }
}