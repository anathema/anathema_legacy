package net.sf.anathema.character.model.traits.attributes;

import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.lib.util.Identifier;

public interface IAttributeGroup extends Identifier {

  boolean containsAttribute(AttributeType type);

  Trait getAttributeById(String id);

  Trait getAttributeByType(AttributeType type);

  Trait[] getAttributes();

  int getCreationValueSum();

  int getInitialAttributeSum();
}