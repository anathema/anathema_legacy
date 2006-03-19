package net.sf.anathema.character.model.traits.attributes;

import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.library.trait.ITrait;
import net.sf.anathema.lib.util.IIdentificate;

public interface IAttributeGroup extends IIdentificate {

  public boolean containsAttribute(AttributeType type);

  public ITrait getAttributeById(String id);

  public ITrait getAttributeByType(AttributeType type);

  public ITrait[] getAttributes();

  public int getCreationValueSum();

  public int getInitialAttributeSum();
}