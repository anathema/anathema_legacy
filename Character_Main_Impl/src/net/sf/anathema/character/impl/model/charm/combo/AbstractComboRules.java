package net.sf.anathema.character.impl.model.charm.combo;

import net.sf.anathema.character.generic.magic.ICharm;

public abstract class AbstractComboRules implements IComboRules {
  
  protected final boolean haveSamePrerequisite(ICharm charm1, ICharm charm2) {
    return charm1.getPrerequisites()[0].getType() == charm2.getPrerequisites()[0].getType();
  }
}