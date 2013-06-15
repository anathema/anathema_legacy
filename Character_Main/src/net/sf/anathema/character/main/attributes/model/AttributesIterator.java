package net.sf.anathema.character.main.attributes.model;

import net.sf.anathema.lib.util.Identifier;

public interface AttributesIterator {

  void nextGroup(Identifier groupId);

  void nextTrait(Identifier traitId);
}
