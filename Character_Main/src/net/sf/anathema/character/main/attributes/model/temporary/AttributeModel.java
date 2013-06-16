package net.sf.anathema.character.main.attributes.model.temporary;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;
import net.sf.anathema.character.main.traits.model.TraitMap;

public interface AttributeModel extends TraitMap {

  Trait[] getAll();

  TraitGroup[] getTraitGroups();

  IIdentifiedTraitTypeGroup[] getAttributeTypeGroups();
}
