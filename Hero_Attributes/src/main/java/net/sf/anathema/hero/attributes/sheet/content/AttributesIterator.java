package net.sf.anathema.hero.attributes.sheet.content;

import net.sf.anathema.lib.util.Identifier;

public interface AttributesIterator {

  void nextGroup(Identifier groupId);

  void nextTrait(Identifier traitId);
}
