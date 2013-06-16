package net.sf.anathema.character.impl.model.temporary;

import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;

public interface AttributeConfiguration {

  Trait[] getAllAttributes();

  TraitGroup[] getTraitGroups();
}
