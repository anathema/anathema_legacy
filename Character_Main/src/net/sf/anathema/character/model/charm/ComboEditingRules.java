package net.sf.anathema.character.model.charm;

import net.sf.anathema.character.generic.magic.ICharm;

public interface ComboEditingRules {
  boolean isAllowedToRemove(ICharm charm);
  
  boolean canFinalize();

  boolean canFinalizeWithXP();
}