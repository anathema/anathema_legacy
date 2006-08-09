package net.sf.anathema.character.model.traits.attributes;

import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import net.sf.anathema.lib.util.IIdentificate;

public interface IAttributeGroup extends IIdentificate {

  public boolean containsAttribute(AttributeType type);

  public IDefaultTrait getAttributeById(String id);

  public IDefaultTrait getAttributeByType(AttributeType type);

  public IDefaultTrait[] getAttributes();

  public int getCreationValueSum();

  public int getInitialAttributeSum();
}