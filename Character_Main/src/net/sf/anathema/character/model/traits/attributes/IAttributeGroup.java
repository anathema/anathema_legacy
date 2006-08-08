package net.sf.anathema.character.model.traits.attributes;

import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.IModifiableTrait;
import net.sf.anathema.lib.util.IIdentificate;

public interface IAttributeGroup extends IIdentificate {

  public boolean containsAttribute(AttributeType type);

  public IModifiableTrait getAttributeById(String id);

  public IModifiableTrait getAttributeByType(AttributeType type);

  public IModifiableTrait[] getAttributes();

  public int getCreationValueSum();

  public int getInitialAttributeSum();
}