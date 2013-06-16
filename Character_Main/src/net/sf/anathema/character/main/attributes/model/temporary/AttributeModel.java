package net.sf.anathema.character.main.attributes.model.temporary;

import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.character.library.trait.TraitGroup;

public interface AttributeModel {

  Trait[] getAllAttributes();

  TraitGroup[] getTraitGroups();

  Trait getTrait(AttributeType type);

  IIdentifiedTraitTypeGroup[] getAttributeTypeGroups();
}
