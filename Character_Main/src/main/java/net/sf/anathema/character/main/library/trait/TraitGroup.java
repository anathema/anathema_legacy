package net.sf.anathema.character.main.library.trait;

import net.sf.anathema.lib.util.Identifier;

public interface TraitGroup {
  Trait[] getGroupTraits();

  Identifier getGroupId();
}
