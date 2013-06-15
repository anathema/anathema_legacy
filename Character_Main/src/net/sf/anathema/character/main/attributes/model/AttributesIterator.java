package net.sf.anathema.character.main.attributes.model;

import net.sf.anathema.lib.util.Identified;

public interface AttributesIterator {

  void nextGroup(Identified groupId);

  void nextTrait(Identified traitId);
}
