package net.sf.anathema.character.model.traits.attributes;

import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.util.Identified;

public interface IAttributeGroup extends Identified {

  boolean containsAttribute(AttributeType type);

  IDefaultTrait getAttributeById(String id);

  IDefaultTrait getAttributeByType(AttributeType type);

  IDefaultTrait[] getAttributes();

  int getCreationValueSum();

  int getInitialAttributeSum();
}