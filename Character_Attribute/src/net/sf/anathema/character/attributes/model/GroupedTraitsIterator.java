package net.sf.anathema.character.attributes.model;

import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.lib.util.Identified;

public interface GroupedTraitsIterator {

  void nextGroup(Identified groupId);

  void nextTrait(ITraitType traitId, int currentValue);
}
