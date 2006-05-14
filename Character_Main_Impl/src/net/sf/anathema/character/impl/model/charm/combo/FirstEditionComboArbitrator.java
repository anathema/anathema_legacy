package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.charms.DurationType;

public class FirstEditionComboArbitrator extends ComboArbitrator {

  @Override
  protected boolean isCharmLegalByRules(ICharm charm) {
    return charm.getDuration().getType() == DurationType.Instant;
  }
}