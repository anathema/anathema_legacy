package net.sf.anathema.character.generic.magic.charms.special;

import net.sf.anathema.character.generic.magic.ICharm;

public interface IPrerequisiteModifyingCharm extends ISpecialCharm {
  int modifyRequiredValue(ICharm charm, int currentlyRequiredValue);
}