package net.sf.anathema.hero.traits.model;

import net.sf.anathema.lib.util.Identifier;

public interface TraitGroup {
  Trait[] getGroupTraits();

  Identifier getGroupId();
}
